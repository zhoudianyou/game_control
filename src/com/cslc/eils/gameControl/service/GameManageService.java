/**
 * 
 */
package com.cslc.eils.gameControl.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cslc.eils.gameControl.dao.GameInfoDao;
import com.cslc.eils.gameControl.dao.SysPeriodDao;
import com.cslc.eils.gameControl.dto.REQGameControl;
import com.cslc.eils.gameControl.dto.RESGameControl;
import com.cslc.eils.gameControl.entity.TInfoGame;
import com.cslc.eils.gameControl.netInterface.jms.ISender;
import com.cslc.eils.gameControl.pojo.Game;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.GameConfig;
import com.cslc.eils.gameControl.util.SysErrorCode;

/**
 * @author tianhao
 *
 */
public class GameManageService {
	private static final Log log = LogFactory.getLog(GameManageService.class);
	private ISender msgSender;
	private GameConfig gameConfig ;
	private GameInfoDao gameInfoDao;
	private SysPeriodDao  sysPeriodDao;
	
	
	@Transactional(rollbackFor=Exception.class ,propagation=Propagation.REQUIRED)
	public void gameOpen(REQGameControl req) {
		RESGameControl res = new RESGameControl();
		try {
			log.info("开启游戏销售，游戏id为："+req.getGameId());
			// 判断游戏是否存在
			if (gameConfig.contains(req.getGameId())) {
				Game gameLoc = gameConfig.getGame(req.getGameId());
				TInfoGame gameDb = gameInfoDao.findByGameId(req.getGameId());
				// 游戏状态为已创建或者已暂停
				if (gameDb.getGameStatus() == Constants.GAME_STATUS_CREATED || gameDb
						.getGameStatus() == Constants.GAME_STATUS_PAUSING) {
					// 1.更新游戏在数据库中的状态
					gameInfoDao.updateStatus(req.getGameId(),
							Constants.GAME_STATUS_SELLING);
					// 2.写入日志
					sysPeriodDao.insertLogAtOpen(req.getGameId());
					// 3.更新游戏在内存中和配置文件信息
					gameLoc.setStatus(Constants.GAME_STATUS_SELLING);
					gameConfig.putGame(req.getGameId(), gameLoc);
					// 4.根据需要加载票池
					if (gameLoc.getGameType() == Constants.GAME_TYPE_TRADITIONAL) {
						// 傳統獎組型遊戲需要加載票池
					//	((TicketPoolService) InitSystem.getBean("ticketPoolService")).addPoolsByGame(req.getGameId());
					}
					// 5.设置返回信息
					res.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));

				} else {
					log.error("游戏状态错误，无法开始销售！");
					res.setErrCode(SysErrorCode.OPEN_GAME_FAILURE);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.OPEN_GAME_FAILURE));
				}
			} else {
				// 本地配置未找到此游戏
				log.warn("开启游戏销售失败！系统不支持此游戏-本地配置未找到此游戏，游戏id为："+req.getGameId());
				res.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
				res.setErrDesc(SysErrorCode
						.getMessageByErrCode(SysErrorCode.GAME_NOT_SUPPORTED));
			}
		} catch (Exception e) {
			//开启游戏失败
			res.setErrCode(SysErrorCode.OPEN_GAME_FAILURE);
			res.setErrDesc(SysErrorCode
					.getMessageByErrCode(SysErrorCode.OPEN_GAME_FAILURE));
			e.printStackTrace();
		}
		// 设置返回信息基本信息
		res.setReqType(Constants.MESSAGE_TYPE_RESPONSE_START_GAME);
		res.setRequesId(req.getRequesId());
		res.setGameId(req.getGameId());
		res.setRequestTime(DateUtil.getDate());
		msgSender.sendMessage(res, Constants.MESSAGE_TYPE_RESPONSE_START_GAME);
	}
	
	@Transactional(rollbackFor=Exception.class ,propagation=Propagation.REQUIRED)
	public void gameClose(REQGameControl req) {
		RESGameControl res = new RESGameControl();
		try {
			log.info("暂停游戏销售，游戏id为："+req.getGameId());
			// 判断游戏是否存在
			if (gameConfig.contains(req.getGameId())) {
				Game gameLoc = gameConfig.getGame(req.getGameId());
				TInfoGame gameDb = gameInfoDao.findByGameId(req.getGameId());
				// 游戏状态为正在销售
				if (gameDb.getGameStatus() == Constants.GAME_STATUS_SELLING) {
					// 1.更新游戏在数据库中的状态
					gameInfoDao.updateStatus(req.getGameId(),
							Constants.GAME_STATUS_PAUSING);
					// 2.更新日志
					sysPeriodDao.updateLogAtClose(req.getGameId());
					// 3.更新游戏在内存中和配置文件信息
					gameLoc.setStatus(Constants.GAME_STATUS_PAUSING);
					gameConfig.putGame(req.getGameId(), gameLoc);
					// 4.根据需要移除票池
					if (gameLoc.getGameType() == Constants.GAME_TYPE_TRADITIONAL) {
						// 傳統獎組型遊戲需要移除票池
//						((TicketPoolService) InitSystem.getBean("ticketPoolService")).delPoolsByGame(req.getGameId());
					}
					// 5.设置返回信息
					res.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
				} else {
					log.error("游戏状态错误，无法暂停销售！");
					res.setErrCode(SysErrorCode.CLOSE_GAME_FAILURE);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.CLOSE_GAME_FAILURE));
				}
			} else {
				// 本地配置未找到此游戏
				log.warn("暂停游戏销售失败！系统不支持此游戏-本地配置未找到此游戏，游戏id为："+req.getGameId());
				res.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
				res.setErrDesc(SysErrorCode
						.getMessageByErrCode(SysErrorCode.GAME_NOT_SUPPORTED));
			}
		} catch (Exception e) {
			//暂停游戏销售失败
			res.setErrCode(SysErrorCode.CLOSE_GAME_FAILURE);
			res.setErrDesc(SysErrorCode
					.getMessageByErrCode(SysErrorCode.CLOSE_GAME_FAILURE));
			e.printStackTrace();
		}

		// 设置返回信息基本信息
		res.setRequestTime(DateUtil.getDate());
		res.setRequesId(req.getRequesId());
		res.setGameId(req.getGameId());
		res.setReqType(Constants.MESSAGE_TYPE_RESPONSE_STOP_GAME);
		// 发送处理消息
	//	msgSender.sendMessage(res, Constants.MESSAGE_TYPE_RESPONSE_STOP_GAME);

	}

	//getter and setter
	public GameConfig getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

	public ISender getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(ISender msgSender) {
		this.msgSender = msgSender;
	}

	public GameInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(GameInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}

	public SysPeriodDao getSysPeriodDao() {
		return sysPeriodDao;
	}

	public void setSysPeriodDao(SysPeriodDao sysPeriodDao) {
		this.sysPeriodDao = sysPeriodDao;
	}
	
}
