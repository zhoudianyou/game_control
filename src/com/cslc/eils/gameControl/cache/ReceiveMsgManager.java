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
	
	 /** �������� ,static�ģ��������ʱ���г�ʼ��һ�Σ���֤�̰߳�ȫ */
	private static ReceiveMsgManager instance= new ReceiveMsgManager();
	
	/** ��������Ķ���*/
    public final static BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
    
    private ReceiveMsgManager(){}

    /**
	* ��ȡ��������ʵ��
	* 
	* @return ��������
	*/
	public static ReceiveMsgManager getInstance() {
		return instance;
	}

	/**
	 * ��������
	 * @param req
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean pushReq(Object o) throws InterruptedException {
		queue.put(o);
		return true;
	}

	/**
	 * ȡ����
	 * @return
	 */
	public Object pollReq() {
		return queue.poll();
	}

	
	
	
}
