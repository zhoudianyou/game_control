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
	
	//��¼������ˮ�ӿ�
	private TransactionHistoryDao transactionHistoryDao;
	//��Ϸ��Ϣ�ӿ�
	private GameInfoDao gameInfoDao;
	//ȡ������ӿ�
	private IGetRngs IgetRngs = new GetRngs();
	
	private ImportedDao importedDao;
	
	//��Ϸ��Ϣ
	private TInfoGame gameInfo;
	//Ʊ�ض���
	private ITicketPoolManager ticketPoolManager;
	//rng��Ϸ��Ʊ�ӿ�
	private ITicketFactory ITicketFactory;

	/**
	 * Ĭ�Ϲ��캯�� 
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
		log.info("��ʼ����Ϸ����!��ϷidΪ��" +
				"	gameId ��" +gameId+"		value:"+ value);
		int gameType = this.judgeGameType(gameId);
		//��������Ϸ
		if( gameType == Constants.GAME_TYPE_TRADITIONAL){
			//����Ʊ�ض���
			TicketPool ticketPool = new TicketPool( gameId, value);
			TicketPoolService ticketPoolService = ((TicketPoolService) InitSystem.getBean("ticketPoolService"));
			ticketPoolManager = ticketPoolService.getPool(ticketPool.getPoolName());
			if(ticketPoolManager != null){
				log.info("�ɹ�������Ϸ������Ϸ����Ϊ��" +gameType+
						"	gameId ��" +gameId+"		value:"+ value);
				return true;
			}else{
				log.warn("������Ϸ����ʧ�ܣ�ʧ��ԭ��δ�ҵ���ӦƱ�ء���Ϸ����Ϊ��" +gameType+
						"����ϷgameIdΪ ��" +gameId+"����Ϸ��ֵvalueΪ:"+ value);
				throw new TicketPoolNotFind("δ�ҵ���ӦƱ�ء���Ϸ����Ϊ��" +gameType+
						"����ϷgameIdΪ ��" +gameId+"����Ϸ��ֵvalueΪ:"+ value);
			}
		}
		/*���ʼ�ʱ�� �ಽ��Ϸ*/
		if (gameType == Constants.GAME_TYPE_INSTANT_MULTI) {
			
			switch (gameId) {
			case 1000201:
				ITicketFactory = new SlotFactory();
				break;
			}
			log.warn("�ɹ�������Ϸ������Ϸ����Ϊ��" +gameType+
					"	gameId ��" +gameId+"	value:"+ value);
			return true;
		}
		
		return false;
	}
	
	/**
	 * �����û������ȡһ��Ʊ
	 * ��Ϊ������Ϸ���Ʊ��ȡһ�ţ�
	 * ��Ϊ������Ϸ��ʱ����һ�ţ�
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
				//��������Ϸ
				if( gameType == Constants.GAME_TYPE_TRADITIONAL){
					return getTicketFromPool(reqBet);
				}//���ʼ�ʱ�� 
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
	 * ����gameid�ж���Ϸ����
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
				log.error("���ݿ��쳣��");
				throw new DBUnavailableException();
			}
			if(gameInfo == null){
				log.warn("���ݿ���δ�ҵ����淨���淨idΪ��"+gameId);
				throw new UnSupprotedGameException("���ݿ���δ�ҵ����淨���淨idΪ��"+gameId);
			}
		}
		return gameInfo.getGameType();
	}



	
	
	/**
	 * ��������Ϸ��Ʊ����ȡƱ
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
		log.info("��ʼ��Ʊ����ȡƱ��");
		//��ȡȡƱ��Ʊ��λ��
		List<Integer> rng  = getPosition(reqBet.getGameID());
		int position = (Integer) rng.get(0);
		log.info("ϵͳȡ�õ������Ϊ��"+position);
		boolean flag = true;
		while(flag){
			//�ж�Ʊ��λ���Ƿ񱻼���
			if(ticketPoolManager.contains(position)){
				log.info("Ʊ��λ�ñ�����!");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//��Ʊ��λ�ü���
				ticketPoolManager.locksPosition(position);
				log.info("��Ʊ��λ�ü���!");
				Ticket replaceTicket =  ticketPoolManager.getReplaceTicket(position);
				log.info("��ƱsnΪ��"+replaceTicket.getLotterySn());
				Ticket soldticket = ticketPoolManager.replace(position, replaceTicket);
				log.info("��ƱsnΪ��"+soldticket.getLotterySn());
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
					//�־û�������ˮ��¼
					transactionHistoryDao.addTransRecord(transRecord);
					log.info("�־û�������ˮ��¼�ɹ���");
					
				}catch(ConstraintViolationException e){
					e.printStackTrace();
					//�ع�����
					ticketPoolManager.rollbackTrans(position,soldticket);
					log.error("TransactionId�ظ���");
					throw e;
				}finally{
					//��Ʊ��λ�ý���
					ticketPoolManager.releasePosition(position);
					log.debug("��Ʊ��λ�ý���!");
				}
				
				log.info("��Ʊ�ɹ�����ƱsnΪ��"+replaceTicket.getLotterySn()+"	��ƱsnΪ��"+soldticket.getLotterySn());
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
		log.info("����У���û����룬input��"+reqBet.getInPut() + "��step��"+reqBet.getStep()+",betValue:"+reqBet.getBetValue());
		if(reqBet.getStep() >1){
			if(reqBet.getInPut() == null || reqBet.getInPut().length()!=22){
				log.error("�û��������Ӧ��������ȷ��ƱΨһ��ʾ��");
				throw new UserInputVerifyException("�û��������!Ӧ��������ȷ��ƱΨһ��ʾ��");
			}
			
			TTicketTransaction soldTicket = transactionHistoryDao.findSoldTicketbySn(reqBet.getInPut(), reqBet.getStep()-1);
			if(soldTicket!= null){
				String ticketContent = soldTicket.getTicketContent();
				int prize = SystemUtil.resolvePrizeFromTicketContent(ticketContent);
				if(prize != reqBet.getBetValue()){
					log.error("�û��������Ӧ��������ȷ��Ͷע��Ͷע���Ϊ��һ���н���");
					throw new UserInputVerifyException("�û��������!Ӧ��������ȷ��Ͷע��Ͷע���Ϊ��һ���н���");
				}
			}else{
				log.error("�û��������δ�ҵ��н�Ʊ����ȷ������Ʊsn�Ƿ���ȷ��");
				throw new UserInputVerifyException("�û��������!δ�ҵ��н�Ʊ����ȷ������Ʊsn�Ƿ���ȷ��");
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
			//�־û�������ˮ��¼
			transactionHistoryDao.addTransRecord(transRecord);
			log.info("�־û�������ˮ��¼�ɹ���");
			
		}catch(ConstraintViolationException e){
			e.printStackTrace();
			log.error("�־û�������ˮ��¼ʧ�ܣ�");
			throw e;
		}
		
		RESBet resBet = this.createResBet(instanceTicket);
		resBet.setTransactionID(transRecord.getTransactionId());
		resBet.setSaleTime(DateUtil.getDateString(transRecord.getSoldTime(), "yyyyMMddHHmmss"));
		return resBet;
	}
	
	

	/**
	 * ������Ϸ����ȡ�����
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
