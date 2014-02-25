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
	
	/*���ӽ��׼�¼*/
	public String addTransRecord(TTicketTransaction transRecord);

	/*��������δ����״̬��Ʊ*/
	public List<TTicketTransaction> findUnupdata();
	
	/*������������Ʊ״̬*/
	public void updataSoldStatus();

	/*����Ʊ��ʷ*/
	public int addTicketpoolHistory(TTicketpoolHistory tcketpoolHistory);

	/*Ѱ�ҵ�ǰSerialNumber*/
	public long getCurrentSerialNumber(int gameId);
	
	/*����Ʊ�š�����ţ�Ѱ�������۵�Ʊ*/
	public TTicketTransaction findSoldTicketbySn(String soldTicketSn ,int step );

	/*��������idѰ�������۵�Ʊ*/
	public TTicketTransaction findByTid(String transactionID);

	/*��������idѰ���н�Ʊ*/
	public TTicketTransaction findUnPayTicketbySn(String tsn);

	/*��ʶ�Ѷҽ�*/
	public void updataPayStatus(String tsn,boolean flag);

	
	
}
