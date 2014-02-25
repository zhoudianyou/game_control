package com.cslc.eils.gameControl.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.dao.GroupInfoDao;
import com.cslc.eils.gameControl.dao.ImpGroupDao;
import com.cslc.eils.gameControl.dao.ImpGroupDetailDao;
import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.entity.TInfoGroup;
import com.cslc.eils.gameControl.entity.TSysImportgroup;
import com.cslc.eils.gameControl.entity.TSysImportgroupdetail;
import com.cslc.eils.gameControl.service.ImportService;

public class ExaminImportThread implements Runnable {
	private static final Log log = LogFactory.getLog(ExaminImportThread.class);
	private ImpGroupDao impGroupDao = (ImpGroupDao)InitSystem.getBean("impGroupDao");
	private ImpGroupDetailDao impGroupDetailDao = (ImpGroupDetailDao)InitSystem.getBean("impGroupDetailDao");
	private GroupInfoDao groupInfoDao = (GroupInfoDao)InitSystem.getBean("groupInfoDao");
	
	@Override
	public void run() {

		//获取导入日志中未成功的日志
		List<TSysImportgroup> ls = impGroupDao.findFailRecorder();
		log.info("检查异常导入日志，");
	
		if(ls.size()>0){
			log.warn("存在数据导入异常日志，共计："+ls.size()+"	条");
			//封装GroupRes列表
			List<GroupRes> groupResList = new ArrayList<GroupRes>();
			for(TSysImportgroup t : ls){
				String grpupSnStr = t.getGroupList().substring(1,t.getGroupList().length()-1);
				String[]groupSns = grpupSnStr.split(",");
				log.warn("正在处理数据导入异常,请求id为："+t.getRequestId()+"	，包含奖组数为："+groupSns.length+"	。");
				for(String tdSn : groupSns){
					long groupSn = Long.parseLong(tdSn.trim());
					TSysImportgroupdetail td = impGroupDetailDao.findByRequestIdAndSn(t, groupSn);
					if(td == null || td.getErrCode() != 0){
						TInfoGroup tGroup = groupInfoDao.findByGroupSn(groupSn);
						GroupRes groupRes = new GroupRes();
						groupRes.setGameId(tGroup.getGameId());
						groupRes.setBetValue(tGroup.getBetValue());
						groupRes.setGroupId(tGroup.getGroupId());
						groupRes.setGroupSn(groupSn);
						groupRes.setRequestId(t.getRequestId());
						groupRes.setErrCode(1);
						
						groupResList.add(groupRes);
					}
				}
				//数据导入
				ImportService importService = (ImportService)InitSystem.getBean("importService");
				importService.completeImportGroup(groupResList);
			}
		}
		
		

		}
}
