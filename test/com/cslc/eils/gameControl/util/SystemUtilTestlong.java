package com.cslc.eils.gameControl.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemUtilTestlong {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreatelotterySn() {
		//fail("Not yet implemented"); // TODO
		long long1 = 1234567890123456789l;
		System.out.println("long + :"+(long1+long1));
		
		int int1 = 1234567890;
		System.out.println("int + :"+(int1));
		
		float float1 = 20014999f;
		System.out.println("float1 + :\n"+(Integer.toBinaryString(Float.floatToIntBits(float1))));
		
		double double1 = 20014999d;
		System.out.println("double1 + :\n"+(Long.toBinaryString( Double.doubleToLongBits(double1))));
	}

}
