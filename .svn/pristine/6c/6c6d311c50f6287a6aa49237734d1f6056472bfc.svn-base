package com.cslc.eils.gameControl.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreatePoolName() {
		System.out.println(SystemUtil.createPoolName(1, 2));
		SystemUtil.resolveGroupSn("1000001100000010000180");
	}

	@Test
	public final void testTransBetValueformDetail() {
		Integer[] values = SystemUtil.transBetValueformDetail("2,4,8");
		for(int i=0;i<values.length ;i++)
			System.out.println(values[i]);
	}
	
	@Test
	public final void testResolvePrizeFromOutPut(){
		String res = "1000001020000010000000,1000001,1,2,000000,00,00000000,0,00000000,20131206174326,";
		System.out.println(res.length());
		String r2 = "1000001020000010000000,1000001,1,2,000000,00,00000000,0,00000000,20131206174326,{\"lotteryId\":\"0\",\"lotterySn\":\"1000001020000010000001\",\"ticketContent\":\"004020000005\"}";
		System.out.println(r2.substring(res.length(), r2.length()));
	//	System.out.println(SystemUtil.resolvePrizeFromTicketContent("1000001020000010000000,1000001,1,2,000000,00,00000000,0,00000000,20131206174326,{\"lotteryId\":\"0\",\"lotterySn\":\"1000001020000010000001\",\"ticketContent\":\"004020000005\"}"));
	}

}
