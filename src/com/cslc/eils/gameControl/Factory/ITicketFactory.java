/**
 * 
 */
package com.cslc.eils.gameControl.Factory;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.cslc.eils.gameControl.pojo.REQBet;



/**
 * @author tianhao
 *
 */
public interface ITicketFactory {

	public IProduct createTicket(REQBet reqBet)throws HttpException, IOException ;
}
