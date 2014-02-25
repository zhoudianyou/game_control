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
			log.info("������Ϸ���ۣ���ϷidΪ��"+req.getGameId());
			// �ж���Ϸ�Ƿ����
			if (gameConfig.contains(req.getGameId())) {
				Game gameLoc = gameConfig.getGame(req.getGameId());
				TInfoGame gameDb = gameInfoDao.findByGameId(req.getGameId());
				// ��Ϸ״̬Ϊ�Ѵ�����������ͣ
				if (gameDb.getGameStatus() == Constants.GAME_STATUS_CREATED || gameDb
						.getGameStatus() == Constants.GAME_STATUS_PAUSING) {
					// 1.������Ϸ�����ݿ��е�״̬
					gameInfoDao.updateStatus(req.getGameId(),
							Constants.GAME_STATUS_SELLING);
					// 2.д����־
					sysPeriodDao.insertLogAtOpen(req.getGameId());
					// 3.������Ϸ���ڴ��к������ļ���Ϣ
					gameLoc.setStatus(Constants.GAME_STATUS_SELLING);
					gameConfig.putGame(req.getGameId(), gameLoc);
					// 4.������Ҫ����Ʊ��
					if (gameLoc.getGameType() == Constants.GAME_TYPE_TRADITIONAL) {
						// ���y���M���[����Ҫ���dƱ��
					//	((TicketPoolService) InitSystem.getBean("ticketPoolService")).addPoolsByGame(req.getGameId());
					}
					// 5.���÷�����Ϣ
					res.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));

				} else {
					log.error("��Ϸ״̬�����޷���ʼ���ۣ�");
					res.setErrCode(SysErrorCode.OPEN_GAME_FAILURE);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.OPEN_GAME_FAILURE));
				}
			} else {
				// ��������δ�ҵ�����Ϸ
				log.warn("������Ϸ����ʧ�ܣ�ϵͳ��֧�ִ���Ϸ-��������δ�ҵ�����Ϸ����ϷidΪ��"+req.getGameId());
				res.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
				res.setErrDesc(SysErrorCode
						.getMessageByErrCode(SysErrorCode.GAME_NOT_SUPPORTED));
			}
		} catch (Exception e) {
			//������Ϸʧ��
			res.setErrCode(SysErrorCode.OPEN_GAME_FAILURE);
			res.setErrDesc(SysErrorCode
					.getMessageByErrCode(SysErrorCode.OPEN_GAME_FAILURE));
			e.printStackTrace();
		}
		// ���÷�����Ϣ������Ϣ
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
			log.info("��ͣ��Ϸ���ۣ���ϷidΪ��"+req.getGameId());
			// �ж���Ϸ�Ƿ����
			if (gameConfig.contains(req.getGameId())) {
				Game gameLoc = gameConfig.getGame(req.getGameId());
				TInfoGame gameDb = gameInfoDao.findByGameId(req.getGameId());
				// ��Ϸ״̬Ϊ��������
				if (gameDb.getGameStatus() == Constants.GAME_STATUS_SELLING) {
					// 1.������Ϸ�����ݿ��е�״̬
					gameInfoDao.updateStatus(req.getGameId(),
							Constants.GAME_STATUS_PAUSING);
					// 2.������־
					sysPeriodDao.updateLogAtClose(req.getGameId());
					// 3.������Ϸ���ڴ��к������ļ���Ϣ
					gameLoc.setStatus(Constants.GAME_STATUS_PAUSING);
					gameConfig.putGame(req.getGameId(), gameLoc);
					// 4.������Ҫ�Ƴ�Ʊ��
					if (gameLoc.getGameType() == Constants.GAME_TYPE_TRADITIONAL) {
						// ���y���M���[����Ҫ�Ƴ�Ʊ��
//						((TicketPoolService) InitSystem.getBean("ticketPoolService")).delPoolsByGame(req.getGameId());
					}
					// 5.���÷�����Ϣ
					res.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
				} else {
					log.error("��Ϸ״̬�����޷���ͣ���ۣ�");
					res.setErrCode(SysErrorCode.CLOSE_GAME_FAILURE);
					res.setErrDesc(SysErrorCode
							.getMessageByErrCode(SysErrorCode.CLOSE_GAME_FAILURE));
				}
			} else {
				// ��������δ�ҵ�����Ϸ
				log.warn("��ͣ��Ϸ����ʧ�ܣ�ϵͳ��֧�ִ���Ϸ-��������δ�ҵ�����Ϸ����ϷidΪ��"+req.getGameId());
				res.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
				res.setErrDesc(SysErrorCode
						.getMessageByErrCode(SysErrorCode.GAME_NOT_SUPPORTED));
			}
		} catch (Exception e) {
			//��ͣ��Ϸ����ʧ��
			res.setErrCode(SysErrorCode.CLOSE_GAME_FAILURE);
			res.setErrDesc(SysErrorCode
					.getMessageByErrCode(SysErrorCode.CLOSE_GAME_FAILURE));
			e.printStackTrace();
		}

		// ���÷�����Ϣ������Ϣ
		res.setRequestTime(DateUtil.getDate());
		res.setRequesId(req.getRequesId());
		res.setGameId(req.getGameId());
		res.setReqType(Constants.MESSAGE_TYPE_RESPONSE_STOP_GAME);
		// ���ʹ�����Ϣ
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
