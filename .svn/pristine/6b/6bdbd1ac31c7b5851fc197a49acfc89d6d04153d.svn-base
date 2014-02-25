package com.cslc.eils.gameControl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <P>Title: 日期处理辅助类</P>
 * <P>Description: 辅助处理日期的格式转换和计算</P>
 * <P>Copyright: Copyright(c) 2012</P>
 * <P>Company: cslc.com.cn</P>
 * @author <a href="mailto:tangmingdong@cslc.com.cn">唐明东</a>
 * @version 1.0, 2013-2-18
 */
public abstract class DateUtil {

	/**
    SimpleDateFormat函数语法：
  
    G 年代标志符
    y 年
    M 月
    d 日
    h 时 在上午或下午 (1~12)
    H 时 在一天中 (0~23)
    m 分
    s 秒
    S 毫秒
    E 星期
    D 一年中的第几天
    F 一月中第几个星期几
    w 一年中第几个星期
    W 一月中第几个星期
    a 上午 / 下午 标记符 
    k 时 在一天中 (1~24)
    K 时 在上午或下午 (0~11)
    z 时区
*/

	/**
	 * 取得当前日期
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	/**
	 * 按指定格式转换日期成字符串
	 * @param date 日期对象
	 * @param format   格式
	 * @return
	 */
	public static String getDateString(Date date, String format) {
	    if (date == null) 
            return null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	/**
	 * 按指定格式把字符串转换成日期
	 * @param date 日期字符串
	 * @param format   日期格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}
	/**
	 * 计算指定日期前一天
	 * @param date 指定日期
	 * @return
	 */
	public static Date getPreviousDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}
	/**
	 * 计算指定日期的前一个月对应的日期
	 * @param date 指定日期
	 * @return
	 */
	public static Date getPreviousMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.MONTH, -1);
		return calendar.getTime();
	}
	/**
     * 计算指定日期的前一个年对应的日期
     * @param date 指定日期
     * @return
     */
	public static Date getPreviousYearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.YEAR, -1);
		return calendar.getTime();
	}
	/**
     * 计算指定日期的后一个年对应的日期
     * @param date 指定日期
     * @return
     */
	public static Date getNextYearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.YEAR, 1);
		return calendar.getTime();
	}
	/**
     * 计算指定日期的后一天对应的日期
     * @param date 指定日期
     * @return
     */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}
	/**
	 * 获取给定时间所在月的第一天Ｆ的日期和最后一天的日期
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static Date[] getMonthStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];
		Date firstDateOfMonth, lastDateOfMonth; // 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		// 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = calendar.getTime();
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
		calendar.add(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
		lastDateOfMonth = calendar.getTime();
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	 * 获取给定时间所在月的第一天Ｆ的日期和最后一天的日期
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static Date[] getMonthStartAndEndDate(Date date) {
		Date[] dates = new Date[2];
		Date firstDateOfMonth, lastDateOfMonth; // 得到当天是这月的第几天
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		firstDateOfMonth = cal.getTime();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		lastDateOfMonth = cal.getTime();
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}
	
	public static String getDate() {
		String result = null;
		result = getDateString(new Date(), "yyyyMMddHHmmss");
		return result;
	}
}
