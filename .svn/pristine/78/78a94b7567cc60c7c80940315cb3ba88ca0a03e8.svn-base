/**
 * 
 */
package com.cslc.eils.gameControl.dao;

/**
 * @author tianhao
 *
 */
public interface UsersDAO {
	/**查询账户信息*/
	public Object findByUserId(String userId);
	
	/**查询余额*/
	public long queryAccountByUserId(String userId);
	
	/**查询总中奖金额*/
	public long queryTotalPrizeByUserId(String userId);
	
	/**扣款*/
	public void consume(String userId ,long money);
	
	/**兑奖*/
	public void pay(String userId ,long money);



}
