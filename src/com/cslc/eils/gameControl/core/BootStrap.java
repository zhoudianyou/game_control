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
	
	/** ��Ϣ�ַ��̳߳�(�ɱ��������̳߳�)*/
    private static ExecutorService trade_exec = Executors.newCachedThreadPool();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		 try {
			 BootStrap.sysInit();
			
			 //ϵͳ�쳣�Ͽ�����
			 Runtime.getRuntime().addShutdownHook(new ShutDown());
			
        }catch (Exception ex){
            //��ERROR��ϵͳ�ڲ�����
        	ex.printStackTrace();
        	// 10401
            System.exit(1);
        }
	}
	
	/**
	 * 
	 */
	private static  void sysInit() {
		
		 //����ϵͳ������־
		InitSystem.loadLog4jProps();
		// TODO ����ϵͳ����
        InitSystem.getFtpProps();
        InitSystem.getRngsProps();
		// TODO ����spring���ã������������
      //����Ӧ������        
        InitSystem.getAppContext();

        initSecurityKey();
        
        
		//�����첽������
		examine();
        
		//���ؿ���Ʊ��
        ((TicketPoolService) InitSystem.getBean("ticketPoolService")).initPools();
        
		
		//�����̳߳�
		initTradeThreadPool();
		
		//����web����˽ӿ�
		JettyServer.getInstance().start();
		

		
	}

	private static void initSecurityKey() {
		// TODO Auto-generated method stub
		String SecurityKey = "";
		System.setProperty("System.Security.Key", SecurityKey);
	}

	/**
	 * ���������̳߳�
	 */
	private static void initTradeThreadPool() {
		// ������Ϣ�ַ��߳�
		int dispatcherCount = 100;

		TradeThread[] dispatchers = new TradeThread[dispatcherCount];
		for (int i = 0; i < dispatcherCount; i++) {
			dispatchers[i] = new TradeThread();
		}
		 log.info("�����߳�" + dispatcherCount + "��");

		// ����
		for (int i = 0; i < dispatcherCount; i++) {
			trade_exec.execute(dispatchers[i]);
		}
		trade_exec.shutdown();
	}
	
	
	/**
	 * �����쳣����̣߳�
	 * �첽�����쳣����
	 */
	private static void examine() {
		// ����
		for (int i = 0; i < 1; i++) {
			trade_exec.execute(new ExaminImportThread());
			trade_exec.execute(new ExaminDeleteThread());
		}		
	}

	
	
}
