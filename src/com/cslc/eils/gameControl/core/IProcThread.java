/**
 * 
 */
package com.cslc.eils.gameControl.core;

/**
 * @author tianhao
 *
 */
public interface IProcThread {

	
	/**
	 * �߳�ֹͣ��
	 */
	public void stopThread();
	
	/**
	 * �����߳�״̬���±�־��
	 */
	public void setStatus(int status);
	
	/**
	 * ����߳�״̬��
	 */
	public int getStatus();
	
	/**
	 * ����߳�����
	 */
	public String getThreadName();

	
	/**
	 * �����߳�ѭ���������
	 */
	public boolean isFlag();

	public void setFlag(boolean flag);


}
