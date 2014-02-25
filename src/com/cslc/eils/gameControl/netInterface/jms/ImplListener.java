/**
 * 
 */
package com.cslc.eils.gameControl.netInterface.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dto.REQDataImport;
import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.dto.REQGameControl;
import com.cslc.eils.gameControl.dto.REQMonitor;
import com.cslc.eils.gameControl.dto.RESDataImport;
import com.cslc.eils.gameControl.service.DeleteService;
import com.cslc.eils.gameControl.service.GameManageService;
import com.cslc.eils.gameControl.service.ImportService;
import com.cslc.eils.gameControl.service.TicketPoolService;
import com.cslc.eils.gameControl.util.Constants;


/**
 * @author tianhao
 *
 */
public class ImplListener implements MessageListener{
	private static final Log log = LogFactory.getLog(ImplListener.class);
	@Override
	public void onMessage(Message msg) {
		try {
			String jmsType = msg.getJMSType();
			int type = Integer.parseInt(jmsType);
			log.info("收到一个消息，消息类型为："+jmsType);
			ObjectMessage objMsg = (ObjectMessage) msg;
			//
			switch (type) {
				/*数据导入请求*/
			case Constants.MESSAGE_TYPE_REQUEST_IMPORT_DATA:
				ImportService importService = (ImportService) InitSystem.getBean("importService");
				importService.importGroupsData((REQDataImport)objMsg.getObject());
				break;
				/*游戏销售请求*/
			case Constants.MESSAGE_TYPE_REQUEST_START_GAME:
				GameManageService gameManageService = (GameManageService) InitSystem.getBean("gameManageService");
				gameManageService.gameOpen((REQGameControl) objMsg.getObject());
				break;
				/*游戏暂停请求*/
			case Constants.MESSAGE_TYPE_REQUEST_STOP_GAMET:
				GameManageService gameManageService2 = (GameManageService) InitSystem.getBean("gameManageService");
				gameManageService2.gameClose((REQGameControl) objMsg.getObject());
				break;	
				/*删除奖组请求*/
			case Constants.MESSAGE_TYPE_REQUEST_DELETE_GROUP:
				DeleteService deleteService = (DeleteService) InitSystem.getBean("deleteService");
				REQDelGroup reqDelGroup = (REQDelGroup) objMsg.getObject();
				deleteService.delGroups(reqDelGroup);
				break;
				/*查询监控信息请求*/
			case Constants.MESSAGE_TYPE_REQUEST_MONITOR:
				TicketPoolService ticketPoolService = (TicketPoolService) InitSystem.getBean("ticketPoolService");
				ticketPoolService.monitor((REQMonitor) objMsg.getObject());
				break;
			default:
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
