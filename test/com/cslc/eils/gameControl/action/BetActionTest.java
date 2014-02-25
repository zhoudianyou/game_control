package com.cslc.eils.gameControl.action;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.pojo.RngRes;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;

public class BetActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDoPostHttpServletRequestHttpServletResponse()  {

		REQBet REQBet = new REQBet();
		REQBet.setGameID(1000001);
		REQBet.setBetValue(2);
		REQBet.setUserId("10000000001");
		REQBet.setRequestTime(DateUtil.getDate());
		REQBet.setRetryFlag(false);
		REQBet.setStep(1);
		REQBet.setTransactionID("12345678901234567890030");
		String url = "http://127.0.0.1:7980/bet.action";
		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(url);
		
		String requestJson = JsonUtil.pojoToJson(REQBet);	
		NameValuePair[] data =  { new NameValuePair("content", requestJson)};
		pm.setRequestBody(data);
		
		
		int statusCode;
		try {
			statusCode = hc.executeMethod(pm);
			if(statusCode == HttpStatus.SC_OK){
				byte[] responseBody = pm.getResponseBody();
				String responseJson = new String(responseBody);
				System.out.println(responseJson);
				RESBet RESBet = JsonUtil.jsonToPojo(responseJson, RESBet.class);
				
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
