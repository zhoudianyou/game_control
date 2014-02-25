package com.cslc.eils.gameControl.dto;

import java.io.Serializable;

public class Group implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* ÓÎÏ·±àºÅ*/
	private int gameId;
	/*½±×éË³ÐòºÅ*/
	private int groupId;
	/* ½±×é±àºÅ*/
	private Long groupSn;	
	/* ½±×éÃæÖµ*/
	private int betValue;
	
	//getter and setter
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public Long getGroupSn() {
		return groupSn;
	}
	public void setGroupSn(Long groupSn) {
		this.groupSn = groupSn;
	}
	public int getBetValue() {
		return betValue;
	}
	public void setBetValue(int betValue) {
		this.betValue = betValue;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}


}
