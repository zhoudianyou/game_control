/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class TransQueryMsg {
	//请求对象
	private REQQuery reqQuery;
	
	//响应对象
	private RESBet resBet;

	public REQQuery getReqQuery() {
		return reqQuery;
	}

	public void setReqQuery(REQQuery reqQuery) {
		this.reqQuery = reqQuery;
	}

	public RESBet getResBet() {
		return resBet;
	}

	public void setResBet(RESBet resBet) {
		this.resBet = resBet;
	}

}
