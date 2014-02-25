/**
 * 
 */
package com.cslc.eils.gameControl.dto;

import java.io.Serializable;

/**
 * @author tianhao
 *
 */
public class TicketPoolInfo  implements Serializable{
	/**/
	private static final long serialVersionUID = 1L;
	/*票池名*/
	private String poolName;
	
	private String gameName;
	
	private int gameId;
	private int value;
	/*票池大小*/
	private int poolSize ;
	/*票池实际票数*/
	private int onSaleSize ;
	/*票池候补队列大小*/
	private int waitingSize;
	
	public TicketPoolInfo(String poolName, int poolSize, int onSaleSize,
			int waitingSize) {
		this.poolName = poolName;
		this.poolSize = poolSize;
		this.onSaleSize = onSaleSize;
		this.waitingSize = waitingSize;
	}
	
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public int getPoolSize() {
		return poolSize;
	}
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	public int getOnSaleSize() {
		return onSaleSize;
	}
	public void setOnSaleSize(int onSaleSize) {
		this.onSaleSize = onSaleSize;
	}
	public int getWaitingSize() {
		return waitingSize;
	}
	public void setWaitingSize(int waitingSize) {
		this.waitingSize = waitingSize;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}
