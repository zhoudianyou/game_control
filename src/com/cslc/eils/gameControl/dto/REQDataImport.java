package com.cslc.eils.gameControl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class REQDataImport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**����id*/
	private int requesId;
	/**��������*/
	private int reqType;
	/**����ʱ��*/
	private String requestTime;
	
	/**�赼�����ݵĽ���*/
	private List<Group>groupList = new ArrayList<Group>();
	
	//getter and setter
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
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
