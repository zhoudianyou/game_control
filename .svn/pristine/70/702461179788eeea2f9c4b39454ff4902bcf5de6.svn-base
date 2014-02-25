package com.cslc.eils.gameControl.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cslc.eils.gameControl.core.InitSystem;

public class TicketPoolServiceTest {
	
	ApplicationContext applicationContext;
	TicketPoolService ticketPoolService;
	ImportService importService;

	@Before
	public void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		ticketPoolService = (TicketPoolService) applicationContext.getBean("ticketPoolService");
		importService = (ImportService)applicationContext.getBean("importService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		System.out.println(importService);
		System.out.println( (ImportService)applicationContext.getBean("importService"));
		
		//fail("Not yet implemented");
		System.out.println(ticketPoolService);
		System.out.println( (TicketPoolService) applicationContext.getBean("ticketPoolService"));
		System.out.println(((TicketPoolService) InitSystem.getBean("ticketPoolService")));
		System.out.println(((TicketPoolService) InitSystem.getBean("ticketPoolService")));
		System.out.println(((TicketPoolService) InitSystem.getBean("ticketPoolService")));
	}

}
