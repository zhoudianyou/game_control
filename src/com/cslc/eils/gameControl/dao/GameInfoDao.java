/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import com.cslc.eils.gameControl.entity.TInfoGame;

/**
 * @author tianhao
 *
 */
public interface GameInfoDao {
	
	/*查询玩法*/
	public TInfoGame findByGameId(int gameId);

	/*判断游戏是否在售*/
	public boolean isOnSale(int gameId);
	
	/*更新遊戲狀態*/
	public int  updateStatus(int gameId, int status);
	
	public  int addNewGame(TInfoGame tInfoGame);
}
