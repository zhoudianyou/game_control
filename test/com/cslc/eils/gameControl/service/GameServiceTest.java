package com.cslc.eils.gameControl.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class GameServiceTest {
	ApplicationContext applicationContext;
	GameService gameService;

	@Before
	public void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		gameService = (GameService) applicationContext.getBean("gameService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGameService() {
//		fail("Not yet implemented");
		System.out.println(gameService);
		System.out.println((GameService) applicationContext.getBean("gameService"));
	}

	//@Test
	public void testInitService() {
		fail("Not yet implemented");
	}

}
