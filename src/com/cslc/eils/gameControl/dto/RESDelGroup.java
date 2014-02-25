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
public class RESDelGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**请求id*/
	private int requesId;
	/**相应类型*/
	private int reqType;
	/**响应时间*/
	private String responseTime;
	/**已处理的奖组*/
	private List<GroupRes> groupList = new ArrayList<GroupRes>();
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
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public List<GroupRes> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GroupRes> groupList) {
		this.groupList = groupList;
	}
	
	
}
