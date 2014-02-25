/**
 * 
 */
package com.cslc.eils.gameControl.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.netInterface.jetty.JettyServer;
import com.cslc.eils.gameControl.service.TicketPoolService;





/**
 * @author tianhao
 *
 */
public class BootStrap {
	
	private static final Log log = LogFactory.getLog(BootStrap.class);
	
	/** 消息分发线程池(可变数量的线程池)*/
    private static ExecutorService trade_exec = Executors.newCachedThreadPool();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		 try {
			 BootStrap.sysInit();
			
			 //系统异常断开处理
			 Runtime.getRuntime().addShutdownHook(new ShutDown());
			
        }catch (Exception ex){
            //【ERROR】系统内部错误
        	ex.printStackTrace();
        	// 10401
            System.exit(1);
        }
	}
	
	/**
	 * 
	 */
	private static  void sysInit() {
		
		 //加载系统参数日志
		InitSystem.loadLog4jProps();
		// TODO 加载系统参数
        InitSystem.getFtpProps();
        InitSystem.getRngsProps();
		// TODO 加载spring配置，启动缓存队列
      //加载应用配置        
        InitSystem.getAppContext();

        initSecurityKey();
        
        
		//启动异步检查服务
		examine();
        
		//加载可售票池
        ((TicketPoolService) InitSystem.getBean("ticketPoolService")).initPools();
        
		
		//启动线程池
		initTradeThreadPool();
		
		//启动web服务端接口
		JettyServer.getInstance().start();
		

		
	}

	private static void initSecurityKey() {
		// TODO Auto-generated method stub
		String SecurityKey = "";
		System.setProperty("System.Security.Key", SecurityKey);
	}

	/**
	 * 启动交易线程池
	 */
	private static void initTradeThreadPool() {
		// 创建消息分发线程
		int dispatcherCount = 100;

		TradeThread[] dispatchers = new TradeThread[dispatcherCount];
		for (int i = 0; i < dispatcherCount; i++) {
			dispatchers[i] = new TradeThread();
		}
		 log.info("创建线程" + dispatcherCount + "个");

		// 启动
		for (int i = 0; i < dispatcherCount; i++) {
			trade_exec.execute(dispatchers[i]);
		}
		trade_exec.shutdown();
	}
	
	
	/**
	 * 启动异常检查线程，
	 * 异步处理异常场景
	 */
	private static void examine() {
		// 启动
		for (int i = 0; i < 1; i++) {
			trade_exec.execute(new ExaminImportThread());
			trade_exec.execute(new ExaminDeleteThread());
		}		
	}

	
	
}
