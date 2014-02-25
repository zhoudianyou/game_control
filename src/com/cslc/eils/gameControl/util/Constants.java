/**
 * 
 */
package com.cslc.eils.gameControl.util;

/**
 * @author qierfei
 *
 */
public final class Constants {
	
	/**
	 * 游戏状态定义
	 */
	/*已创建*/
	public final static int GAME_STATUS_CREATED = 0;
	/*可售票*/
	public final static int GAME_STATUS_SELLING = 1;
	/*暂停售票*/
	public final static int GAME_STATUS_PAUSING = 2;
	/*玩法停用*/
	public final static int GAME_STATUS_STOPED = 3;
	
	/**
	 * 奖组状态定义
	 */
	/*已创建*/
	public final static int GROUP_STATUS_CREATED = 0;
	/*造票中*/
	public final static int GROUP_STATUS_CREATETICKET_ONGOING = 1;
	/*造票完成*/
	public final static int GROUP_STATUS_CREATETICKET_COMPLETE = 2;
	/*已审核*/
	public final static int GROUP_STATUS_AUDITED = 3;
	/*导入中*/
	public final static int GROUP_STATUS_IMPORTTICKET_ONGOING =4;
	/*在售*/
	public final static int GROUP_STATUS_ONSALE =5;
	/*售罄*/
	public final static int GROUP_STATUS_SOLD_OUT=6;
	/*止售*/
	public final static int GROUP_STATUS_DELETE=7;
	/*造票异常*/
	public final static int GROUP_STATUS_CREATETICKET_FAILURE=20;
	/*导入失败*/
	public final static int GROUP_STATUS_IMPORTICKET_FAILURE=21;
	
	/**
	 * 票状态定义
	 */
	/*未售*/
	public final static int TICKET_STATUS_ONSALE = 0;
	/*已售*/
	public final static int TICKET_STATUS_SOLD = 1;
	/*已失效*/
	public final static int TICKET_STATUS_INVALID = 2;
	
	/**
	 * 游戏类型
	 */
	/*传统奖组型游戏*/
	public final static int GAME_TYPE_TRADITIONAL = 1;
	/*概率即时型 单步游戏*/
	public final static int GAME_TYPE_INSTANT_SINGLE = 2;
	/*概率即时型 多步游戏*/
	public final static int GAME_TYPE_INSTANT_MULTI = 3;
	
	/**
	 * 消息类型
	 */
	/*数据导入请求*/
	public final static int MESSAGE_TYPE_REQUEST_IMPORT_DATA = 101;
	/*数据导入响应*/
	public final static int MESSAGE_TYPE_RESPONSE_IMPORT_DATA = 201;
	/*游戏销售请求*/
	public final static int MESSAGE_TYPE_REQUEST_START_GAME = 102;
	/*游戏销售响应*/
	public final static int MESSAGE_TYPE_RESPONSE_START_GAME = 202;
	/*游戏暂停请求*/
	public final static int MESSAGE_TYPE_REQUEST_STOP_GAMET = 103;
	/*游戏暂停响应*/
	public final static int MESSAGE_TYPE_RESPONSE_STOP_GAME = 203;
	/*删除奖组请求*/
	public final static int MESSAGE_TYPE_REQUEST_DELETE_GROUP = 104;
	/*删除奖组响应*/
	public final static int MESSAGE_TYPE_RESPONSE_DELETE_GROUP  = 204;
	/*监控请求*/
	public final static int MESSAGE_TYPE_REQUEST_MONITOR = 105;
	/*监控响应*/
	public final static int MESSAGE_TYPE_RESPONSE_MONITOR  = 205;
	/*警告请求*/
	public final static int MESSAGE_TYPE_REQUEST_WARNING = 106;
	/*警告响应*/
	public final static int MESSAGE_TYPE_RESPONSE_WARNING  = 206;
	
	/*任务正在进行中*/
	public final static int  TASK_STATUS_ONGOING = 0;
	/*任务已完成*/
	public final static int  TASK_STATUS_COMPLETED = 1;
	
	/**
	 * 系统预警定义
	 */
	/*票池，票不足*/
	public final static int SYS_WARNING_TYPE_TICKET_LACKING = 1;
	
	
}
