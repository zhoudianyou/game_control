/**
 * 
 */
package com.cslc.eils.gameControl.Factory;

import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * @author tianhao
 *
 */
public class InstanceTicket extends Ticket implements IProduct {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*票唯一标示*/
	private String lotterySn;

	//游戏编号
	private int gameID;
	
	//面值
	private int betValue;
	
	//步骤数
	private int step;
	
	//用户输入
	private String inPut;
	
	/*rngs*/
	private String rngs;
	
	//票内容
	private String outPut;
	
	//中奖金额
	private int prize;
	
	//奖等
	private int prizeLevel;
	
	//结束标识
	private Boolean endFlag;

	public String getRngs() {
		return rngs;
	}

	public void setRngs(String rngs) {
		this.rngs = rngs;
	}

	public Boolean getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(Boolean endFlag) {
		this.endFlag = endFlag;
	}

	public String getLotterySn() {
		return lotterySn;
	}

	public void setLotterySn(String lotterySn) {
		this.lotterySn = lotterySn;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getBetValue() {
		return betValue;
	}

	public void setBetValue(int betValue) {
		this.betValue = betValue;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getInPut() {
		return inPut;
	}

	public void setInPut(String inPut) {
		this.inPut = inPut;
	}

	public String getOutPut() {
		return outPut;
	}

	public void setOutPut(String outPut) {
		this.outPut = outPut;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public int getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(int prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	
	
	

}
