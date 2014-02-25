package com.cslc.eils.gameControl.entity;

import java.util.Date;


/**
 * TInfoManager entity. @author MyEclipse Persistence Tools
 */

public class TInfoManager  implements java.io.Serializable {


    // Fields    

     private Integer managerId;
     private String name;
     private String password;
     private Integer role;
     private Date addTime;
     private Date delTime;


    // Constructors

    /** default constructor */
    public TInfoManager() {
    }

	/** minimal constructor */
    public TInfoManager(Integer managerId, String name, String password, Integer role, Date addTime) {
        this.managerId = managerId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.addTime = addTime;
    }
    
    /** full constructor */
    public TInfoManager(Integer managerId, String name, String password, Integer role, Date addTime, Date delTime) {
        this.managerId = managerId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.addTime = addTime;
        this.delTime = delTime;
    }

   
    // Property accessors

    public Integer getManagerId() {
        return this.managerId;
    }
    
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return this.role;
    }
    
    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getDelTime() {
        return this.delTime;
    }
    
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }
   








}