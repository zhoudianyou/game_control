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
import com.cslc.eils.gameControl.dao.UsersDAO;
import com.cslc.eils.gameControl.entity.TInfoUsers;
import com.cslc.eils.gameControl.pojo.REQQueryUser;
import com.cslc.eils.gameControl.pojo.RESQueryUser;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;

/**
 * @author tianhao
 *
 */
public class QueryUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(QueryUserAction.class);
	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		REQQueryUser reqQueryUser = new REQQueryUser();
		RESQueryUser resQueryUser = new RESQueryUser();
		
		try{
			String reqJson =  req.getParameter("content");
			reqQueryUser = JsonUtil.jsonToPojo(reqJson, REQQueryUser.class);
			UsersDAO usersDAOImpl = (UsersDAO) InitSystem.getBean("usersDao");
			TInfoUsers user = (TInfoUsers) usersDAOImpl.findByUserId(reqQueryUser.getUserId());
			if(user == null){
				resQueryUser.setErrCode(-2);
				resQueryUser.setErrDesc("查询用户失败！此用户不存在");
			}else{
				resQueryUser.setAccount(String.valueOf(user.getAccount()));
				resQueryUser.setTotalPrize(String.valueOf(user.getTotalPrize()));
				resQueryUser.setPayTime(DateUtil.getDate());
				resQueryUser.setErrCode(0);
				resQueryUser.setErrDesc("查询用户成功!");
			}
		}catch(Exception e){
			// TODO
			resQueryUser.setErrCode(-2);
			resQueryUser.setErrDesc("查询用户失败！系统异常");
		}finally{
			String resJson = JsonUtil.pojoToJson(resQueryUser);
			log.info("查询用户结果为："+resJson);
			
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
