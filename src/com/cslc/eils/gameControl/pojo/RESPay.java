/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class RESPay {
		//票sn
		private String ticketSn;
		//实兑金额
		private int prize;
		//兑奖时间
		private String payTime;
		//响应码
		private int errCode;
		//响应码描述
		private String errDesc;
		public String getTicketSn() {
			return ticketSn;
		}
		public void setTicketSn(String ticketSn) {
			this.ticketSn = ticketSn;
		}
		public int getPrize() {
			return prize;
		}
		public void setPrize(int prize) {
			this.prize = prize;
		}
		public String getPayTime() {
			return payTime;
		}
		public void setPayTime(String payTime) {
			this.payTime = payTime;
		}
		public int getErrCode() {
			return errCode;
		}
		public void setErrCode(int errCode) {
			this.errCode = errCode;
		}
		public String getErrDesc() {
			return errDesc;
		}
		public void setErrDesc(String errDesc) {
			this.errDesc = errDesc;
		}
		
}
