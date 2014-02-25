/**
 * 
 */
package com.cslc.eils.gameControl.util;

import java.util.HashMap;
import java.util.Map;
import com.cslc.eils.gameControl.pojo.TicketDetailInfo;


/**
 * @author tianhao
 *
 */
public class SystemUtil {
	
	/**
	 * 构造票池名称
	 * @param gameId
	 * @param value
	 * @return gameId_value
	 */
	public static String createPoolName(int gameId, int value){
		return String.valueOf(gameId)+"_"+String.valueOf(value);
	}
	
	/**
	 * 解析票池名称
	 * @param poolName
	 * @return gameId & value
	 * @throws Exception 
	 */
	public static  Map<?,?> resolvePoolName(String poolName) throws Exception{
		String[] strs = poolName.split("_");
		if(strs.length != 2)
			throw new Exception("票池名称无法解析！");
		Map<String,String> map = new HashMap<String,String>();
		map.put("gameId", strs[0]);
		map.put("value", strs[1]);
		return map;
	}
	/**
	 * 将游戏支持的面值明细转换成面值数组
	 * @param valueDetail
	 * @return
	 */
	public static Integer[] transBetValueformDetail(String valueDetail){
		String[] strs = valueDetail.split(",");
		Integer[] values = new Integer[strs.length] ;
		for(int i=0;i<strs.length ;i++)
			try{
				values[i] = Integer.valueOf(strs[i]);
			}catch(NumberFormatException e){
				//TODO EXP
			}
			
		return values;
	}
	
	/**
	 * 造票号
	 * @param gameId
	 * @param value
	 * @param SerialNumber 票顺序号
	 * @return
	 */
	public static String createlotterySn(int gameId, int value, long SerialNumber) {
		int check = createCheck( gameId,  value,  SerialNumber);
		String lotterySn = fixedLengthString(gameId,7)+fixedLengthString(value,2)+
				fixedLengthString(SerialNumber,12) +fixedLengthString(check,1);
		return lotterySn;
	}
	
	/**
	 * @param gameId
	 * @param value
	 * @param SerialNumber
	 * @return
	 */
	public static int createCheck(int gameId, int value, long SerialNumber){
		int check = (int) ((gameId + value + SerialNumber) % (2));
		return check;
	}
	
	/**
	 * 奖组游戏票中造SerialNumber
	 * @param groupId
	 * @param lotteryId
	 * @return
	 */
	public static long  createSerialNumber(int groupId,int lotteryId ){
		String SerialNumber = fixedLengthString(groupId,6)+fixedLengthString(lotteryId,6);
		return Long.valueOf(SerialNumber);
	}
	
	/**
	 * @param lotterySn
	 * @return 
	 */
	public static Map<?,?> resolveGroupSn(String lotterySn){
		if(lotterySn.length() == 22){
			String gameId = (lotterySn.substring(0, 7));
			String value = (lotterySn.substring(7, 9));
			String SerialNumber = (lotterySn.substring(9, 21));
			String check = (lotterySn.substring(21, 22));
			Map<String,String> map = new HashMap<String,String>();
			map.put("gameId", gameId);
			map.put("value", value);
			map.put("SerialNumber", SerialNumber);
			map.put("check", check);
			return map;
		}
		
		return null;
		
	}

	/**
	 * @param SerialNumber
	 * @return
	 */
	public static Map<?,?> resolveSerialNumber(String SerialNumber){
		if(SerialNumber.length() == 12){
			String groupId = SerialNumber.substring(0, 5);
			String lotteryId = SerialNumber.substring(6, 11);
			Map<String,String> map = new HashMap<String,String>();
			map.put("groupId", groupId);
			map.put("lotteryId", lotteryId);
			return map;
		}
		return null;
	}
	/**
	 * 返回固定长度的字符串
	 * 
	 * @param n
	 * @param length
	 * @return
	 */
	public static String fixedLengthString(int n,int length){
		StringBuffer sb = new StringBuffer();
		String str = String.valueOf(n);
		for(int i=0;i<length-str.length();i++){
			sb.append(0);
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 返回固定长度的字符串
	 * 
	 * @param n
	 * @param length
	 * @return
	 */
	public static String fixedLengthString(long n,int length){
		StringBuffer sb = new StringBuffer();
		String str = String.valueOf(n);
		for(int i=0;i<length-str.length();i++){
			sb.append(0);
		}
		sb.append(str);
		return sb.toString();
	}
	
	/**
	 * 根据票信息返回票的中奖金额
	 * @param oupPut
	 * @return
	 */
	public static int getPrizeFromOutPut(String oupPut){
		String[] arr = oupPut.split(",");
		String fixedPrize = arr[6];
		int prize = 0;
		for(int i=0;i<fixedPrize.length();i++){
			if(fixedPrize.charAt(i) != '0'){
				prize = Integer.parseInt(fixedPrize.substring(i, fixedPrize.length()-i));
			}
		}
		return prize;
	}
	
	/**
	 * @param outPut
	 * @return
	 */
	public static int resolvePrizeFromTicketContent(String ticketContent) {
		String[] arr = ticketContent.split(",");
		String fixedPrize = arr[6];
		int prize = Integer.parseInt(fixedPrize);
		
//		for(int i=0;i<fixedPrize.length();i++){
//			if(fixedPrize.charAt(i) != '0'){
//				prize = Integer.parseInt(fixedPrize.substring(i, fixedPrize.length()));
//			}
//		}
		return prize;
	}

	public static String resolveOutPutFromTicketContent(String outPut) {
		String ob = outPut.substring(outPut.indexOf("{"), outPut.length());
		TicketDetailInfo t = JsonUtil.jsonToPojo(ob, TicketDetailInfo.class);
		return t.getTicketContent();
	}


		/**
		 * 判断是否为大奖
		 * 
		 * @param awardLevelInfo
		 * @return
		 */
		public static int isLargeAward(int price){
			int flag = 0;
			if(price>=10000){
				flag = 1;
			}		
			return flag;
		}
	
		/**
		 * 计算中大奖的税额，保留小数点后2位
		 * 
		 * @param awardLevelInfo
		 * @return
		 */
		public static int getTax(int price){
			int tax = 0;
			if(isLargeAward(price) == 1){
				tax = (int)(price*0.2);
			}
			return tax;
		}
}
