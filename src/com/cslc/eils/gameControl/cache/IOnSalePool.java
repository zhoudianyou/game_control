/**
 * 
 */
package com.cslc.eils.gameControl.cache;

import java.util.List;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.pojo.Ticket;

/**
 * ����Ʊ�ؽӿ�
 * @author tianhao
 *
 */
public interface IOnSalePool {
	
	/**
	 * ��ȡƱ��λ���ϵ�Ʊ
	 * @param position
	 * @return
	 */
	public Ticket get(int position ) ;
	
	/**
	 * ��ȡ���滻Ʊ���ϵ�Ʊ
	 * @param position
	 * @param ticket
	 * @return
	 */
	public Ticket replace(int position ,Ticket ticket);
	
	/**
	 * ����Ʊ����Ʊ���������
	 * ���ڳ�ʼ��ʱʹ��
	 * @param position
	 * @param ticket
	 * @return
	 */
	public boolean add(Ticket ticket);
	
	/**
	 * ��������Ʊ����Ʊ�������������
	 * ���ڳ�ʼ��ʱʹ��
	 * @param tickets
	 * @return
	 */
	public List<TTicketImported> addAll(List<TTicketImported> tickets);
	
	
	
	/**
	 * ���Ʊ�أ�
	 * @return
	 */
	public boolean clear();

}
