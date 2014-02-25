/**
 * 
 */
package com.cslc.eils.gameControl.util;

/**
 * @author tianhao
 *
 */
public class SysErrorCode {

	/**业务类错误码*/
	public static final int SYSTEM_EXCUTE_CORRECT = 0;
	public static final String SYSTEM_EXCUTE_CORRECT_MSG = "系统处理正常!";
	
	public static final int GAME_NOT_SUPPORTED = 10101;
	public static final String GAME_NOT_SUPPORTED_MSG = "系统不支持此玩法!";
	
	public static final int FILE_NOT_EXIT = 10102;
	public static final String FILE_NOT_EXIT_MSG = "数据文件不存在!";
	
	public static final int GAME_STATUS_ERROR = 10103;
	public static final String GAME_STATUS_ERROR_MSG = "游戏状态错误!";
	
	public static final int GROUP_STATUS_ERROR = 10104;
	public static final String GROUP_STATUS_ERROR_MSG = "奖组状态错误!";
	
	public static final int FILE_VERIFY_FAILURE = 10105;
	public static final String FILE_VERIFY_FAILURE_MSG = "数据文件校验失败!";
	
	public static final int FILE_DELETE_FAILURE = 10106;
	public static final String FILE_DELETE_FAILURE_MSG = "删除临时文件失败!";
	
	public static final int SYNC_GAME_CONFIG_FAILURE = 10107;
	public static final String SYNC_GAME_CONFIG_FAILURE_MSG = "同步游戏信息失败!";
	
	public static final int OPEN_GAME_FAILURE = 10108;
	public static final String OPEN_GAME_FAILURE_MSG = "开启游戏销售失败!";
	
	public static final int BET_FAILURE = 10109;
	public static final String BET_FAILURE_MSG = "投注失败!";
	
	public static final int USER_INPUT_VERIFY_FAILUER = 10110;
	public static final String USER_INPUT_VERIFY_FAILUER_MSG = "用户输入校验失败！";
	
	public static final int UN_FIND_TRANSACTION = 10111;
	public static final String UN_FIND_TRANSACTION_MSG = "未找到此记录！";
	
	public static final int CLOSE_GAME_FAILURE = 10112;
	public static final String CLOSE_GAME_FAILURE_MSG = "暂停游戏销售失败!";
	
	public static final int DB_IMPORTDATA_FAILURE = 10202;
	public static final String DB_IMPORTDATA_FAILURE_MSG = "数据导入失败!";
	
	public static final int SERVER_FTP_UNAVAILABLE = 10301;
	public static final String SERVER_FTP_UNAVAILABLE_MSG = "FTP不可用!";
	
	
	/**
	 * 根据错误代码获得相应的错误信息
	 * @param errCode
	 * @return String
	 */
	public static String getMessageByErrCode(int errCode) {
		
		String errMsg = null;
		switch (errCode) {
			case SYSTEM_EXCUTE_CORRECT: errMsg = SYSTEM_EXCUTE_CORRECT_MSG;
				break;
			case GAME_NOT_SUPPORTED: errMsg = GAME_NOT_SUPPORTED_MSG;
				break;
			case FILE_NOT_EXIT: errMsg = FILE_NOT_EXIT_MSG;
				break;
			case GAME_STATUS_ERROR: errMsg = GAME_STATUS_ERROR_MSG;
				break;
			case GROUP_STATUS_ERROR: errMsg = GROUP_STATUS_ERROR_MSG;
				break;
			case FILE_VERIFY_FAILURE: errMsg = FILE_VERIFY_FAILURE_MSG;
				break;
			case FILE_DELETE_FAILURE: errMsg = FILE_DELETE_FAILURE_MSG;
				break;
			case SERVER_FTP_UNAVAILABLE: errMsg = SERVER_FTP_UNAVAILABLE_MSG;
				break;
			case DB_IMPORTDATA_FAILURE: errMsg = DB_IMPORTDATA_FAILURE_MSG;
				break;	
			case SYNC_GAME_CONFIG_FAILURE: errMsg = SYNC_GAME_CONFIG_FAILURE_MSG;
				break;	
			case OPEN_GAME_FAILURE: errMsg = OPEN_GAME_FAILURE_MSG;
				break;	
			case BET_FAILURE:errMsg =  BET_FAILURE_MSG;
				break;	
			case UN_FIND_TRANSACTION:errMsg =  UN_FIND_TRANSACTION_MSG;
				break;
			case CLOSE_GAME_FAILURE:errMsg = CLOSE_GAME_FAILURE_MSG;
				break;
			default:
				break;
				}
				return errMsg;
			}
}
