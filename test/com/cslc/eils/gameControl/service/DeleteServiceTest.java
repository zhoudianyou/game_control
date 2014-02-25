package com.cslc.eils.gameControl.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.exception.AppException;
import com.cslc.eils.gameControl.util.Constants;

public class DeleteServiceTest {
	
	private  REQDelGroup reqDelGroup = new REQDelGroup();
	
	DeleteService deleteService ;
	
	ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		int requesId = 100011001;
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

//	@Test
	public final void testDelGroups() throws AppException {
		deleteService = new DeleteService();
		deleteService.delGroups(reqDelGroup);
	}
	
	@Test
	public final void testGetBean() {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		deleteService = (DeleteService) applicationContext.getBean("deleteService");
	}

}
