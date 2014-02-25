/**
 * 
 */
package com.cslc.eils.gameControl.Factory;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.cslc.eils.gameControl.cache.SNIncrementManager;
import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.netInterface.httpClient.GetRngs;
import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.RngReq;
import com.cslc.eils.gameControl.pojo.TicketDetailInfo;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;
import com.cslc.eils.gameControl.util.SystemUtil;



/**
 * @author tianhao
 *
 */
public class SlotFactory  extends AbstractFactory implements ITicketFactory{

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.Factory.AbstractFactory#createTicket(com.cslc.eils.gameControl.pojo.REQBet)
	 */
	@Override
	public IProduct createTicket(REQBet reqBet) throws HttpException, IOException {
		int step = reqBet.getStep();
		switch (step) {
		case 1:
			InstanceTicket ticket = createFristTicket( reqBet);
			return ticket;
		case 2:
			InstanceTicket ticket2 = createSecondTicket( reqBet);
			return ticket2;
			
			
		}
		return null;
	}

	

	/**
	 * @param reqBet
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private InstanceTicket createFristTicket(REQBet reqBet) throws HttpException, IOException {
		InstanceTicket ticket = new InstanceTicket();
		int betValue = reqBet.getBetValue();
		int gameId = reqBet.getGameID();
		ticket.setBetValue(betValue);
		ticket.setGameID(gameId);
		
			
		long SerialNumber = ((SNIncrementManager) InitSystem.getBean("snIncrementManager")).getSerialNumber(gameId);//  get SerialNumber++
		String lotterySn  = SystemUtil.createlotterySn(gameId,betValue, SerialNumber);
		ticket.setLotterySn(lotterySn);
		
		List<Integer> rngs = this.getFristRngs();
		ticket.setRngs(rngs.toString());
		
		OutPut outPut = new OutPut(rngs.get(0),rngs.get(1),rngs.get(2));
		ticket.setOutPut(outPut.fromat());
		
		int prizeLevel = judgePrizeLevel(outPut);
		ticket.setPrizeLevel(prizeLevel);
		//TODO get prize
		int prize = 0;
		switch (prizeLevel) {
		case 0:
			ticket.setEndFlag(true);
			break;
		case 1:
			prize = 25;
			ticket.setEndFlag(false);
			break;
		case 2:
			prize = 2;
			ticket.setEndFlag(false);
			break;
		}
		prize=betValue*prize;
		ticket.setPrize(prize);
		
		
		
		//��Ϸ����
		int gameType = 3;
		//����˳���(����)
		String fixedLotteryId = SystemUtil.fixedLengthString(0, 6);	
		//����
		String fixedAwardLevel = SystemUtil.fixedLengthString(prizeLevel, 2);
		//����= �����н���*��Ͷע���/������ֵ(2)��
		String fixedPrice = SystemUtil.fixedLengthString(prize, 8);
		//�Ƿ��
		int isLargeAward = SystemUtil.isLargeAward(prize);
		//��˰���
		 int tax = SystemUtil.getTax(prize);
		//��˰���(����)
		 String fixedTax = SystemUtil.fixedLengthString(tax, 8);
		//ʱ���
		String creatTime = DateUtil.getDate();
		TicketDetailInfo td = new TicketDetailInfo();
		td.setLotteryId(0);
		td.setLotterySn(lotterySn);
		td.setTicketContent(outPut.fromat());
		/**********���л�Ʊ��Ϣ************/
		StringBuffer sb = new StringBuffer();
		
		//Ʊsn
		sb.append(gameId);
		sb.append(lotterySn);
		
		//Ʊ������Ϣ
		sb.append(",");
		sb.append(gameId);
		sb.append(",");
		sb.append(gameType);
		sb.append(",");
		sb.append(betValue);
		sb.append(",");
		sb.append(fixedLotteryId);
		sb.append(",");
		
		//Ʊ��ϸ��Ϣ
		sb.append(fixedAwardLevel);
		sb.append(",");
		sb.append(fixedPrice);
		sb.append(",");
		sb.append(isLargeAward);
		sb.append(",");
		sb.append(fixedTax);
		sb.append(",");
		sb.append(creatTime);
		sb.append(",");		
		sb.append(JsonUtil.pojoToJson(td));	
		
