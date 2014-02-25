/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.ArrayList;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;


/**
 * 票池管理接口
 * @author tianhao
 *
 */
public interface ITicketPoolManager {
	
	/**
	 * 从票池取票
	 * @param position
	 * @return
	 */
	public Ticket getReplaceTicket(int position) ;
	
	/**
	 * 获取票池相应位置的票，并用候补票替换票池相应位置的待售票，
	 * @param position 票池位置
	 * @param replaceTicket 替换票
	 * @return 被替换票
	 */
	public Ticket replace(int position,Ticket replaceTicket);
	
	/**
	 * 取票失败，回滚票池
	 * @param position
	 * @param ticket
	 */
	public void rollbackTrans(int position, Ticket ticket);

	/**
	 * 票池位置加锁
	 * @param position
	 * @return
	 */
	public  boolean locksPosition(int position);
	
	/**
	 * 票池位置解锁
	 * @param position
	 * @return
	 */
	public  boolean releasePosition(int position);

	/**
	 * 判断票池位置是否被加锁
	 * @param position
	 * @return
	 */
	public boolean contains(int position);
	
	/**
	 * 获取票池名称
	 * @return
	 */
	public String getPoolName();
	
	/**
	 * 获取候补票队列
	 * @return
	 */
	public OnSalePool getOnSalePool();
	
	/**
	 * 清空票池，
	 * @return
	 */
	public boolean clear();

	/**
	 * 向票池中批量增加票
	 * @param tickets
	 */
	public void addTickets(ArrayList<TTicketImported> tickets);

	/**
	 * 向候补票队列增加票
	 * @param tTicketImported
	 */
	public void addTicket2waiting(TTicketImported tTicketImported);
	
	/**
	 * 获取候补票队列大小
	 * @return
	 */
	public int getSize4Waiting();
	
	/**
	 * 判断后补票队列是否不足，
	 * @return
	 */
	public boolean isLacking();
}
