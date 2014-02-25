package com.cslc.eils.gameControl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RESDataImport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**����id*/
	private int requesId;
	/**��Ӧ����*/
	private int reqType;
	/**��Ӧʱ��*/
	private String requestTime;
	/**������*/
	private List<GroupRes> resList = new ArrayList<GroupRes>();
	
	//getter and setter 
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
	public List<GroupRes> getResList() {
		return resList;
	}
	public void setResList(List<GroupRes> resList) {
		this.resList = resList;
	}


}
