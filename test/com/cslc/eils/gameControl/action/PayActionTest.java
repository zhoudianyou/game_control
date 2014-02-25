package com.cslc.eils.gameControl.action;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.pojo.REQPay;
import com.cslc.eils.gameControl.pojo.REQQueryUser;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.util.JsonUtil;

public class PayActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testExecute() {



		REQPay reqPay = new REQPay();
		reqPay.setUserId("10000000001");
		reqPay.setPrize(40);
		reqPay.setTicketSn("1000201100000000002301");
		String url = "http://127.0.0.1:7980/pay.action";
		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(url);
		
		String requestJson = JsonUtil.pojoToJson(reqPay);	
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
