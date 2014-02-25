package com.cslc.eils.gameControl.util;

import org.apache.commons.codec.binary.Base64;



/**
 * <P>Title: lobom</P>
 * <P>Description: Base64编码组件</P>
 * <P>Copyright: Copyright(c) 2010</P>
 * <P>Company: cslc.com.cn</P>
 * @author qierfei
 * @version 1.0
 * @date Jun 24, 2010
 */
public class Base64Coder {
	
	/**
	 * 基本Base64编码
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode(byte[] data) throws Exception {
		byte[] encodeData = Base64.encodeBase64(data);
		return encodeData;
	}
	
	/**
	 * Base64安全编码
	 * 遵循RFC2045实现
	 * @param data 待编码数据
	 * @return String 编码数据
	 * @throws Exception
	 */
	public static byte[] encodeSafe(byte[] data) throws Exception {
		byte[] encodeData = Base64.encodeBase64(data, true);
		return encodeData;
	}
	
	/**
	 * Base64解码
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(byte[] data) throws Exception {
		byte[] decodeData = Base64.decodeBase64(data);
		return decodeData;
	}
}
