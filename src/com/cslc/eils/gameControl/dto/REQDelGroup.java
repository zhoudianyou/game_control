/**
 * 
 */
package com.cslc.eils.gameControl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author tianhao
 *
 */
public class REQDelGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**����id*/
	private int requesId;
	/**��Ӧ����*/
	private int reqType;
	/**����ʱ��*/
	private String requestTime;
	/**��ɾ���Ľ���*/
	private List<Group> groupList = new ArrayList<Group>();
	
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
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> resList) {
		this.groupList = resList;
	}
	
	

}
