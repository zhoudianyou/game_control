package com.cslc.eils.gameControl.pojo;

import java.io.Serializable;

public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	/*票唯一标示*/
	private String lotterySn;
	
	/*票内容*/
	private String ticketContent = "";
	
	public Ticket (){}
	
	/**
	 * @param lotteryId
	 * @param lotterySn
	 * @param ticketContent
	 */
	public Ticket (String lotterySn ,String ticketContent){
		this.lotterySn = lotterySn;
		this.ticketContent = ticketContent;
	}
	
	//getter and setter

	public String getLotterySn() {
		return lotterySn;
	}
	
	public void setLotterySn(String lotterySn) {
		this.lotterySn = lotterySn;
	}
	public String getTicketContent() {
		return ticketContent;
	}
	public void setTicketContent(String ticketContent) {
		this.ticketContent = ticketContent;
	}
}
