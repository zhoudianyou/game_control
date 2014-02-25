/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class REQPay {
	
	//票sn
	private String ticketSn;
	//用户id
	private String userId;
	//请求时间
	private String requestTime;
	//中奖金额
	private int prize;
	public String getTicketSn() {
		return ticketSn;
	}
	public void setTicketSn(String ticketSn) {
		this.ticketSn = ticketSn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public int getPrize() {
		return prize;
	}
	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	
}
