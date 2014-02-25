package com.cslc.eils.gameControl.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cslc.eils.gameControl.dao.DelGroupDao;
import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.dto.REQDelGroup;

public class DelGroupDaoImplTest {
	
	ApplicationContext applicationContext;
	
	
	
	DelGroupDao delGroupDao = new DelGroupDaoImpl();
	
	private  REQDelGroup reqDelGroup = new REQDelGroup();

	@Before
	public void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		
		
		int requesId = 1002;
		int reqType = 104;
		String requestTime = "20131126145523";
		reqDelGroup.setRequesId(requesId);
		reqDelGroup.setReqType(reqType);
		reqDelGroup.setRequestTime(requestTime);
		
		
		for(int gameId = 1 ;gameId<3;gameId++){
			for(int betValue = 2;betValue<10;betValue = 2*betValue){
				for(int groupId = 1;groupId<3;groupId++){
					Group group = new Group();
					group.setGameId(gameId);
					group.setBetValue(betValue);
					group.setGroupId(groupId);
					reqDelGroup.getGroupList().add(group);
				}
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddDelGroupRecord() {
		delGroupDao= (DelGroupDao) applicationContext.getBean("delGroupDao");
		delGroupDao.addDelGroupRecord(reqDelGroup);
	}

	@Test
	public final void testUpdata2Completed() {
		delGroupDao= (DelGroupDao) applicationContext.getBean("delGroupDao");
		delGroupDao.updata2Completed(reqDelGroup.getRequesId());
	}

}
