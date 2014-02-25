package com.cslc.eils.gameControl.cache;

import java.util.List;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * 候补票队列接口
 * @author tianhao
 *
 */
public interface IWaitingDeque {

	/**
	 * 在队列头部增加票
	 * @param ticket
	 * @return
	 */
	public boolean addFirst(Ticket ticket);
	
	/**
	 * 在队列尾部增加票
	 * @param ticket
	 * @return
	 */
	public boolean addLast(Ticket ticket);
	
	/**
	 * 从队列头部取票
	 * @param ticket
	 * @return
	 */
	public Ticket removeFirst();
	
	/**
	 * 批量增加票，仅在初始化时使用
	 * @param tickets
	 * @return
	 */
	public void addAll(List<TTicketImported> tickets) ;
	
	/**
	 * 清空票池，
	 * @return
	 */
	public boolean clear();
	
	/**
	 * 获取队列大小
	 * @return
	 */
	public int getSize();
}
