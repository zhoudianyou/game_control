/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.cslc.eils.gameControl.dao.GameInfoDao;
import com.cslc.eils.gameControl.entity.TInfoGame;
import com.cslc.eils.gameControl.entity.TInfoGroup;
import com.cslc.eils.gameControl.pojo.TicketPool;
import com.cslc.eils.gameControl.util.Constants;

/**
 * @author tianhao
 * 
 */
public class GameInfoDaoImpl extends HibernateDaoImpl implements GameInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TInfoGame findByGameId(int gameId) {
		return (TInfoGame) find(TInfoGame.class, gameId);
	}

	@Override
	public boolean isOnSale(int gameId) {
		TInfoGame tInfoGame = findByGameId(gameId);
		if (tInfoGame != null
				&& tInfoGame.getGameStatus() == Constants.GAME_STATUS_SELLING)
			return true;
		return false;
	}

	@Override
	public int updateStatus(int gameId, int status) {
		String hql = "update TInfoGame t set gameStatus = ? "+"	where gameId =?";
		Object[] objParam = new Object[]{status,gameId};
		return super.updateByHql(hql, objParam);
	}

	@Override
	public int addNewGame(TInfoGame tInfoGame) {
		return  (Integer) super.insert(tInfoGame);
	}

}
