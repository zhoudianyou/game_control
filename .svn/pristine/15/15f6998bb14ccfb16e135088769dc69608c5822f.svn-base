package com.cslc.eils.gameControl.util;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cslc.eils.gameControl.core.InitSystem;

public class GameConfigTest {
	ApplicationContext applicationContext;
	GameConfig gameConfig;

	@Before
	public void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		gameConfig= (GameConfig) applicationContext.getBean("gameConfig");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetGame() {
		//fail("Not yet implemented"); // TODO
		Assert.assertEquals(1, gameConfig.getGame(1).getId());
	}

	@Test
	public final void testPutGame() {
		gameConfig.putGame(1, gameConfig.getGame(1));
	}

	@Test
	public final void testLoadGame() {
		Assert.assertEquals(1, gameConfig.getGame(1).getId());
		InitSystem.getFtpProps();
	}

}
