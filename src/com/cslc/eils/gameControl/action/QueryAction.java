/**
 * 
 */
package com.cslc.eils.gameControl.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cslc.eils.gameControl.cache.ReceiveMsgManager;
import com.cslc.eils.gameControl.pojo.REQQuery;
import com.cslc.eils.gameControl.pojo.RESBet;
import com.cslc.eils.gameControl.pojo.TransQueryMsg;
import com.cslc.eils.gameControl.util.JsonUtil;

/**
 * @author tianhao
 *
 */
public class QueryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.action.BaseAction#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		REQQuery reqQuery = null;
		RESBet resBet = null;
		try {
			String reqJson =  req.getParameter("content");
			
			reqQuery = JsonUtil.jsonToPojo(reqJson, REQQuery.class);
			
			TransQueryMsg transQueryMsg = new TransQueryMsg();
			transQueryMsg.setReqQuery(reqQuery);
			synchronized(transQueryMsg){
				// push to queue
				ReceiveMsgManager.getInstance().pushReq(transQueryMsg);
				// 防止假唤醒
				while(transQueryMsg.getResBet() == null){
					transQueryMsg.wait();
				}
			}
			// 获得结果
			resBet = transQueryMsg.getResBet();
		}catch(Exception e){
			// TODO
		}finally{
			String resJson = JsonUtil.pojoToJson(resBet);
			
			
			PrintWriter pw =null;
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			try {
				pw=resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.print(resJson);
		}
		return null;
	}

}
