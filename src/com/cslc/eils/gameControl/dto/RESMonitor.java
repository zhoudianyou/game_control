/**
 * 
 */
package com.cslc.eils.gameControl.dto;

import java.io.Serializable;
import java.util.List;


/**
 * @author tianhao
 *
 */
public class RESMonitor implements Serializable {

	/**/
	private static final long serialVersionUID = 1L;
	/*��Ӧ����*/
	private int reqType;
	/*��Ӧʱ��*/
	private String responseTime;
	/*��Ӧ����*/
	private List<TicketPoolInfo> poolsInfo;
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
	public List<TicketPoolInfo> getPoolsInfo() {
		return poolsInfo;
	}
	public void setPoolsInfo(List<TicketPoolInfo> poolsInfo) {
		this.poolsInfo = poolsInfo;
	}
	
}
