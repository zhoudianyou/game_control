/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class TransQueryMsg {
	//�������
	private REQQuery reqQuery;
	
	//��Ӧ����
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
