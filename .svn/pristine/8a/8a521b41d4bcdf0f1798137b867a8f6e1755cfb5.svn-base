/**
 * 
 */
package com.cslc.eils.gameControl.core;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cslc.eils.gameControl.util.PropertiesUtil;


/**
 * @author tianhao
 *
 */
public class InitSystem {
	
	private static final Log log = LogFactory.getLog(InitSystem.class);
	 /**
     * 获取指定的系统配置属性值
     * 
     * @param key 指定的配置属性
     * 
     * @return 配置属性值
     */
    public static String getFtpProperty(String key){   
        if(key == null || "".equals(key)){
           // throw new PropertyException("属性键为空!");
        }
        
        return (String)getFtpProps().get(key);
    }
    
    public static String getRngsProperty(String key){   
        if(key == null || "".equals(key)){
           // throw new PropertyException("属性键为空!");
        }
        
        return (String)getRngsProps().get(key);
    }

    /**
     * 获取ftp属性配置项
     * 
     * @return 所有系统配置文件属性项
     */
    public static Properties getFtpProps() {
        try{
        	log.info("获取ftp属性配置项;");
            return PropsHolder.ftpProps;
        }catch (Exception e){
//            log.info("Initializing System Properties failured, nested exception is: " 
//                    + e.getMessage());
//            throw new AppRuntimeException(e);
        }
		return null;
    }
    
    /**
     * 获取所有系统属性配置项，缺省文件为：config/rngs.properties
     * 
     * @return 所有系统配置文件属性项
     */
    public static Properties getRngsProps() {
        try{
        	log.info("获取rng属性配置项;");
            return PropsHolder.rngsProps;
        }catch (Exception e){
//            log.info("Initializing System Properties failured, nested exception is: " 
//                    + e.getMessage());
//            throw new AppRuntimeException(e);
        }
		return null;
    }
    
    /**
     * 获取所有系统属性配置项，缺省文件为：config/log4j.properties
     * 
     * @return 所有系统配置文件属性项
     */
    public static void loadLog4jProps() {
    	log.info("获取log4j属性配置项;");
		 //加载系统参数日志
    	PropertyConfigurator.configure("conf/log4j.properties");  
    }

    
    
    /**
     * 获取 Spring 应用上下文，缺省文件为：applicationContext.xml
     * 
     * @return Spring 应用上下文
     */
    public static ApplicationContext getAppContext() {        
        try{
            return ApplicationContextHolder.appContext;
        }catch (Exception e){
//           //TODO EXP
        }
		return null;
    }

    /**
     * 从 Spring 配置上下文中获取指定的bean实例对象
     * 
     * @param beanId bean Id
     * @return bean实例对象
     */
    public static Object getBean(String beanId){
        return getAppContext().getBean(beanId);
    }
    
    
  

	


	/**
     * 系统配置对象Hoder类，延迟到第一次引用时加载
     *
     */
    private static class PropsHolder {        
       
      
        //
        private static final String ftpPropFilePath = "conf/ftp.properties";
        
        private static final String rngsPropFilePath = "conf/rngs.properties";
        

        
        /** 系统属性设置 */
        public static Properties ftpProps;
        
        public static Properties rngsProps;

       
        static {
            
            //加载应用属性配置文件
            PropsHolder.ftpProps = PropertiesUtil.loadProperties(ftpPropFilePath);
            
            PropsHolder.rngsProps = PropertiesUtil.loadProperties(rngsPropFilePath);
        }
    }

    /**
     * Spring 应用上下文对象Hoder类，延迟到第一次引用时加载
     *
     */
    private static class ApplicationContextHolder { 
    	
        //缺省的Spring配置文件路径
        private static final String springXmlFilePath = "conf/*.xml";

        /** Spring 应用上下文对象 */
        public static ApplicationContext appContext;
       
        static {
        	log.info("加载ApplicationContext："+springXmlFilePath);
            //加载Spring 应用上下文
            ApplicationContextHolder.appContext = new FileSystemXmlApplicationContext(springXmlFilePath);
            
        }
    }
}
