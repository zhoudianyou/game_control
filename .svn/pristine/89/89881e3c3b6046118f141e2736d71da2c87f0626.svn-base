package com.cslc.eils.gameControl.dao.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cslc.eils.gameControl.dao.DelGroupDao;
import com.cslc.eils.gameControl.dao.DelGroupDetailDao;
import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.exception.AppException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class DelGroupDetailDaoImplTest {
	ApplicationContext applicationContext;
	DelGroupDetailDao delGroupDetailDao;
	GroupRes groupRes = new GroupRes();

	@Before
	public void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		groupRes.setBetValue(2);
		groupRes.setErrCode(0);
		groupRes.setErrDesc("ok!");
		groupRes.setGameId(1001);
		groupRes.setGroupId(1);
		groupRes.setRequestId(1001);
		groupRes.setGroupSn(1001020001l);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddDelGroupDetail() throws AppException {
		delGroupDetailDao= (DelGroupDetailDao) applicationContext.getBean("delGroupDetailDao");
		try{
			delGroupDetailDao.addDelGroupDetail(groupRes);
		}catch(DataIntegrityViolationException e){
			delGroupDetailDao.updateGroupDetail(groupRes);
		}
	}

}
