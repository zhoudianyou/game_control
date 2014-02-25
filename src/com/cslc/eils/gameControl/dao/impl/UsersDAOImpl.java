package com.cslc.eils.gameControl.dao.impl;

import com.cslc.eils.gameControl.dao.UsersDAO;
import com.cslc.eils.gameControl.entity.TInfoUsers;

public class UsersDAOImpl extends HibernateDaoImpl implements UsersDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long queryAccountByUserId(String userId) {
		TInfoUsers user = (TInfoUsers) findByUserId(userId);
		return user.getAccount();
	}

	@Override
	public long queryTotalPrizeByUserId(String userId) {
		TInfoUsers user = (TInfoUsers) findByUserId(userId);
		return user.getTotalPrize();
	}

	@Override
	public void consume(String userId, long money) {
		TInfoUsers user = (TInfoUsers) findByUserId(userId);
		user.setAccount(user.getAccount() -money);
		super.saveOrUpdate(user);
		
	}

	@Override
	public void pay(String userId, long money) {
		TInfoUsers user = (TInfoUsers) findByUserId(userId);
		user.setAccount(user.getAccount() + money);
		user.setTotalPrize(user.getTotalPrize() + money);
		super.saveOrUpdate(user);
		
	}

	@Override
	public Object findByUserId(String userId) {
		String hql = "from TInfoUsers t where t.userId = ?";
		Object[] objParm = new Object[]{userId};
		return (TInfoUsers)this.findUniqueObjByParam(hql, objParm);
	}

	

}
