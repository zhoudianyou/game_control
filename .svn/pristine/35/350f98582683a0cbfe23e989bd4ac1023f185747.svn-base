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

public class RngBetActionTest {

	@Before
	public void setUp() throws Exception {
		String reqJson = "{\"retryFlag\":false,\"step\":\"1\",\"gameID\":\"1000201\",\"betValue\":\"10\",\"inPut\":\"ok\",\"transactionID\":\"60275976225974588503241\",\"requestTime\":\"20140107143524\"}";
//		String reqJson = "{\"transactionID\":\"22345678901234567890013\",\"gameID\":\"1000201\",\"retryFlag\":\"false\",\"betValue\":\"10\",\"step\":\"1\",\"inPut\":\"1000201100000000000641\",\"requestTime\":\"20140107145353\"}";
		REQBet reqBet = new REQBet();
		reqBet.setGameID(1000201);
		reqBet.setBetValue(10);
		reqBet.setRequestTime(DateUtil.getDate());
		reqBet.setRetryFlag(false);
		reqBet.setStep(1);
		reqBet.setTransactionID("22345678901234567890013");
		reqBet.setInPut("1000201100000000000641");
		System.out.println("pojo 2 json "+JsonUtil.pojoToJson(reqBet));
		reqBet = JsonUtil.jsonToPojo(reqJson, REQBet.class);
		System.out.println("................."+reqJson);
		System.out.println("................."+reqBet.getInPut());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDoPostHttpServletRequestHttpServletResponse()  {

		REQBet REQBet = new REQBet();
		REQBet.setGameID(1000201);
		REQBet.setBetValue(10);
		REQBet.setRequestTime(DateUtil.getDate());
		REQBet.setRetryFlag(false);
		REQBet.setStep(1);
		REQBet.setTransactionID("22345678901234567890014");
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
				RESBet resBet = JsonUtil.jsonToPojo(responseJson, RESBet.class);
				
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
