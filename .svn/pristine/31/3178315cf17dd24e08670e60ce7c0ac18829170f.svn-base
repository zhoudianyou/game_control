package com.cslc.eils.gameControl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TSysImportgroup entity. @author MyEclipse Persistence Tools
 */

public class TSysImportgroup  implements java.io.Serializable {


    // Fields    

     private Integer requestId;
     private Date requestTime;
     private String groupList;
     private Integer status;
     private Set<TSysImportgroupdetail> TSysImportgroupdetails = new HashSet(0);


    // Constructors

    /** default constructor */
    public TSysImportgroup() {
    }

	/** minimal constructor */
    public TSysImportgroup(Integer requestId, Date requestTime, String groupList, Integer status) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.groupList = groupList;
        this.status = status;
    }
    
    /** full constructor */
    public TSysImportgroup(Integer requestId, Date requestTime, String groupList, Integer status, Set TSysImportgroupdetails) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.groupList = groupList;
        this.status = status;
        this.TSysImportgroupdetails = TSysImportgroupdetails;
    }

   
    // Property accessors

    public Integer getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Date getRequestTime() {
        return this.requestTime;
    }
    
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getGroupList() {
        return this.groupList;
    }
    
    public void setGroupList(String groupList) {
        this.groupList = groupList;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Set<TSysImportgroupdetail> getTSysImportgroupdetails() {
		return TSysImportgroupdetails;
	}

	public void setTSysImportgroupdetails(
			Set<TSysImportgroupdetail> tSysImportgroupdetails) {
		TSysImportgroupdetails = tSysImportgroupdetails;
	}


   








}