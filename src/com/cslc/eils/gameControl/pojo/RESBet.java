/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class RESBet {

	
	//������
	private String transactionID;
	//Ʊsn
	private String ticketSn;
	//����ʱ��
	private String saleTime;
	//Ʊ����
	private String outPut;
	//�н����
	private int prize;
	//������ʶ
	private Boolean endFlag;
	//��Ӧ��
	private int errCode;
	//��Ӧ������
	private String errDesc;
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTicketSn() {
		return ticketSn;
	}
	public void setTicketSn(String ticketSn) {
		this.ticketSn = ticketSn;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public String getOutPut() {
		return outPut;
	}
	public void setOutPut(String outPut) {
		this.outPut = outPut;
	}
	public int getPrize() {
		return prize;
	}
	public void setPrize(int prize) {
		this.prize = prize;
	}
	public Boolean getEndFlag() {
		return endFlag;
	}
	public void setEndFlag(Boolean endFlag) {
		this.endFlag = endFlag;
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
