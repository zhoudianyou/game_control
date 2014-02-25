/**
 * 
 */
package com.cslc.eils.gameControl.service;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cslc.eils.gameControl.dao.GameInfoDao;
import com.cslc.eils.gameControl.dao.SysPeriodDao;
import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.dto.REQDataImport;
import com.cslc.eils.gameControl.dto.REQGameControl;
import com.cslc.eils.gameControl.dto.RESDataImport;
import com.cslc.eils.gameControl.dto.RESGameControl;
import com.cslc.eils.gameControl.entity.TInfoGame;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;

/**
 * @author tianhao
 *
 */

public class ServiceTest{
	
	ApplicationContext applicationContext;
	ImportService importService;
	GameManageService gameManageService;
	
	@Before
	public  void setUp() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("conf/*.xml");
		importService = (ImportService)applicationContext.getBean("importService");
		gameManageService = (GameManageService)applicationContext.getBean("gameManageService");

	}

	@After
	public  void tearDown() throws Exception {
	}
	
	@Test
	public void testCheck() throws Exception{
		REQDataImport req = new REQDataImport();
		
        int s = (int)(Math.random()*(10000) + 1);        

		
		req.setRequesId(s);
		req.setReqType(0);
		req.setRequestTime(DateUtil.getDateString(new Date(), "yyyyMMddHHmmss"));
		
//		Group group = new Group();
//		group.setGroupId(1);
//		group.setGameId(1000001);
//		group.setBetValue(5);
//		group.setGroupSn(Long.valueOf("100000105000001"));
		
//		req.getGroupList().add(group);
		
		Group group2 = new Group();
		group2.setGroupId(2);
		group2.setGameId(1000001);
		group2.setBetValue(2);
		group2.setGroupSn(Long.valueOf("100000102000002"));
		req.getGroupList().add(group2);
		
//		Group group3 = new Group();
//		group3.setGroupId(1);
//		group3.setGameId(1000001);
//		group3.setBetValue(10);
//		group3.setGroupSn(Long.valueOf("100000110000001"));
//		req.getGroupList().add(group3);
		
		RESDataImport res = importService.importGroupsData(req);
		System.out.print("");
	}

//	@Test
	public void testControllGame(){
		//开启游戏
		REQGameControl req = new REQGameControl();
        int s = (int)(Math.random()*(10000) + 1);  
		req.setRequesId(s);
		req.setGameId(1000001);
		req.setReqType(Constants.MESSAGE_TYPE_REQUEST_START_GAME);
		req.setRequestTime(DateUtil.getDateString(new Date(), "yyyyMMddHHmmss"));	
		gameManageService.gameOpen(req);
		
		//关闭游戏
		req.setReqType(Constants.MESSAGE_TYPE_REQUEST_STOP_GAMET);
		gameManageService.gameClose(req);
		
		
	}
	
//	@Test
	@Transactional(rollbackFor=Exception.class ,propagation=Propagation.REQUIRED)
	public void testTraction(){
		GameInfoDao gameInfoDao = (GameInfoDao)applicationContext.getBean("gameInfoDao");
		TInfoGame tInfoGame = new TInfoGame(101, "test", 1, "2", 0, 100, 100, 1, DateUtil.getCurrentDate() );
		gameInfoDao.addNewGame(tInfoGame);
		gameInfoDao.addNewGame(tInfoGame);
	}
	
}
