/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.List;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * 待售票池接口
 * @author tianhao
 *
 */
public interface IOnSalePool {
	
	/**
	 * 获取票池位置上的票
	 * @param position
	 * @return
	 */
	public Ticket get(int position ) ;
	
	/**
	 * 获取并替换票池上的票
	 * @param position
	 * @param ticket
	 * @return
	 */
	public Ticket replace(int position ,Ticket ticket);
	
	/**
	 * 增加票，将票增加在最后
	 * 仅在初始化时使用
	 * @param position
	 * @param ticket
	 * @return
	 */
	public boolean add(Ticket ticket);
	
	/**
	 * 批量增加票，将票依次增加在最后
	 * 仅在初始化时使用
	 * @param tickets
	 * @return
	 */
	public List<TTicketImported> addAll(List<TTicketImported> tickets);
	
	
	
	/**
	 * 清空票池，
	 * @return
	 */
	public boolean clear();

}
