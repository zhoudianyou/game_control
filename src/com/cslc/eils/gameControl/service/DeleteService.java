package com.cslc.eils.gameControl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dao.DelGroupDao;
import com.cslc.eils.gameControl.dao.DelGroupDetailDao;
import com.cslc.eils.gameControl.dao.GameInfoDao;
import com.cslc.eils.gameControl.dao.GroupInfoDao;
import com.cslc.eils.gameControl.dao.ImportedDao;
import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.dto.RESDelGroup;
import com.cslc.eils.gameControl.exception.AppException;
import com.cslc.eils.gameControl.netInterface.jms.ISender;
import com.cslc.eils.gameControl.pojo.TicketPool;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.SysErrorCode;

public class DeleteService {
	
	private static final Log log = LogFactory.getLog(DeleteService.class);
	
	private ISender msgSender;
	
	private DelGroupDao delGroupDao;
	
	private DelGroupDetailDao delGroupDetailDao;
	
	private GameInfoDao gameInfoDao;
	
	private GroupInfoDao  groupInfoDao;
	
	private ImportedDao importedDao;
	
	/**已处理的奖组结果*/
	private List<GroupRes> resList = new ArrayList<GroupRes>();
	
	/**
	 * 批量删除票池中的奖组
	 * @param reqDelGroup
	 * @throws AppException 
	 */
	public void delGroups(REQDelGroup reqDelGroup) throws AppException {
		Map<Integer,Map<Integer ,ArrayList<Group>> > doGroups = this.checkAndSendMsg(reqDelGroup);
		log.info("批量删除票池中的奖组.请求id为："+reqDelGroup.getRequesId());
		//记录日志
		delGroupDao.addDelGroupRecord(reqDelGroup);
		//按照游戏-票池 结构，批量删除票池中的奖组
		delGroups(reqDelGroup.getRequesId(),doGroups);
		//更新日志为已完成
		delGroupDao.updata2Completed(reqDelGroup.getRequesId());
	}

	/**
	 * 
	 * @param groups
	 * @return
	 */
	private Map<Integer, Map<Integer, ArrayList<Group>>> checkAndSendMsg(
			REQDelGroup reqDelGroup) {
		//将请求对象转换为结构化数据
		Map<Integer,Map<Integer ,ArrayList<Group>> > reqGroups = sortByTicketPool(reqDelGroup);
		
		//按照游戏遍历
		Set<Integer> gameIds = reqGroups.keySet();
		Iterator<Integer> itGameId = gameIds.iterator(); 
		while (itGameId.hasNext()) {
            int gameId= (Integer) itGameId.next();
            Map<Integer, ArrayList<Group>> games = reqGroups.get(gameId);
            //判断游戏状态是否为在售
            if(gameInfoDao.isOnSale(gameId)){
            	//遍历面值
            	Set<Integer> values = games.keySet();
        		Iterator<Integer> itValue = values.iterator(); 
        		while (itValue.hasNext()) {
        			int value= (Integer) itValue.next();
        			ArrayList<Group> groups = games.get(value);
        			//遍历奖组
        			Iterator<Group> itGroup = groups.iterator();
        			while(itGroup.hasNext()){
        				Group group = (Group) itGroup.next();
        				GroupRes groupRes = new GroupRes();
        				groupRes.setRequestId(reqDelGroup.getReqType());
        				groupRes.setGameId(gameId);
        				groupRes.setBetValue(value);
        				groupRes.setGroupSn(group.getGroupSn());
        				groupRes.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
        				groupRes.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
        				resList.add(groupRes);
        			}
        		}
            }else{
            	//遍历面值
            	Set<Integer> values = games.keySet();
        		Iterator<Integer> itValue = values.iterator(); 
        		while (itValue.hasNext()) {
        			int value= (Integer) itValue.next();
        			ArrayList<Group> groups = games.get(value);
        			Iterator<Group> itGroup = groups.iterator();
        			while(itGroup.hasNext()){
        				Group group = (Group) itGroup.next();
        				GroupRes groupRes = new GroupRes();
        				groupRes.setGameId(gameId);
        				groupRes.setBetValue(value);
        				groupRes.setGroupId(group.getGroupId());
        				groupRes.setErrCode(SysErrorCode.GAME_NOT_SUPPORTED);
        				groupRes.setErrDesc(SysErrorCode.getMessageByErrCode(SysErrorCode.GAME_STATUS_ERROR));
        				resList.add(groupRes);
        			}
        		}
        		//删除暂停游戏下的所有票池
            	reqGroups.remove(gameId);
            }
            	
		}
		
		//返回消息
		RESDelGroup resMsg = new RESDelGroup();
		resMsg.setGroupList(resList);
		resMsg.setReqType(reqDelGroup.getReqType());
		resMsg.setRequesId(reqDelGroup.getRequesId());
		resMsg.setResponseTime(DateUtil.getDate());
		msgSender.sendMessage(resMsg, Constants.MESSAGE_TYPE_RESPONSE_DELETE_GROUP);
		return reqGroups;
	}
	
