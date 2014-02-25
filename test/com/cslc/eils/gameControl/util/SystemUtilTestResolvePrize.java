package com.cslc.eils.gameControl.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemUtilTestResolvePrize {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testResolvePrizeFromTicketContent() {
		String res = "10002011000201100000000000090,1000201,3,10,000000,01,00000025,0,00000000,20131213162614,";
		System.out.println(SystemUtil.resolvePrizeFromTicketContent(res));
	}

}
