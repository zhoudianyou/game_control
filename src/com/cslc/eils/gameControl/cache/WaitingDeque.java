/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * ∫Û≤π∆±∂‘œÛ
 * @author tianhao
 *
 */
public class WaitingDeque implements IWaitingDeque {
	
	private Deque<Ticket> waitings = new LinkedBlockingDeque<Ticket>();

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.cache.IWaitingDeque#addFirst(com.cslc.eils.gameControl.pojo.Ticket)
	 */
	@Override
	public boolean addFirst(Ticket ticket) {
		 waitings.addFirst(ticket);
		 return true;
	}

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.cache.IWaitingDeque#addLast(com.cslc.eils.gameControl.pojo.Ticket)
	 */
	@Override
	public boolean addLast(Ticket ticket) {
		waitings.addLast(ticket);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.cache.IWaitingDeque#removeFirst(com.cslc.eils.gameControl.pojo.Ticket)
	 */
	@Override
	public Ticket removeFirst() {
		return waitings.removeFirst();
	}
	
	public void addAll(List<TTicketImported> lave) {
		Iterator<TTicketImported> iterator = lave.iterator();
		while (iterator.hasNext()){
			TTicketImported tTicketImported = iterator.next();
			Ticket ticket = new Ticket(tTicketImported.getTicketSn(),tTicketImported.getTicketContent());
			this.addLast(ticket);
		}
	}
	
	@Override
	public boolean clear() {
		waitings.clear();
		return true;
	}
	
	@Override
	public int getSize() {
		return waitings.size();
	}

	public Deque<Ticket> getWaitings() {
		return waitings;
	}

	public void setWaitings(Deque<Ticket> waitings) {
		this.waitings = waitings;
	}

	

	

	


}
