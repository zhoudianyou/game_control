package com.cslc.eils.gameControl.netInterface.httpClient;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.cslc.eils.gameControl.pojo.RngReq;


public interface IGetRngs {

	public List<Integer> getRngs(RngReq rngReq) throws HttpException, IOException;
}
