/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class REQPay {
	
	//Ʊsn
	private String ticketSn;
	//�û�id
	private String userId;
	//����ʱ��
	private String requestTime;
	//�н����
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
