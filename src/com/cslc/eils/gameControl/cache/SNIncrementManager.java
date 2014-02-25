/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dao.TransactionHistoryDao;
import com.cslc.eils.gameControl.pojo.Game;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.GameConfig;


/**
 * @author tianhao
 *
 */
public class SNIncrementManager {
	
	private GameConfig gameConfig ;
	
	//记录交易流水接口
	private TransactionHistoryDao transactionHistoryDao;
	
	/** SerialNumber 自增器*/
	public final static Map<Integer,Long> map = new ConcurrentHashMap<Integer,Long>();

	
	/**
	 * @param gameId
	 * @return
	 */
	public synchronized long getSerialNumber(int gameId){
		long serialNumber = map.get(gameId)+1;
		map.put(gameId, serialNumber);
		return serialNumber;
	}
	
	public void init() {
		Map<Integer,Game> gameMap = gameConfig.getMap();
		// 遍历本地配置所支持的游戏
		Set<Integer> gameIdSet = gameMap.keySet();
		Iterator<Integer> itGame = gameIdSet.iterator();
		while (itGame.hasNext()) {
			int gameId = (Integer) itGame.next();
			Game game = gameMap.get(gameId);
			if (game.getGameType() != Constants.GAME_TYPE_TRADITIONAL) {
				long SerialNumber = transactionHistoryDao.getCurrentSerialNumber(gameId);
				map.put(gameId, SerialNumber);
			}
		}
	}

	public GameConfig getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

	public TransactionHistoryDao getTransactionHistoryDao() {
		return transactionHistoryDao;
	}

	public void setTransactionHistoryDao(TransactionHistoryDao transactionHistoryDao) {
		this.transactionHistoryDao = transactionHistoryDao;
	}

	
}
