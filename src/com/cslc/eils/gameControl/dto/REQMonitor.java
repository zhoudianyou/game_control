/**
 * 
 */
package com.cslc.eils.gameControl.dto;

import java.io.Serializable;

/**
 * @author tianhao
 *
 */
public class REQMonitor implements Serializable {


	/**/
	private static final long serialVersionUID = 1L;
	/**��������*/
	private int reqType;
	/**����ʱ��*/
	private String requestTime;
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
