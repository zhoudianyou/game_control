/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.ArrayList;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;


/**
 * Ʊ�ع���ӿ�
 * @author tianhao
 *
 */
public interface ITicketPoolManager {
	
	/**
	 * ��Ʊ��ȡƱ
	 * @param position
	 * @return
	 */
	public Ticket getReplaceTicket(int position) ;
	
	/**
	 * ��ȡƱ����Ӧλ�õ�Ʊ�����ú�Ʊ�滻Ʊ����Ӧλ�õĴ���Ʊ��
	 * @param position Ʊ��λ��
	 * @param replaceTicket �滻Ʊ
	 * @return ���滻Ʊ
	 */
	public Ticket replace(int position,Ticket replaceTicket);
	
	/**
	 * ȡƱʧ�ܣ��ع�Ʊ��
	 * @param position
	 * @param ticket
	 */
	public void rollbackTrans(int position, Ticket ticket);

	/**
	 * Ʊ��λ�ü���
	 * @param position
	 * @return
	 */
	public  boolean locksPosition(int position);
	
	/**
	 * Ʊ��λ�ý���
	 * @param position
	 * @return
	 */
	public  boolean releasePosition(int position);

	/**
	 * �ж�Ʊ��λ���Ƿ񱻼���
	 * @param position
	 * @return
	 */
	public boolean contains(int position);
	
	/**
	 * ��ȡƱ������
	 * @return
	 */
	public String getPoolName();
	
	/**
	 * ��ȡ��Ʊ����
	 * @return
	 */
	public OnSalePool getOnSalePool();
	
	/**
	 * ���Ʊ�أ�
	 * @return
	 */
	public boolean clear();

	/**
	 * ��Ʊ������������Ʊ
	 * @param tickets
	 */
	public void addTickets(ArrayList<TTicketImported> tickets);

	/**
	 * ���Ʊ��������Ʊ
	 * @param tTicketImported
	 */
	public void addTicket2waiting(TTicketImported tTicketImported);
	
	/**
	 * ��ȡ��Ʊ���д�С
	 * @return
	 */
	public int getSize4Waiting();
	
	/**
	 * �жϺ�Ʊ�����Ƿ��㣬
	 * @return
	 */
	public boolean isLacking();
}
