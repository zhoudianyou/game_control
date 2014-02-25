/*
 * Copyright 2002-2013 China Sports Lottery Technology Group All rights reserved.
 * CSLC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.cslc.eils.gameControl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author tianhao
 *
 */
public class PropertiesUtil{

    private static final Log log = LogFactory.getLog(PropertiesUtil.class);

  
    /**
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static String get(String propertyName, String defaultValue) {
    	if(propertyName == null || propertyName.equals("") ){
    		
    		log.warn("parameters propertyName or defaultValue is null");
    		
    		return null;
    	}
        return System.getProperty(propertyName, defaultValue);
    }
    
    /**
     * @param propertyName
     * @param value
     * @return
     */
    public static String set(String propertyName,String value) {
    	if(propertyName == null || propertyName.equals("") || value == null || value.equals("") ){
    		
    		log.warn("parameters propertyName or value is null");
    		
    		return null;
    	}
        return System.setProperty(propertyName, value);
    }
    /**
     * 加载指定的属性文件配置项
     * 
     * @param propFileUrl 
     * 
     * @return 
     */
	public static Properties loadProperties(String propFileUrl) {
		log.info("加载指定的属性文件配置项:"+propFileUrl);
		InputStream in = null;
		try {
			File path = new File(propFileUrl);
			if (!path.exists()) {
				//	TODO Throws exp
			} else {
				Properties properties = new Properties();
				in = new FileInputStream(path);
				properties.load(in);
				return properties;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return null;
	}
    
  
   
}