		//���л�Ʊ��Ϣ
		ticket.setTicketContent(sb.toString());
		return ticket;
	}

	/**
	 * @param reqBet
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private InstanceTicket createSecondTicket(REQBet reqBet) throws HttpException, IOException {
		InstanceTicket ticket = new InstanceTicket();
		ticket.setLotterySn(reqBet.getInPut());//LotterySnΪ�û�����
		int betValue = reqBet.getBetValue();//betValueΪ��һ���н����
		int gameId = reqBet.getGameID();
		ticket.setBetValue(betValue);
		ticket.setGameID(gameId);
		
		List<Integer> rng = this.getSecondRng();
		ticket.setRngs(rng.toString());
		
		int prize = 0 ;
		int prizeLevel = 3;
		String fixedAwardLevel = null;
		String fixedPrice = null;
		if(rng.get(0) == 0){
			prize = betValue*2;
			ticket.setOutPut("0");
			//���𷭱�
			ticket.setPrize(prize);
			//���� ����
			ticket.setPrizeLevel(prizeLevel);
			//����
			 fixedAwardLevel = SystemUtil.fixedLengthString(prizeLevel, 2);
			//����= �����н���*��Ͷע���/������ֵ(2)��
			 fixedPrice = SystemUtil.fixedLengthString(betValue*2, 8);
		}else{
			//ʧ�ܣ�����ͽ��ȹ���
			ticket.setOutPut("1");
			ticket.setPrize(0);
			ticket.setPrizeLevel(0);
			//����
			 fixedAwardLevel = SystemUtil.fixedLengthString(0, 2);
			//����= �����н���*��Ͷע���/������ֵ(2)��
			 fixedPrice = SystemUtil.fixedLengthString(0, 8);
		}
		ticket.setStep(2);
		ticket.setEndFlag(true);
		
		
				//��Ϸ����
				int gameType = 3;


				//����˳���(����)
				String fixedLotteryId = SystemUtil.fixedLengthString(0, 6);	
				
				//�Ƿ��
				int isLargeAward = SystemUtil.isLargeAward(prize);
				//��˰���
				 int tax = SystemUtil.getTax(prize);
				//��˰���(����)
				 String fixedTax = SystemUtil.fixedLengthString(tax, 8);
				//ʱ���
				String creatTime = DateUtil.getDate();
				TicketDetailInfo td = new TicketDetailInfo();
				td.setLotteryId(0);
				td.setLotterySn(reqBet.getInPut());
				td.setTicketContent(rng.get(0).toString());
				/**********���л�Ʊ��Ϣ************/
				StringBuffer sb = new StringBuffer();
				
				//Ʊsn
				sb.append(gameId);
				sb.append(reqBet.getInPut());

				
				//Ʊ������Ϣ
				sb.append(",");
				sb.append(gameId);
				sb.append(",");
				sb.append(gameType);
				sb.append(",");
				sb.append(betValue);
				sb.append(",");
				sb.append(fixedLotteryId);
				sb.append(",");
				
				//Ʊ��ϸ��Ϣ
				sb.append(fixedAwardLevel);
				sb.append(",");
				sb.append(fixedPrice);
				sb.append(",");
				sb.append(isLargeAward);
				sb.append(",");
				sb.append(fixedTax);
				sb.append(",");
				sb.append(creatTime);
				sb.append(",");		
				sb.append(JsonUtil.pojoToJson(td));	
				
				//���л�Ʊ��Ϣ
				ticket.setTicketContent(sb.toString());
		return ticket;
	}
	
	



	/**
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private List<Integer> getFristRngs() throws HttpException, IOException {
			GetRngs getRngs = new GetRngs();
			RngReq rngReq = new RngReq();
			rngReq.setMinValue(0);
			rngReq.setMaxValue(11);
			rngReq.setRandomNumberCount(3);
			rngReq.setAllowMultipleRequest(true);
			rngReq.setAllowRepeated(true);
		return getRngs.getRngs(rngReq);
	}
	
	 /**
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private List<Integer> getSecondRng() throws HttpException, IOException {
		GetRngs getRngs = new GetRngs();
		RngReq rngReq = new RngReq();
		rngReq.setMinValue(0);
		rngReq.setMaxValue(1);
		rngReq.setRandomNumberCount(1);
		rngReq.setAllowMultipleRequest(true);
		rngReq.setAllowRepeated(true);
		return getRngs.getRngs(rngReq);
		}

	/**
	 * @param outPut
	 * @return
	 */
	private int judgePrizeLevel(OutPut outPut) {
		if(outPut.frist == outPut.second && outPut.frist == outPut.third){
			return 1;
		}
		if((outPut.frist == outPut.second && outPut.frist != outPut.third)
				||( outPut.frist == outPut.third && outPut.frist != outPut.second)
				||(outPut.second == outPut.third && outPut.second != outPut.frist )){
			return 2;
		}
		return 0;
	}


	class OutPut{
		private int frist;
		private int second;
		private int third;
		
		public OutPut(int frist,int second,int third){
			this.frist = frist;
			this.second = second;
			this.third = third;
		}
		
		public String fromat(){
			StringBuffer sb = new StringBuffer();
			sb.append(frist);
			sb.append(",");
			sb.append(second);
			sb.append(",");
			sb.append(third);
			return sb.toString();
		}

		public int getFrist() {
			return frist;
		}

		public void setFrist(int frist) {
			this.frist = frist;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public int getThird() {
			return third;
		}

		public void setThird(int third) {
			this.third = third;
		}
		
	}


}