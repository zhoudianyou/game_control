package com.cslc.eils.gameControl.netInterface.httpClient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.pojo.RngReq;

public class GetRngsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetRngs() throws HttpException, IOException {
		GetRngs r = new GetRngs();
		RngReq rng = new RngReq();
		int maxValue = 5;
		int minValue = 0;
		int randomNumberCount = 2;
		rng.setMaxValue(maxValue);
		rng.setMinValue(minValue);
		rng.setRandomNumberCount(randomNumberCount);
		System.out.println(r.getRngs(rng));
	}

}
