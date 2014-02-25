package com.cslc.eils.gameControl.action;

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

import com.cslc.eils.gameControl.pojo.REQBet;
import com.cslc.eils.gameControl.pojo.REQQuery;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;

public class QueryActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDoPostHttpServletRequestHttpServletResponse()  {

		REQQuery REQQuery = new REQQuery();
		REQQuery.setTransactionID("22345678901234567890115");
		REQQuery.setRequestTime(DateUtil.getDateString(new Date(), "yyyyMMddHHmmss"));

		String url = "http://127.0.0.1:7980/query.action";
		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(url);
		
		String requestJson = JsonUtil.pojoToJson(REQQuery);	
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
