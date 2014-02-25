package com.cslc.eils.gameControl.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.pojo.TicketPool;

public class TicketPoolServiceTest2 {
	ApplicationContext applicationContext;
	TicketPoolService ticketPoolService;
	ImportService importService;

	@Before
	public void setUp() throws Exception {
		InitSystem.loadLog4jProps();
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		ticketPoolService = (TicketPoolService) applicationContext.getBean("ticketPoolService");
		importService = (ImportService)applicationContext.getBean("importService");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInitPools() {
		ticketPoolService.initPools();
		TicketPool ticketPool = new TicketPool(1000001,2);
		ticketPoolService.addPool(ticketPool);
		ticketPoolService.delPool(ticketPool);
	}

}
