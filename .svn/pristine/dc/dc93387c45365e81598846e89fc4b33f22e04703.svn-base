package com.cslc.eils.gameControl.util;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	private static Gson gson;
	/**
	 * 初始化
	 */
	static{
		if(gson == null){
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Date.class, new GsonDateTypeAdapter())
			.registerTypeAdapter(Integer.class, new GsonIntegerTypeAdapter())
			.serializeNulls();	
			gson = builder.create();
		}
	}
	/**
	 * json转换成类
	 * 
	 * @param json 
	 * @param clasz 
	 * @return Object
	 */
	public static <T> T jsonToPojo(String json,Class<T> clasz)  {
		return (T)gson.fromJson(json, clasz);
	}
	
	/**
	 * 类对象转换成json字符串
	 * 
	 * @param obj 
	 * @return JSON
	 */
	public static String pojoToJson(Object obj){
		String json = gson.toJson(obj);
		json = StringUtils.replace(json, "null", "\"\"");
		return json;
	}
}
