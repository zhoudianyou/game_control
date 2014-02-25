package com.cslc.eils.gameControl.netInterface.httpClient;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.pojo.RngReq;
import com.cslc.eils.gameControl.pojo.RngRes;
import com.cslc.eils.gameControl.util.JsonUtil;

public class GetRngs implements IGetRngs {

	@Override
	public List<Integer> getRngs(RngReq rngReq) throws HttpException, IOException {
		String url = InitSystem.getRngsProperty("url");
		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(url);
		
		String requestJson = JsonUtil.pojoToJson(rngReq);	
		NameValuePair[] data =  { new NameValuePair("rngsRequestJson", requestJson)};
		pm.setRequestBody(data);
		
		RngRes rngRes = new RngRes();
		int statusCode = hc.executeMethod(pm);
		if(statusCode == HttpStatus.SC_OK){
			byte[] responseBody = pm.getResponseBody();
			String responseJson = new String(responseBody);
			
			rngRes = JsonUtil.jsonToPojo(responseJson, RngRes.class);
		}
		return rngRes.getRngs();
	}
}
