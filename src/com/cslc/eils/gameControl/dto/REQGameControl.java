package com.cslc.eils.gameControl.dto;

import java.io.Serializable;

public class REQGameControl implements Serializable {
	/* */
	private static final long serialVersionUID = 1L;
	/**��Ϸ���*/
	private int gameId;
	/**����id*/
	private int requesId;
	/**��Ӧ����*/
	private int reqType;
	/**����ʱ��*/
	private String requestTime;
	
	//getter and setter
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getRequesId() {
		return requesId;
	}
	public void setRequesId(int requesId) {
		this.requesId = requesId;
	}
	public int getReqType() {
		return reqType;
	}
	public void setReqType(int reqType) {
		this.reqType = reqType;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	
	
}
