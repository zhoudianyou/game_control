/**
 * 
 */
package com.cslc.eils.gameControl.service;

import com.cslc.eils.gameControl.dao.TransactionHistoryDao;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.pojo.REQQuery;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.SysErrorCode;
import com.cslc.eils.gameControl.util.SystemUtil;

/**
 * @author tianhao
 *
 */
public class QueryService {
	private TransactionHistoryDao transactionHistoryDao;
	
	public RESBet queryByReq(REQQuery reqQuery) {

		TTicketTransaction tTransaction = new TTicketTransaction();
		RESBet resBet = new RESBet();

		tTransaction = transactionHistoryDao.findByTid(reqQuery
				.getTransactionID());
		if(tTransaction != null){
			resBet.setTransactionID(tTransaction.getTransactionId());
			resBet.setTicketSn(tTransaction.getSoldTicketSn());
			resBet.setSaleTime(DateUtil.getDateString(tTransaction.getSoldTime(),
					"yyyyMMddHHmmss"));
			resBet.setPrize(SystemUtil.getPrizeFromOutPut(tTransaction
					.getTicketContent()));
			resBet.setOutPut(SystemUtil.resolveOutPutFromTicketContent(tTransaction
					.getTicketContent()));
		}else{
			//Î´ÕÒµ½¼ÇÂ¼
			resBet.setErrCode(SysErrorCode.UN_FIND_TRANSACTION);
			resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.UN_FIND_TRANSACTION));
		}
		resBet.setTransactionID(reqQuery.getTransactionID());
		return resBet;
	}

	public TransactionHistoryDao getTransactionHistoryDao() {
		return transactionHistoryDao;
	}

	public void setTransactionHistoryDao(TransactionHistoryDao transactionHistoryDao) {
		this.transactionHistoryDao = transactionHistoryDao;
	}
	
}
