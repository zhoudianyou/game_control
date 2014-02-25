package com.cslc.eils.gameControl.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.dao.DelGroupDao;
import com.cslc.eils.gameControl.dao.DelGroupDetailDao;
import com.cslc.eils.gameControl.dao.GroupInfoDao;
import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.entity.TInfoGroup;
import com.cslc.eils.gameControl.entity.TSysDelgroup;
import com.cslc.eils.gameControl.entity.TSysDelgroupdetail;
import com.cslc.eils.gameControl.entity.TSysImportgroupdetail;
import com.cslc.eils.gameControl.exception.AppException;
import com.cslc.eils.gameControl.service.DeleteService;

public class ExaminDeleteThread implements Runnable {
	private static final Log log = LogFactory.getLog(ExaminDeleteThread.class);
	
	private GroupInfoDao groupInfoDao = (GroupInfoDao)InitSystem.getBean("groupInfoDao");
	private DelGroupDao delGroupDao = (DelGroupDao)InitSystem.getBean("delGroupDao");
	private DelGroupDetailDao delGroupDetailDao = (DelGroupDetailDao)InitSystem.getBean("delGroupDetailDao");
	
	@Override
	public void run() {		

		//��ȡɾ����־��δ�ɹ�����־
		List<TSysDelgroup> dgroupList = delGroupDao.findFailRecorder();
		log.info("����쳣ɾ����־��");
		if(dgroupList.size()>0){
			log.warn("����ɾ���쳣��־�����ƣ�"+dgroupList.size()+"��");
			//����map
			Map<Integer,Map<Integer ,ArrayList<Group>> > groups = new HashMap<Integer,Map<Integer ,ArrayList<Group>> >();
			
			for(TSysDelgroup t : dgroupList){
				String grpupSnStr = t.getGroupList().substring(1,t.getGroupList().length()-1);
				String[]groupSns = grpupSnStr.split(",");
				log.warn("���ڴ���ɾ���쳣,����idΪ��"+t.getRequestId()+"	������������Ϊ��"+groupSns.length+"��");
				for(String tdSn : groupSns){
					long groupSn = Long.parseLong(tdSn.trim());
					TSysDelgroupdetail td = delGroupDetailDao.findByRequestIdAndSn(t, groupSn);
					//��ϸΪ�� ����δ������ɣ�ErrCode ��Ϊ0��������ʧ��
					if(td == null || td.getErrCode() != 0){
						TInfoGroup tGroup = groupInfoDao.findByGroupSn(groupSn);
						log.warn("ɾ���쳣,����idΪ��"+t.getRequestId()+"	������snΪ��"+tGroup.getGroupSn()+"��");
						int gameId = tGroup.getGameId();
						int value = tGroup.getBetValue();
						
						Group group = new Group();
						group.setGameId(gameId);
						group.setBetValue(value);
						group.setGroupId(tGroup.getGroupId());
						group.setGroupSn(tGroup.getGroupSn());
											
						if(!groups.containsKey(gameId)){
							groups.put(gameId, new HashMap<Integer , ArrayList<Group>>() );
						}
						if(!groups.get(gameId).containsKey(value)){
							groups.get(gameId).put(value, new ArrayList<Group>());
						}
						groups.get(gameId).get(value).add(group);
					}				
				}
				
				//��ȡ����ɾ��������ִ��һ�Ρ�
				DeleteService deleteService = (DeleteService)InitSystem.getBean("deleteService");
				try {
					deleteService.delGroups(t.getRequestId(),groups);
				} catch (AppException e) {
					e.printStackTrace();
				}
			}
		}
		
		

		}
}
