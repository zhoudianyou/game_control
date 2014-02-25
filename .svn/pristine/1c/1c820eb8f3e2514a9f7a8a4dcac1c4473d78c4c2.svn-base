/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import com.cslc.eils.gameControl.dao.GroupInfoDao;
import com.cslc.eils.gameControl.entity.TInfoGroup;
import com.cslc.eils.gameControl.util.Constants;

/**
 * @author tianhao
 *
 */
public class GroupInfoDaoImpl extends HibernateDaoImpl implements GroupInfoDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TInfoGroup> findOnSaleGroup(int gameId, int value) {
		String hql = "from TInfoGroup t where  gameId = ? and betValue = ? and groupStatus = ?";
		return (ArrayList<TInfoGroup>)super.findByParam(hql, gameId,value,Constants.GROUP_STATUS_ONSALE);
	}

	@Override
	public int updateStatus(Long groupSn, int status) {
		String hql = "update TInfoGroup t set groupStatus = ? "+"	where groupSn =?";
		Object[] objParam = new Object[]{status,groupSn};
		return super.updateByHql(hql, objParam);
	}

	@Override
	public TInfoGroup findByGroupId(int groupId) {
		return (TInfoGroup)find(TInfoGroup.class,groupId);
	}

	@Override
	public TInfoGroup findByGroupSn(long groupSn) {
		String hql = "from TInfoGroup t where t.groupSn =?";
		Object[] objParam = new Object[]{groupSn};
		return (TInfoGroup) this.findUniqueObjByParam(hql, objParam);
	}




}
