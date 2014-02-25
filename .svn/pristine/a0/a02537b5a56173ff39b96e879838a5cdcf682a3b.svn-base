/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

import com.cslc.eils.gameControl.util.SystemUtil;

/**
 * 票池对象
 * @author tianhao
 *
 */
public class TicketPool {
	
	//游戏id
	public int gameId;
	//面值
	public int value;
	//票池名称
	public String poolName;
	
	/**不允许空构造*/
	
	/**
	 * 
	 * @param gameId
	 * @param value
	 */
	public TicketPool(int gameId,int value){
		this.gameId = gameId;
		this.value = value;
		this.poolName = SystemUtil.createPoolName(gameId,value);
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

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	
}
