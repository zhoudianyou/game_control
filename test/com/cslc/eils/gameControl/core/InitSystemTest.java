package com.cslc.eils.gameControl.core;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.util.GameConfig;


public class InitSystemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetBean() {
		
		GameConfig gameConfig = (GameConfig) InitSystem.getBean("gameConfig");
		Assert.assertEquals(true, gameConfig.contains(1));
	}

	@Test
	public final void testGetGameConfig() {
		Assert.assertEquals("21",InitSystem.getFtpProps().get("port"));
	}

}
