package com.cslc.eils.gameControl.entity;

import java.util.Date;


/**
 * TInfoGroup entity. @author MyEclipse Persistence Tools
 */

public class TInfoGroup  implements java.io.Serializable {


    // Fields    

     private long groupSn;
     private Integer gameId;
     private Integer betValue;
     private Integer groupId;
     private Integer groupStatus;
     private Date createTime;
     private Date startSaleTime;
     private Date delTime;
     private Date soldOutTime;
     private String secretKey;


    // Constructors

    /** default constructor */
    public TInfoGroup() {
    }

	/** minimal constructor */
    public TInfoGroup(long groupSn, Integer gameId, Integer betValue, Integer groupId, Integer groupStatus, Date createTime) {
        this.groupSn = groupSn;
        this.gameId = gameId;
        this.betValue = betValue;
        this.groupId = groupId;
        this.groupStatus = groupStatus;
        this.createTime = createTime;
    }
    
    /** full constructor */
    public TInfoGroup(long groupSn, Integer gameId, Integer betValue, Integer groupId, Integer groupStatus, Date createTime, Date startSaleTime, Date delTime, Date soldOutTime, String secretKey) {
        this.groupSn = groupSn;
        this.gameId = gameId;
        this.betValue = betValue;
        this.groupId = groupId;
        this.groupStatus = groupStatus;
        this.createTime = createTime;
        this.startSaleTime = startSaleTime;
        this.delTime = delTime;
        this.soldOutTime = soldOutTime;
        this.secretKey = secretKey;
    }

   
    // Property accessors

    public long getGroupSn() {
        return this.groupSn;
    }
    
    public void setGroupSn(long groupSn) {
        this.groupSn = groupSn;
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

    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupStatus() {
        return this.groupStatus;
    }
    
    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartSaleTime() {
        return this.startSaleTime;
    }
    
    public void setStartSaleTime(Date startSaleTime) {
        this.startSaleTime = startSaleTime;
    }

    public Date getDelTime() {
        return this.delTime;
    }
    
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Date getSoldOutTime() {
        return this.soldOutTime;
    }
    
    public void setSoldOutTime(Date soldOutTime) {
        this.soldOutTime = soldOutTime;
    }

    public String getSecretKey() {
        return this.secretKey;
    }
    
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
   








}