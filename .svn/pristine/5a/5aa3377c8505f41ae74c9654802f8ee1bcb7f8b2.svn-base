/**
 * 
 */
package com.cslc.eils.gameControl.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cslc.eils.gameControl.Factory.ITicketFactory;
import com.cslc.eils.gameControl.Factory.InstanceTicket;
import com.cslc.eils.gameControl.Factory.SlotFactory;
import com.cslc.eils.gameControl.cache.ITicketPoolManager;
import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dao.GameInfoDao;
import com.cslc.eils.gameControl.dao.ImportedDao;
import com.cslc.eils.gameControl.dao.TransactionHistoryDao;
import com.cslc.eils.gameControl.entity.TInfoGame;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.exception.AppException;
import com.cslc.eils.gameControl.exception.DBUnavailableException;
import com.cslc.eils.gameControl.exception.TicketPoolNotFind;
import com.cslc.eils.gameControl.exception.UnSupprotedGameException;
import com.cslc.eils.gameControl.exception.UserInputVerifyException;
import com.cslc.eils.gameControl.netInterface.httpClient.GetRngs;
import com.cslc.eils.gameControl.netInterface.httpClient.IGetRngs;
import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.pojo.RngReq;
import com.cslc.eils.gameControl.pojo.Ticket;
import com.cslc.eils.gameControl.pojo.TicketPool;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.SysErrorCode;
import com.cslc.eils.gameControl.util.SystemUtil;

/**
 * @author tianhao
 *
 */
public class GameService {
	
	private static final Log log = LogFactory.getLog(GameService.class);
	
	//记录交易流水接口
	private TransactionHistoryDao transactionHistoryDao;
	//游戏信息接口
	private GameInfoDao gameInfoDao;
	//取随机数接口
	private IGetRngs IgetRngs = new GetRngs();
	
	private ImportedDao importedDao;
	
	//游戏信息
	private TInfoGame gameInfo;
	//票池对象
	private ITicketPoolManager ticketPoolManager;
	//rng游戏造票接口
	private ITicketFactory ITicketFactory;

	/**
	 * 默认构造函数 
	 */
	public GameService(){}
	
	/**
	 * 
	 * @param gameId
	 * @param value
	 * @throws Exception 
	 */
	public GameService(int gameId, int value) throws Exception{
		this.initService(gameId,value);
	}
	
