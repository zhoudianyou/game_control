package com.cslc.eils.gameControl.core;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.cache.ReceiveMsgManager;
import com.cslc.eils.gameControl.exception.DBUnavailableException;
import com.cslc.eils.gameControl.exception.TicketPoolNotFind;
import com.cslc.eils.gameControl.exception.UnSupprotedGameException;
import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.REQQuery;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.pojo.TransBetMsg;
import com.cslc.eils.gameControl.pojo.TransQueryMsg;
import com.cslc.eils.gameControl.service.GameService;
import com.cslc.eils.gameControl.service.QueryService;
import com.cslc.eils.gameControl.util.SysErrorCode;




public class TradeThread implements IProcThread, Runnable{
	
	private static final Log log = LogFactory.getLog(TradeThread.class);

	
	private boolean flag = true;

	@Override
	public void run() {
		log.info("启动处理线程。");
		while (flag) {
			try {
				Object reqObj = ReceiveMsgManager.getInstance().pollReq();
				if(reqObj!=null){
					//处理投注请求
					if (reqObj instanceof TransBetMsg) {
						log.info("获取投注对象。");
						TransBetMsg transBetMsg = (TransBetMsg) reqObj;
						this.bet(transBetMsg);
					}else if (reqObj instanceof TransQueryMsg){
						//处理交易结果查询请求
						TransQueryMsg transQueryMsg =  (TransQueryMsg) reqObj;
						this.query(transQueryMsg);
					}
				}else{
					log.debug("未获取到任何请求对象，请求队列为空！");
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				// TODO 记日志。
				e.printStackTrace();
				
			}
		}

	}

	

	



	/**
	 * 投注处理逻辑；
	 * 若是重试，则发起查询请求
	 * 否则，投注，并通知action
	 * @param reqBet
	 * @throws InterruptedException
	 */
	private void bet(TransBetMsg transBetMsg) throws InterruptedException {
		REQBet reqBet = transBetMsg.getReqBet();
		RESBet resBet = new RESBet();;
		try {
			//构造游戏服务
			GameService gameService = (GameService) InitSystem.getBean("gameService");
			if(gameService != null){
				if(reqBet.getRetryFlag()){
					//重试，则发起查询请求
					REQQuery reqQuery = new REQQuery();
					reqQuery.setTransactionID(reqQuery.getTransactionID());
					reqQuery.setRequestTime(reqBet.getRequestTime());
					resBet = this.queryByReq(reqQuery);
					//	TODO 如果是未决交易则完成交易
				}else{
					//投注，初始化游戏服务
					gameService.initService(transBetMsg.getReqBet().getGameID(), 
							transBetMsg.getReqBet().getBetValue());
					resBet = gameService.getTicket(reqBet);
				}
				
			}else{
				resBet.setErrCode(SysErrorCode.BET_FAILURE);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE)+",游戏服务加载失败！未找到服务类，请检查服务配置是否正确！");
			}
		} catch (UnSupprotedGameException e) {
			e.printStackTrace();
			resBet.setErrCode(SysErrorCode.BET_FAILURE);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
		} catch (DBUnavailableException e) {
			e.printStackTrace();
			resBet.setErrCode(SysErrorCode.BET_FAILURE);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
		} catch (TicketPoolNotFind e) {
			e.printStackTrace();
			resBet.setErrCode(SysErrorCode.BET_FAILURE);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
		} catch (Exception e) {
			e.printStackTrace();
			resBet.setErrCode(SysErrorCode.BET_FAILURE);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
		} finally {
			synchronized (transBetMsg) {
				resBet.setTransactionID(reqBet.getTransactionID());
				transBetMsg.setResBet(resBet);
				transBetMsg.notify();
			}
		}		
	}

	/**
	 * 查询交易结果，并通知action
	 * @param reqQuery
	 */
	private void query(TransQueryMsg transQueryMsg) {
		REQQuery reqQuery = transQueryMsg.getReqQuery();
		QueryService queryService = (QueryService) InitSystem.getBean("queryService");
		RESBet resBet = queryService.queryByReq(reqQuery);
		resBet.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
		resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
		//  notify action
				synchronized(transQueryMsg){
					transQueryMsg.setResBet(resBet);
					transQueryMsg.notify();
				}
	}
	
	/**
	 * 投注时可调用此接口查询投注结果，根据结果决定是否投注
	 * @param reqQuery
	 * @return
	 */
	private RESBet queryByReq(REQQuery reqQuery) {
		QueryService queryService = (QueryService) InitSystem.getBean("queryService");
		RESBet resBet = queryService.queryByReq(reqQuery);
		resBet.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
		resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
		return resBet;
	}


	
	
	@Override
	public void stopThread() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getThreadName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isFlag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlag(boolean flag) {
		// TODO Auto-generated method stub
		
	}
	
	

}
