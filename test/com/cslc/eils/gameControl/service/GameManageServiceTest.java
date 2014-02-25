package com.cslc.eils.gameControl.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.dto.REQGameControl;

public class GameManageServiceTest {
	REQGameControl req = new REQGameControl();
	@Before
	public void setUp() throws Exception {
		req.setGameId(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGameOpen() {
		GameManageService gameManageService = new GameManageService();
		
		gameManageService.gameOpen(req);
	}

}
