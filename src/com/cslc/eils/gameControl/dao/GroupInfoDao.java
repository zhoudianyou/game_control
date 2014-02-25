/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.util.ArrayList;

import com.cslc.eils.gameControl.entity.TInfoGroup;



/**
 * @author tianhao
 *
 */
public interface GroupInfoDao {
	
	/**查询奖组
	 * 
	 * @param group
	 * @return
	 */
	public TInfoGroup findByGroupId(int groupId);
	
	/**查询奖组
	 * 
	 * @param group
	 * @return
	 */
	public TInfoGroup findByGroupSn(long groupSn);

	/**
	 * 获取在售的奖组
	 * @param gameId
	 * @param value
	 * @return
	 */
	public ArrayList<TInfoGroup> findOnSaleGroup(int gameId ,int value);

	/**
	 * 更改奖组状态
	 * @param groupSn
	 * @param status
	 * @return
	 */
	public int  updateStatus(Long groupSn, int status);

	

}
