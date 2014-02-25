package com.cslc.eils.gameControl.pojo;

import java.io.Serializable;

public class TicketDetailInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*票在奖组票中的位置*/
	private int lotteryId;
	
	/*票唯一标示*/
	private String lotterySn;
	
	/*票面内容*/
	private String ticketContent = "";
	
	//getter and setter

	public String getLotterySn() {
		return lotterySn;
	}
	public int getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
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