	/**
	 * 
	 * @param gameId
	 * @param value
	 * @throws DBUnavailableException 
	 * @throws UnSupprotedGameException 
	 * @throws TicketPoolNotFind 
	 * @throws Exception 
	 */
	public boolean initService(int gameId, int value) throws UnSupprotedGameException, DBUnavailableException, TicketPoolNotFind  {
		log.info("初始化游戏服务!游戏id为：" +
				"	gameId ：" +gameId+"		value:"+ value);
		int gameType = this.judgeGameType(gameId);
		//奖组型游戏
		if( gameType == Constants.GAME_TYPE_TRADITIONAL){
			//加载票池对象
			TicketPool ticketPool = new TicketPool( gameId, value);
			TicketPoolService ticketPoolService = ((TicketPoolService) InitSystem.getBean("ticketPoolService"));
			ticketPoolManager = ticketPoolService.getPool(ticketPool.getPoolName());
			if(ticketPoolManager != null){
				log.info("成功加载游戏服务！游戏类型为：" +gameType+
						"	gameId ：" +gameId+"		value:"+ value);
				return true;
			}else{
				log.warn("加载游戏服务失败！失败原因：未找到相应票池。游戏类型为：" +gameType+
						"，游戏gameId为 ：" +gameId+"，游戏面值value为:"+ value);
				throw new TicketPoolNotFind("未找到相应票池。游戏类型为：" +gameType+
						"，游戏gameId为 ：" +gameId+"，游戏面值value为:"+ value);
			}
		}
		/*概率即时型 多步游戏*/
		if (gameType == Constants.GAME_TYPE_INSTANT_MULTI) {
			
			switch (gameId) {
			case 1000201:
				ITicketFactory = new SlotFactory();
				break;
			}
			log.warn("成功加载游戏服务！游戏类型为：" +gameType+
					"	gameId ：" +gameId+"	value:"+ value);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 根据用户输入获取一张票
	 * 若为奖组游戏则从票池取一张；
	 * 若为概率游戏则即时生成一张；
	 * @return
	 * @throws DBUnavailableException 
	 * @throws UnSupprotedGameException 
	 * @throws Exception 
	 */
	public RESBet getTicket(REQBet reqBet) throws UnSupprotedGameException, DBUnavailableException  {
		
		int gameType = this.judgeGameType(reqBet.getGameID());
		RESBet  resBet = new RESBet();
		resBet.setTransactionID(reqBet.getTransactionID());
		
			try {
				//奖组型游戏
				if( gameType == Constants.GAME_TYPE_TRADITIONAL){
					return getTicketFromPool(reqBet);
				}//概率即时型 
				if(gameType == Constants.GAME_TYPE_INSTANT_SINGLE || 
						gameType == Constants.GAME_TYPE_INSTANT_MULTI){
					return getInstantTicket(reqBet);
				}
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resBet.setErrCode(SysErrorCode.BET_FAILURE);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resBet.setErrCode(SysErrorCode.BET_FAILURE);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resBet.setErrCode(SysErrorCode.BET_FAILURE);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.BET_FAILURE));
			} catch (UserInputVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resBet.setErrCode(SysErrorCode.USER_INPUT_VERIFY_FAILUER);
				resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.USER_INPUT_VERIFY_FAILUER)+e.getMessage());
			} 
		
		return resBet;
	}
	

	/**
	 * 根据gameid判断游戏类型
	 * @param gameID
	 * @return
	 * @throws NotSupportedException 
	 * @throws DBUnavailableException 
	 */
	private int judgeGameType(int gameId) throws UnSupprotedGameException, DBUnavailableException {
		if(gameInfo == null){
			try{
				gameInfo = gameInfoDao.findByGameId(gameId);
			}catch(Exception e){
				log.error("数据库异常！");
				throw new DBUnavailableException();
			}
			if(gameInfo == null){
				log.warn("数据库中未找到此玩法！玩法id为："+gameId);
				throw new UnSupprotedGameException("数据库中未找到此玩法！玩法id为："+gameId);
			}
		}
		return gameInfo.getGameType();
	}



	
	
	/**
	 * 奖组型游戏从票池中取票
	 * @param position
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws InterruptedException 
	 * @throws AppException 
	 * @throws Exception 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private RESBet getTicketFromPool(REQBet reqBet) throws HttpException, IOException, ConstraintViolationException {
		log.info("开始从票池中取票。");
		//获取取票的票池位置
		List<Integer> rng  = getPosition(reqBet.getGameID());
		int position = (Integer) rng.get(0);
		log.info("系统取得的随机数为："+position);
		boolean flag = true;
		while(flag){
			//判断票池位置是否被加锁
			if(ticketPoolManager.contains(position)){
				log.info("票池位置被加锁!");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//对票池位置加锁
				ticketPoolManager.locksPosition(position);
				log.info("对票池位置加锁!");
				Ticket replaceTicket =  ticketPoolManager.getReplaceTicket(position);
				log.info("补票sn为："+replaceTicket.getLotterySn());
				Ticket soldticket = ticketPoolManager.replace(position, replaceTicket);
				log.info("售票sn为："+soldticket.getLotterySn());
				//	TODO create TransRecord
				TTicketTransaction transRecord = new TTicketTransaction();
	
				
				transRecord.setSerialNumber(Long.valueOf((String) SystemUtil.resolveGroupSn(soldticket.getLotterySn()).get("SerialNumber")));
				transRecord.setTransactionId(reqBet.getTransactionID());
				transRecord.setBetValue(reqBet.getBetValue());
				transRecord.setGameId(reqBet.getGameID());
				transRecord.setInPut(reqBet.getInPut());
				
				transRecord.setTicketContent(soldticket.getTicketContent());
				transRecord.setReplaceTicketSn(replaceTicket.getLotterySn());
				transRecord.setRngs(rng.toString());
				transRecord.setSoldTicketSn(soldticket.getLotterySn());
				transRecord.setSoldTime(DateUtil.getCurrentDate());
				transRecord.setStep(1);
				transRecord.setEndFlag(true);
				String[] tc = soldticket.getTicketContent().split(",");
				transRecord.setPrize(Long.valueOf(tc[6]));
				transRecord.setPrizeLevel(Integer.valueOf(tc[5]));

				
				
				try{
					//持久化交易流水记录
					transactionHistoryDao.addTransRecord(transRecord);
					log.info("持久化交易流水记录成功！");
					
				}catch(ConstraintViolationException e){
					e.printStackTrace();
					//回滚交易
					ticketPoolManager.rollbackTrans(position,soldticket);
					log.error("TransactionId重复！");
					throw e;
				}finally{
					//对票池位置解锁
					ticketPoolManager.releasePosition(position);
					log.debug("对票池位置解锁!");
				}
				
				log.info("售票成功。补票sn为："+replaceTicket.getLotterySn()+"	售票sn为："+soldticket.getLotterySn());
				RESBet resBet = this.createResBet(transRecord);;
				return resBet;
			}	
		}
		return null;
	}
	
	

	/**
	 * @param reqBet
	 * @return
	 * @throws UserInputVerifyException 
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws Exception 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private RESBet getInstantTicket(REQBet reqBet) throws UserInputVerifyException, HttpException, IOException  {
		log.info("正在校验用户输入，input："+reqBet.getInPut() + "，step："+reqBet.getStep()+",betValue:"+reqBet.getBetValue());
		if(reqBet.getStep() >1){
			if(reqBet.getInPut() == null || reqBet.getInPut().length()!=22){
				log.error("用户输入错误，应该输入正确的票唯一标示。");
				throw new UserInputVerifyException("用户输入错误!应该输入正确的票唯一标示。");
			}
			
			TTicketTransaction soldTicket = transactionHistoryDao.findSoldTicketbySn(reqBet.getInPut(), reqBet.getStep()-1);
			if(soldTicket!= null){
				String ticketContent = soldTicket.getTicketContent();
				int prize = SystemUtil.resolvePrizeFromTicketContent(ticketContent);
				if(prize != reqBet.getBetValue()){
					log.error("用户输入错误，应该输入正确的投注金额，投注金额为上一步中奖金额！");
					throw new UserInputVerifyException("用户输入错误!应该输入正确的投注金额，投注金额为上一步中奖金额！");
				}
			}else{
				log.error("用户输入错误，未找到中奖票，请确认输入票sn是否正确！");
				throw new UserInputVerifyException("用户输入错误!未找到中奖票，请确认输入票sn是否正确！");
			}
		}
		
		
		InstanceTicket instanceTicket = (InstanceTicket) ITicketFactory.createTicket(reqBet);
		TTicketTransaction transRecord = new TTicketTransaction();
		transRecord.setSerialNumber(Long.valueOf((String) SystemUtil.resolveGroupSn(instanceTicket.getLotterySn()).get("SerialNumber")));
		transRecord.setTransactionId(reqBet.getTransactionID());
		transRecord.setBetValue(reqBet.getBetValue());
		transRecord.setGameId(reqBet.getGameID());
		transRecord.setInPut(reqBet.getInPut());
		transRecord.setTicketContent(instanceTicket.getTicketContent());
		transRecord.setReplaceTicketSn("0");
		transRecord.setRngs(instanceTicket.getRngs());
		transRecord.setSoldTicketSn(instanceTicket.getLotterySn());
		transRecord.setSoldTime(DateUtil.getCurrentDate());
		transRecord.setStep(reqBet.getStep());
		transRecord.setPrize(Long.valueOf(instanceTicket.getPrize()));
		transRecord.setPrizeLevel(instanceTicket.getPrizeLevel());
		transRecord.setEndFlag(instanceTicket.getEndFlag());
		try{
			//持久化交易流水记录
			transactionHistoryDao.addTransRecord(transRecord);
			log.info("持久化交易流水记录成功！");
			
		}catch(ConstraintViolationException e){
			e.printStackTrace();
			log.error("持久化交易流水记录失败！");
			throw e;
		}
		
		RESBet resBet = this.createResBet(instanceTicket);
		resBet.setTransactionID(transRecord.getTransactionId());
		resBet.setSaleTime(DateUtil.getDateString(transRecord.getSoldTime(), "yyyyMMddHHmmss"));
		return resBet;
	}
	
	

	/**
	 * 根据游戏规则取随机数
	 * @param gameID
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private  List<Integer> getPosition(int gameID) throws HttpException, IOException {
		RngReq rng = new RngReq();
		int maxValue = gameInfo.getPoolSize()-1;
		int minValue = 0;
		int randomNumberCount = 1;
		rng.setMaxValue(maxValue);
		rng.setMinValue(minValue);
		rng.setRandomNumberCount(randomNumberCount);
		return IgetRngs.getRngs(rng);
	}
	
	/**
	 * @param transRecord
	 * @return
	 */
	private RESBet createResBet(TTicketTransaction transRecord) {
		RESBet resBet = new RESBet();
		resBet.setTransactionID(transRecord.getTransactionId());
		resBet.setEndFlag(true);
		resBet.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
		resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
		resBet.setOutPut(SystemUtil.resolveOutPutFromTicketContent(transRecord.getTicketContent()));
		resBet.setPrize(SystemUtil.resolvePrizeFromTicketContent(transRecord.getTicketContent()));
		resBet.setSaleTime(DateUtil.getDateString(transRecord.getSoldTime(), "yyyyMMddHHmmss"));
		resBet.setTicketSn(transRecord.getSoldTicketSn());
		return resBet;
	}
	
	/**
	 * @param instanceTicket
	 * @return
	 */
	private RESBet createResBet(InstanceTicket instanceTicket) {
		RESBet resBet = new RESBet();
		resBet.setEndFlag(instanceTicket.getEndFlag());
		resBet.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
		resBet.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
		resBet.setOutPut(instanceTicket.getOutPut());
		resBet.setPrize(instanceTicket.getPrize());
		resBet.setTicketSn(instanceTicket.getLotterySn());
		return resBet;
	}

	public TransactionHistoryDao getTransactionHistoryDao() {
		return transactionHistoryDao;
	}

	public void setTransactionHistoryDao(TransactionHistoryDao transactionHistoryDao) {
		this.transactionHistoryDao = transactionHistoryDao;
	}

	public IGetRngs getIgetRngs() {
		return IgetRngs;
	}

	public void setIgetRngs(IGetRngs igetRngs) {
		IgetRngs = igetRngs;
	}

	public GameInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(GameInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}

	public ImportedDao getImportedDao() {
		return importedDao;
	}

	public void setImportedDao(ImportedDao importedDao) {
		this.importedDao = importedDao;
	}
	

}
