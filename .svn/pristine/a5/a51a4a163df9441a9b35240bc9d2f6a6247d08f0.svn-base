/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * 票池管理对象
 * @author tianhao
 *
 */
public class TicketPoolManager implements ITicketPoolManager {
	
	private static final Log log = LogFactory.getLog(TicketPoolManager.class);
	//售票池
	private OnSalePool onSalePool ;
	//票池锁
	private Set<Integer> poolLocks;
	//补票队列
	private WaitingDeque waitingDeque;
	//票池名称
	private String poolName ;
	
	public TicketPoolManager (int poolSize ,String poolName){
		this.setPoolName(poolName);
		log.info("新建票池，票池名为："+poolName+"，票池大小为："+poolSize+".");
		onSalePool = new OnSalePool(poolSize , poolName);
		waitingDeque = new WaitingDeque();
		poolLocks = new HashSet<Integer>();
	}


	public Ticket replace(int position,Ticket replaceTicket) {
		//取待售票，并用补票替换待售票，
		return onSalePool.replace(position, replaceTicket);
	}
	
	public Ticket getReplaceTicket(int position) {
		//取候补票
		return waitingDeque.removeFirst();
	}
	
	@Override
	public void rollbackTrans(int position, Ticket ticket) {
		log.info("回滚票池，票池位置为："+position+",回滚票sn为："+ticket.getLotterySn());
		//取补票，并用待售票替换补票，
		Ticket rollbackWaiting = onSalePool.replace(position, ticket);
		//将补票返回补票队列
		waitingDeque.addFirst(rollbackWaiting);
		
	}

	public synchronized boolean locksPosition(int position){
		return poolLocks.add(position);
	}

	public synchronized boolean releasePosition(int position) {
		return poolLocks.remove(position);
	}

	@Override
	public boolean contains(int position) {
		return poolLocks.contains(position);
	}
	
	@Override
	public boolean clear() {
		onSalePool.clear();
		waitingDeque.clear();
		poolLocks.clear();
		return true;
	}


	@Override
	public void addTickets(ArrayList<TTicketImported> tickets) {
		//先向待售票池中添加
		List<TTicketImported> lave = onSalePool.addAll(tickets);
		if(lave!=null && lave.size()>0){
			log.info("将剩余的"+lave.size()+"张票加入补票队列中");
			waitingDeque.addAll(lave);
		}
	}
	

	public String getPoolName() {
		return poolName;
	}

	@Override
	public OnSalePool getOnSalePool() {
		return onSalePool;
	}

	@Override
	public void addTicket2waiting(TTicketImported tTicketImported) {
		Ticket ticket = new Ticket(tTicketImported.getTicketSn(),tTicketImported.getTicketContent());
		waitingDeque.addLast(ticket);
	}

	@Override
	public int getSize4Waiting() {
		return waitingDeque.getSize();
	}
	
	@Override
	public boolean isLacking() {
		//预警条件暂定为：候补票队列大小小于票池的1/2时预警
		int size = this.getSize4Waiting();
		int poolSize = onSalePool.getPoolSize();
		if(2*size<poolSize){
			return true;
		}
		return false;
	}
	
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}


	


	

	
	
}
