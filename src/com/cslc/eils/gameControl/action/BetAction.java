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

import com.cslc.eils.gameControl.cache.ReceiveMsgManager;
import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dao.UsersDAO;
import com.cslc.eils.gameControl.dao.impl.UsersDAOImpl;
import com.cslc.eils.gameControl.entity.TInfoUsers;
import com.cslc.eils.gameControl.exception.UnSupprotedGameException;
import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.pojo.TransBetMsg;
import com.cslc.eils.gameControl.util.JsonUtil;
import com.cslc.eils.gameControl.util.SysErrorCode;

/**
 * @author tianhao
 *
 */
public class BetAction extends BaseAction {

	private static final Log log = LogFactory.getLog(BetAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		REQBet reqBet = new REQBet();
		RESBet resBet = new RESBet();
		try {
			String reqJson =  req.getParameter("content");
			log.info("收到投注请求："+reqJson);
			reqBet = JsonUtil.jsonToPojo(reqJson, REQBet.class);
			check(reqBet.getGameID());
			
			TransBetMsg transBetMsg = new TransBetMsg();
			transBetMsg.setReqBet(reqBet);
			synchronized(transBetMsg){
				// push to queue
				ReceiveMsgManager.getInstance().pushReq(transBetMsg);
				// 防止假唤醒
				while(transBetMsg.getResBet() == null){
					transBetMsg.wait();
				}
			}
			// 获得结果
			resBet = transBetMsg.getResBet();
			
			/**TODO 投注成功扣钱*/
			UsersDAO usersDAOImpl = (UsersDAO) InitSystem.getBean("usersDao");
			long account = usersDAOImpl.queryAccountByUserId(reqBet.getUserId());
			if(account >= reqBet.getBetValue()){
				//扣钱
				usersDAOImpl.consume(reqBet.getUserId(), reqBet.getBetValue());
			}else{
				//账户金额不足
				resBet.setErrCode(SysErrorCode.BET_FAILURE);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE)+"账户金额不足！");
				resBet.setOutPut("");
				resBet.setPrize(0);
				resBet.setTicketSn("");
				resBet.setEndFlag(true);
			}
			
		}catch(UnSupprotedGameException e ){
			resBet.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.GAME_NOT_SUPPORTED)+"游戏id为：:"+reqBet.getGameID());
		}catch(Exception e){
			e.printStackTrace();
			resBet.setErrCode(SysErrorCode.BET_FAILURE);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
		}finally{
			String resJson = JsonUtil.pojoToJson(resBet);
			log.info("投注结果为："+resJson);
			PrintWriter pw =null;
			resp.setCharacterEncoding("gbk");
			resp.setContentType("text/html;charset=gbk");
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
