package com.cslc.eils.gameControl.entity;

import java.util.Date;

/**
 * TTicketTransaction entity. @author MyEclipse Persistence Tools
 */

public class TTicketTransaction implements java.io.Serializable {

	// Fields

	private String transactionId;
	private Integer gameId;
	private Integer betValue;
	private long groupSn;
	private Integer step;
	private String inPut;
	private String rngs;
	private long serialNumber;
	private String soldTicketSn;
	private String replaceTicketSn;
	private String ticketContent;
	private Date soldTime;
	private boolean updated;
	private Boolean endFlag;
	private Long prize;
	private Integer prizeLevel;
	private boolean payFlag;


	// Constructors

	/** default constructor */
	public TTicketTransaction() {
	}

	/** minimal constructor */
	public TTicketTransaction(String transactionId, Integer gameId,
			Integer betValue, Integer step, String rngs, long serialNumber,
			String soldTicketSn, String ticketContent, Date soldTime,
			boolean updated) {
		this.transactionId = transactionId;
		this.gameId = gameId;
		this.betValue = betValue;
		this.step = step;
		this.rngs = rngs;
		this.serialNumber = serialNumber;
		this.soldTicketSn = soldTicketSn;
		this.ticketContent = ticketContent;
		this.soldTime = soldTime;
		this.updated = updated;
	}

	/** full constructor */
	public TTicketTransaction(String transactionId, Integer gameId,
			Integer betValue, Integer step, String inPut, String rngs,
			long serialNumber, String soldTicketSn, String replaceTicketSn,
			String ticketContent, Date soldTime, boolean updated, String outPut) {
		this.transactionId = transactionId;
		this.gameId = gameId;
		this.betValue = betValue;
		this.step = step;
		this.inPut = inPut;
		this.rngs = rngs;
		this.serialNumber = serialNumber;
		this.soldTicketSn = soldTicketSn;
		this.replaceTicketSn = replaceTicketSn;
		this.ticketContent = ticketContent;
		this.soldTime = soldTime;
		this.updated = updated;
	}

	// Property accessors

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getBetValue() {
		return this.betValue;
	}

	public void setBetValue(Integer betValue) {
		this.betValue = betValue;
	}

	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getInPut() {
		return this.inPut;
	}

	public void setInPut(String inPut) {
		this.inPut = inPut;
	}

	public String getRngs() {
		return this.rngs;
	}

	public void setRngs(String rngs) {
		this.rngs = rngs;
	}

	public long getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSoldTicketSn() {
		return this.soldTicketSn;
	}

	public void setSoldTicketSn(String soldTicketSn) {
		this.soldTicketSn = soldTicketSn;
	}

	public String getReplaceTicketSn() {
		return this.replaceTicketSn;
	}

	public void setReplaceTicketSn(String replaceTicketSn) {
		this.replaceTicketSn = replaceTicketSn;
	}

	public String getTicketContent() {
		return this.ticketContent;
	}

	public void setTicketContent(String ticketContent) {
		this.ticketContent = ticketContent;
	}

	public Date getSoldTime() {
		return this.soldTime;
	}

	public void setSoldTime(Date soldTime) {
		this.soldTime = soldTime;
	}

	public boolean getUpdated() {
		return this.updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public Boolean getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(Boolean endFlag) {
		this.endFlag = endFlag;
	}

	public Long getPrize() {
		return prize;
	}

	public void setPrize(Long prize) {
		this.prize = prize;
	}

	public Integer getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(Integer prizeLevel) {
		this.prizeLevel = prizeLevel;
	}

	public long getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(long groupSn) {
		this.groupSn = groupSn;
	}

	public boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(boolean payFlag) {
		this.payFlag = payFlag;
	}

	

}