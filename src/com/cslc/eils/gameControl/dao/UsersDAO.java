/**
 * 
 */
package com.cslc.eils.gameControl.dao;

/**
 * @author tianhao
 *
 */
public interface UsersDAO {
	/**��ѯ�˻���Ϣ*/
	public Object findByUserId(String userId);
	
	/**��ѯ���*/
	public long queryAccountByUserId(String userId);
	
	/**��ѯ���н����*/
	public long queryTotalPrizeByUserId(String userId);
	
	/**�ۿ�*/
	public void consume(String userId ,long money);
	
	/**�ҽ�*/
	public void pay(String userId ,long money);



}
