/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.ArrayList;
import java.util.List;

import org.mortbay.log.Log;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * 待售票池对象
 * @author tianhao
 *
 */
public class OnSalePool implements IOnSalePool {
	
	private int  poolSize ;
	
	private String poolName ;
	
	private Ticket[]  onSales ;
	
	//票池中票总张数
	private int size;
	
	/**
	 * @param poolSize
	 */
	public OnSalePool (int poolSize ,String poolName ){
		this.poolSize = poolSize;
		this.poolName = poolName;
		this.size = 0;
		onSales = new Ticket[poolSize] ;
	}

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.cache.ITicketPool#getTicket(int)
	 */
	@Override
	public Ticket get(int position) {
		if(position < poolSize){
			return onSales[position];
		}
		// TODO throw exp
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.cache.ITicketPool#remove(int, com.cslc.eils.gameControl.pojo.Ticket)
	 */
	@Override
	public Ticket replace(int position, Ticket ticket) {
		if(position < poolSize){
			Ticket t = onSales[position];
			onSales[position] = ticket;
			 return t;
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean add(Ticket ticket) {
		if(size < poolSize){
			onSales[++size]=ticket;
			return true;
		}
		return false;
	}
	
	@Override
	public List<TTicketImported> addAll(List<TTicketImported> tickets) {
		
		int startIndex = size;
		// 获取需添加的新票数
		int toIndex = poolSize - startIndex;
		Log.info("待售票池需添加的票数为：" + toIndex);
		if (toIndex <= tickets.size()) {
			// 票足够填满票池
			// 批量取出要添加的票
			List<TTicketImported> adds = tickets.subList(0, toIndex);
			List<TTicketImported> lave = tickets.subList(toIndex, tickets.size());;
			// 将新票批量添加在最后
			for (int i = 0; i < adds.size(); i++) {
				TTicketImported tTicketImported =  adds.get(i);
				Ticket ticket = new Ticket(tTicketImported.getTicketSn(),tTicketImported.getTicketContent());
				onSales[size++] = ticket;
			}
			return lave;
		} else {
			// 票较少，无法填满票池
			// 批量取出要添加的票
			List<TTicketImported> adds = tickets.subList(0, tickets.size());
			List<TTicketImported> lave = null;
			// 将新票批量添加在最后
			for (int i = 0; i < adds.size(); i++) {
				TTicketImported tTicketImported =  adds.get(i);
				Ticket ticket = new Ticket(tTicketImported.getTicketSn(),tTicketImported.getTicketContent());
				onSales[size++] = ticket;
			}
			return lave;
		}
	}

	

	
	
	@Override
	public boolean clear() {
		onSales = new Ticket[poolSize] ;
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private ArrayList<Ticket> extracted(List<?> tickets) {
		return (ArrayList<Ticket>) tickets;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public Ticket[] getOnSales() {
		return onSales;
	}

	public void setOnSales(Ticket[] onSales) {
		this.onSales = onSales;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
