/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



/**
 * @author tianhao
 *
 */
public class ReceiveMsgManager {
	
	 /** 单例变量 ,static的，在类加载时进行初始化一次，保证线程安全 */
	private static ReceiveMsgManager instance= new ReceiveMsgManager();
	
	/** 接收请求的队列*/
    public final static BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
    
    private ReceiveMsgManager(){}

    /**
	* 获取单例对象实例
	* 
	* @return 单例对象
	*/
	public static ReceiveMsgManager getInstance() {
		return instance;
	}

	/**
	 * 增加请求
	 * @param req
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean pushReq(Object o) throws InterruptedException {
		queue.put(o);
		return true;
	}

	/**
	 * 取请求
	 * @return
	 */
	public Object pollReq() {
		return queue.poll();
	}

	
	
	
}
