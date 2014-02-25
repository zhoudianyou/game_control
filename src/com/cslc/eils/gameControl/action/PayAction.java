/**
 * 
 */
package com.cslc.eils.gameControl.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dao.TransactionHistoryDao;
import com.cslc.eils.gameControl.dao.UsersDAO;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.pojo.REQPay;
import com.cslc.eils.gameControl.pojo.RESPay;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;
import com.cslc.eils.gameControl.util.SystemUtil;

/**
 * @author tianhao
 *
 */
public class PayAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(PayAction.class);

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		REQPay reqPay = new REQPay();
		RESPay resPay = new RESPay();
		
		try{
			String reqJson =  req.getParameter("content");
			log.info("�յ��ҽ�����"+reqJson);
			reqPay = JsonUtil.jsonToPojo(reqJson, REQPay.class);
			String tsn = reqPay.getTicketSn();
			String userId = reqPay.getUserId();
			UsersDAO usersDAOImpl = (UsersDAO) InitSystem.getBean("usersDao");
			TransactionHistoryDao trans = (TransactionHistoryDao) InitSystem.getBean("transactionHistoryDao");
			TTicketTransaction ticket = trans.findUnPayTicketbySn(tsn);
			if (usersDAOImpl.findByUserId(userId) == null){
				//�û�������
				resPay.setErrCode(-1);
				resPay.setErrDesc("�ҽ�ʧ�ܣ�δ�ҵ����û���");
			}else if(ticket ==null ){
				//Ʊ������
				resPay.setErrCode(-1);
				resPay.setErrDesc("�ҽ�ʧ�ܣ�δ�ҵ����н�Ʊ�������Ƿ��Ѷҹ���");
			}else if(SystemUtil.resolvePrizeFromTicketContent(ticket.getTicketContent()) != reqPay.getPrize()){
				//�ҽ�������
				resPay.setErrCode(-1);
				resPay.setErrDesc("�ҽ�ʧ�ܣ��н�������");
			}else{
				//�ҽ�
				usersDAOImpl.pay(userId, reqPay.getPrize());
				trans.updataPayStatus(tsn,true);
				resPay.setPrize(reqPay.getPrize());
				resPay.setPayTime(DateUtil.getDate());
				resPay.setErrCode(0);
				resPay.setErrDesc("�ҽ��ɹ���");
			}
		}catch(Exception e){
			// TODO
			e.printStackTrace();
			//�ҽ�������
			resPay.setErrCode(-1);
			resPay.setErrDesc("�ҽ�ʧ�ܣ�ϵͳ�쳣!");
		}finally{
			resPay.setTicketSn(reqPay.getTicketSn());
			String resJson = JsonUtil.pojoToJson(resPay);
			log.info("�ҽ����Ϊ��"+resJson);
			
			PrintWriter pw =null;
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			try {
				pw=resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.print(resJson);	
		}
		return null;
	}

}
