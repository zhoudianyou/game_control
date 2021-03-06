/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.util.List;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.entity.TTicketpoolHistory;


/**
 * @author tianhao
 *
 */
public interface TransactionHistoryDao {
	
	/*增加交易记录*/
	public String addTransRecord(TTicketTransaction transRecord);

	/*批量查找未更新状态的票*/
	public List<TTicketTransaction> findUnupdata();
	
	/*批量更新已售票状态*/
	public void updataSoldStatus();

	/*增加票历史*/
	public int addTicketpoolHistory(TTicketpoolHistory tcketpoolHistory);

	/*寻找当前SerialNumber*/
	public long getCurrentSerialNumber(int gameId);
	
	/*根据票号、步骤号，寻找已销售的票*/
	public TTicketTransaction findSoldTicketbySn(String soldTicketSn ,int step );

	/*根据事务id寻找已销售的票*/
	public TTicketTransaction findByTid(String transactionID);

	/*根据事务id寻找中奖票*/
	public TTicketTransaction findUnPayTicketbySn(String tsn);

	/*标识已兑奖*/
	public void updataPayStatus(String tsn,boolean flag);

	
	
}
