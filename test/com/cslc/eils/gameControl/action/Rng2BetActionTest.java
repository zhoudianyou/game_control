package com.cslc.eils.gameControl.action;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.util.JsonUtil;

public class Rng2BetActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testExecute() {
		
//		List<Integer> rng = new ArrayList<Integer>();
//		rng.add(0);
//		String  rt = String.valueOf(rng.get(0));
//		System.out.println(rt);
//		
//		StringBuffer r = new StringBuffer();
//		r.append(rt);
//		System.out.println(r.toString());
		
		REQBet REQBet = new REQBet();
		REQBet.setTransactionID("15598639164053968073592");
		String url = "http://127.0.0.1:7980/bet.action";
		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(url);
		REQBet.setBetValue(20);
		REQBet.setInPut("1000201100000000000641");
		REQBet.setGameID(1000201);
		REQBet.setRetryFlag(false);
		REQBet.setStep(2);

		String requestJson = JsonUtil.pojoToJson(REQBet);
		NameValuePair[] data = { new NameValuePair("content",
				requestJson) };
		pm.setRequestBody(data);
		int statusCode;
		try {
			statusCode = hc.executeMethod(pm);
			if (statusCode == HttpStatus.SC_OK) {
				byte[] responseBody = pm.getResponseBody();
				String responseJson = new String(responseBody);
				System.out.println(responseJson);
				RESBet resBet = JsonUtil.jsonToPojo(responseJson,
						RESBet.class);
				
				System.out.println(!resBet.getEndFlag());
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
	}

}