	/**
	 * 将奖组按照游戏-票池进行分类
	 * @param reqDelGroup
	 * @return Map<gameId,Map<value ,ArrayList<Group>> > 
	 */
	private Map<Integer, Map<Integer, ArrayList<Group>>> sortByTicketPool(
			REQDelGroup reqDelGroup) {
		Map<Integer,Map<Integer ,ArrayList<Group>> > groups = 
				new HashMap<Integer, Map<Integer , ArrayList<Group>>>();
		List<Group> list = reqDelGroup.getGroupList();
		Iterator<Group> itr = list.iterator();
		while (itr.hasNext()) {
			Group group = (Group) itr.next();
			int gameId = group.getGameId();
			int value = group.getBetValue();
			if(!groups.containsKey(gameId))
				groups.put(gameId, new HashMap<Integer , ArrayList<Group>>() );
				if(!groups.get(gameId).containsKey(value))
				groups.get(gameId).put(value, new ArrayList<Group>());
			groups.get(gameId).get(value).add(group);
			
		}
		return groups;
	}
	
	/**
	 * 批量删除票池中的奖组
	 * Map<gameId,Map<value ,ArrayList<Group>> > 
	 * @param groups
	 * @return
	 * @throws AppException 
	 */
	public boolean delGroups(int requestId,
			Map<Integer,Map<Integer ,ArrayList<Group>> > groups) throws AppException{
		//依次处理所有游戏
		Set<Integer> keys = groups.keySet();
		Iterator<Integer> it = keys.iterator(); 
		while (it.hasNext()) {
            int gameId= (Integer) it.next();
            Map<Integer ,ArrayList<Group>> gameDel = groups.get(gameId);
            this.delByGame(requestId,gameId,gameDel);
        }
		return false;
	}

	/**
	 * 按照游戏删除奖组
	 * @param gameId
	 * @param gameDel
	 * @throws AppException 
	 */
	private void delByGame(int requestId,int gameId,
			Map<Integer, ArrayList<Group>> gameDel) throws AppException {
		log.info("按照游戏删除奖组,请求id为："+requestId+"	,游戏id为："+gameId);
		//依次处理所有票池
		Set<Integer> keys = gameDel.keySet();
		Iterator<Integer> it = keys.iterator(); 
		while (it.hasNext()) {
			 int vaule= (Integer) it.next();
			 TicketPool ticketPool = new TicketPool(gameId,vaule);
			 this.delByPool(requestId,ticketPool,gameDel.get(vaule));
			 this.reAddPool(ticketPool);
			 
		}
		
	}

	/**
	 * 重新加载票池
	 * @param ticketPool
	 */
	private void reAddPool(TicketPool ticketPool) {
		// TODO Auto-generated method stub
		((TicketPoolService) InitSystem.getBean("ticketPoolService")).addPool(ticketPool);
	}

	/**
	 * 按照票池删除
	 * @param ticketPool
	 * @throws AppException 
	 */
	private ArrayList<GroupRes> delByPool(int requestId,TicketPool ticketPool,ArrayList<Group> groups) throws AppException {
		
		// 删除票池
		((TicketPoolService) InitSystem.getBean("ticketPoolService")).delPool(ticketPool);
		
		Iterator<Group> it = groups.iterator(); 
		while (it.hasNext()) {
			Group group = it.next();
			delByGroup(requestId,group);
		}
		return null;
	}

	/**
	 * @param requestId
	 * @param group
	 * @throws AppException 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private void delByGroup(int requestId, Group group) throws AppException {
		// 更新奖组状态
		groupInfoDao.updateStatus(group.getGroupSn(),
				Constants.GROUP_STATUS_DELETE);
		// 更新票状态
		importedDao.delGroup(group.getGroupSn());
		// 增加明细记录
		GroupRes groupRes = new GroupRes();
		groupRes.setRequestId(requestId);
		groupRes.setGroupSn(group.getGroupSn());
		groupRes.setErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT);
		groupRes.setErrDesc(SysErrorCode
				.getMessageByErrCode(SysErrorCode.SYSTEM_EXCUTE_CORRECT));
		try {
			delGroupDetailDao.addDelGroupDetail(groupRes);
		} catch (DataIntegrityViolationException e) {
			// 如果存在，则更新此记录
			delGroupDetailDao.updateGroupDetail(groupRes);
		}
	}

	public DelGroupDao getDelGroupDao() {
		return delGroupDao;
	}

	public void setDelGroupDao(DelGroupDao delGroupDao) {
		this.delGroupDao = delGroupDao;
	}

	public DelGroupDetailDao getDelGroupDetailDao() {
		return delGroupDetailDao;
	}

	public void setDelGroupDetailDao(DelGroupDetailDao delGroupDetailDao) {
		this.delGroupDetailDao = delGroupDetailDao;
	}

	public GameInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(GameInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}

	public GroupInfoDao getGroupInfoDao() {
		return groupInfoDao;
	}

	public void setGroupInfoDao(GroupInfoDao groupInfoDao) {
		this.groupInfoDao = groupInfoDao;
	}

	public ImportedDao getImportedDao() {
		return importedDao;
	}

	public void setImportedDao(ImportedDao importedDao) {
		this.importedDao = importedDao;
	}

	public ISender getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(ISender msgSender) {
		this.msgSender = msgSender;
	}

	

	
	
}
