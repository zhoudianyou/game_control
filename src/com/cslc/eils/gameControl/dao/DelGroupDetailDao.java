/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.entity.TSysDelgroup;
import com.cslc.eils.gameControl.entity.TSysDelgroupdetail;
import com.cslc.eils.gameControl.exception.AppException;

/**
 * @author tianhao
 *
 */
public interface DelGroupDetailDao {

	/**
	 * 增加删除奖组结果明细
	 * @param details
	 * @throws AppException 
	 */
	void addDelGroupDetail(GroupRes groupRes) throws AppException;

	
	/**
	 * 根据请求信息和groupsn查询日志明细
	 * 
	 * @param t
	 * @param groupSn
	 * @return
	 */
	public TSysDelgroupdetail findByRequestIdAndSn(TSysDelgroup t,long groupSn);


	void updateGroupDetail(GroupRes groupRes);
}
