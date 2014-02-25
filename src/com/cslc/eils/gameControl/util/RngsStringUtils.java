package com.cslc.eils.gameControl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;

/**
 * 
 *字符串辅助类
 */
public abstract class RngsStringUtils {
	
	/**
	 * 是否0-9之间的字符
	 * @param charValue
	 * @return
	 */
	public static boolean isNumberCharacter(char charValue) {
		if (charValue < '0' || charValue > '9') {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取包含N个空格的字符串
	 * @param len
	 * @return
	 */
	public static String getFixLenWhitespaceString(int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			buffer.append(" ");
		}
		return buffer.toString();
	}

	
	/**
	 * 将字符串数组连接成一个字符串，以指定字符分割
	 * 
	 * @param array
	 *            the String array
	 * @param separator
	 *            the separator
	 * @return the String
	 */
	public static String array2String(String[] array, char separator) {
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i == (array.length - 1)) {
				strBuff.append(array[i]);
			} else {
				strBuff.append(array[i]).append(separator);
			}
		}
		return strBuff.toString();

	}
	/**
	 * 拼接字符串
	 * @param args 字符串数组
	 * @return
	 */
	public static String contact(Object... args){
	    StringBuilder sb = new StringBuilder();
	    if(args!=null){
	        for(Object arg:args){
	            sb.append(arg);
	        }
	    }
	    return sb.toString();
	}
	
	
	/**
     * 在字符串的左遍补充足位数
     * @param content 需要补充的字符串
     * @param digit 需要的总位数
     * @return
     */
    public static String leftCompletion(String content, int digit) {
        int length = content.length();
        StringBuilder tempStr = new StringBuilder();
        for(int i=0; i<(digit-length); i++){
            tempStr.append("0");
        }
        
        return tempStr.toString() + content;
    }
    /**
     * 将下载文件的名称转换为中文
     * @param content
     * @return
     */
    public static String getChineseForDown(String content){
        try {
            return URLEncoder.encode(content, "utf8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 功能：判断字符串是否非空，常用
     * @author Weishaoying
     * @version 1.0
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }
    
    
    
    /**
     * 功能：判断字符串是否为空
     * @author Weishaoying
     * @version 1.0
     * @param str
     * @return
     */
    public static boolean empty(String str) {
        return str == null || "".equals(str.trim());
    }
    
    /**
     * 功能：判断集合是否非空，常用
     * @author Weishaoying
     * @version 1.0
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean notEmpty(Collection c) {
        return c != null && c.size() > 0;
    }
    /**
     * 截取前len个，如果不足len个字符，截取全部
     * @param source    源字符串
     * @param len       长度
     * @return
     */
    public static String subLen(String source,int len){
        if(source ==null || len<0 || len>source.length())
            return source;
        return source.substring(0, len);
    }
    /**
     * 构建N个?，以,分割
     * @oaram n 个数
     * @return
     */
    public static String nques(int n){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<n;i++){
            if(i!=0)
                sb.append(",?");
            else
                sb.append("?");
        }
        
        return sb.toString();
    }
    /**
     * 构建N个( column in(?,?...) or  column in(?,?...) or ..)
     * @param columnName 列名
     * @param size 参数个数
     * @return N个( column in(?,?...) or  column in(?,?...) or ..)。 如果size==0,返回 1=1
     */
    public static String inCondition(String columnName,int size){
        if(size==0)
            return "1=1";
        StringBuilder sb = new StringBuilder("(");
        for(int i=0;i<size;i++){
            if(i%1000 == 0){
                if(i!=0){
                    sb.append(") or ");
                    sb.append(columnName+" in (");
                }else{
                    sb.append(columnName+" in (");
                }
            }else{
                sb.append(",");
            }
            sb.append("?");
            
            if(i==size-1)
                sb.append(")");
        }
        sb.append(")");
        return sb.toString();
    }
}