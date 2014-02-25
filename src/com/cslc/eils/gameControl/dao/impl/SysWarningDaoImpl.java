package com.cslc.eils.gameControl.dao.impl;

import com.cslc.eils.gameControl.dao.SysWarningDao;
import com.cslc.eils.gameControl.entity.TSysWarning;

public class SysWarningDaoImpl extends HibernateDaoImpl implements
		SysWarningDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object addSysWarning(TSysWarning tSysWarning) {
		return super.insert(tSysWarning);
	}

}
