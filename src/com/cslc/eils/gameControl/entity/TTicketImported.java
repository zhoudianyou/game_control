package com.cslc.eils.gameControl.entity;

import java.util.Date;


/**
 * TTicketImported entity. @author MyEclipse Persistence Tools
 */

public class TTicketImported  implements java.io.Serializable {


    // Fields    

     private String ticketSn;
     private Integer gameId;
     private long groupSn;
     private Integer betValue;
     private String TicketContent;
     private String summary;
     private Integer ticketStatus;
     private Date importTime;
     private Date soldTime;
     private Date invalidTime;


    // Constructors

    /** default constructor */
    public TTicketImported() {
    }

	/** minimal constructor */
    public TTicketImported(String ticketSn, Integer gameId, long groupSn, Integer betValue, String TicketContent, String summary, Integer ticketStatus, Date importTime) {
        this.ticketSn = ticketSn;
        this.gameId = gameId;
        this.groupSn = groupSn;
        this.betValue = betValue;
        this.TicketContent = TicketContent;
        this.summary = summary;
        this.ticketStatus = ticketStatus;
        this.importTime = importTime;
    }
    
    /** full constructor */
    public TTicketImported(String ticketSn, Integer gameId, long groupSn, Integer betValue, String TicketContent, String summary, Integer ticketStatus, Date importTime, Date soldTime, Date invalidTime) {
        this.ticketSn = ticketSn;
        this.gameId = gameId;
        this.groupSn = groupSn;
        this.betValue = betValue;
        this.TicketContent = TicketContent;
        this.summary = summary;
        this.ticketStatus = ticketStatus;
        this.importTime = importTime;
        this.soldTime = soldTime;
        this.invalidTime = invalidTime;
    }

   
    // Property accessors

    public String getTicketSn() {
        return this.ticketSn;
    }
    
    public void setTicketSn(String ticketSn) {
        this.ticketSn = ticketSn;
    }

    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public long getGroupSn() {
        return this.groupSn;
    }
    
    public void setGroupSn(long groupSn) {
        this.groupSn = groupSn;
    }

    public Integer getBetValue() {
        return this.betValue;
    }
    
    public void setBetValue(Integer betValue) {
        this.betValue = betValue;
    }

   

    public String getTicketContent() {
		return TicketContent;
	}

	public void setTicketContent(String ticketContent) {
		TicketContent = ticketContent;
	}

	public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTicketStatus() {
        return this.ticketStatus;
    }
    
    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Date getImportTime() {
        return this.importTime;
    }
    
    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public Date getSoldTime() {
        return this.soldTime;
    }
    
    public void setSoldTime(Date soldTime) {
        this.soldTime = soldTime;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }
    
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }
   








}