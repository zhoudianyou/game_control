/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class REQBet {


	
	//������
	private String transactionID;
	
	private String userId;
	//���Ա��
	private Boolean retryFlag;
	//��Ϸ���
	private int gameID;
	//��ֵ
	private int betValue;
	//������
	private int step;
	//�û�����
	private String inPut;
	//����ʱ��
	private String requestTime;
	
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public Boolean getRetryFlag() {
		return retryFlag;
	}
	public void setRetryFlag(Boolean retryFlag) {
		this.retryFlag = retryFlag;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getBetValue() {
		return betValue;
	}
	public void setBetValue(int betValue) {
		this.betValue = betValue;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getInPut() {
		return inPut;
	}
	public void setInPut(String inPut) {
		this.inPut = inPut;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	






}
