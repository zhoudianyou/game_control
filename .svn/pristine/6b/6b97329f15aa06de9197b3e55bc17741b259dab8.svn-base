/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.dao.DelGroupDetailDao;
import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.entity.TSysDelgroup;
import com.cslc.eils.gameControl.entity.TSysDelgroupdetail;
import com.cslc.eils.gameControl.entity.TSysImportgroupdetail;
import com.cslc.eils.gameControl.exception.AppException;

/**
 * @author tianhao
 *
 */
public class DelGroupDetailDaoImpl extends HibernateDaoImpl implements DelGroupDetailDao {
	private final Log log = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void addDelGroupDetail(GroupRes groupRes) throws AppException {
		TSysDelgroupdetail tSysDelgroupdetail = new TSysDelgroupdetail();
		
		TSysDelgroup tSysDelgroup = (TSysDelgroup) super.find(TSysDelgroup.class, groupRes.getRequestId());
		if(tSysDelgroup != null){
			tSysDelgroupdetail.setTSysDelgroup(tSysDelgroup);
			tSysDelgroupdetail.setGroupSn(groupRes.getGroupSn());
			tSysDelgroupdetail.setErrCode(groupRes.getErrCode());
			tSysDelgroupdetail.setErrMsg(groupRes.getErrDesc());
			super.insert(tSysDelgroupdetail);
		}else{
			throw  new AppException("删除奖组，没有找到奖组记录，请检查相应的外键-RequestId："+groupRes.getRequestId()+"是否存在记录。");
		}

	}
	
	@Override
	public TSysDelgroupdetail findByRequestIdAndSn(TSysDelgroup t,long groupSn){
		String hql = "from TSysDelgroupdetail t where TSysDelgroup = ? and t.groupSn = ?";
		Object[] params = new Object[]{t,groupSn};
		TSysDelgroupdetail td = (TSysDelgroupdetail)this.findUniqueObjByParam(hql, params);
		return td;

	}

	@Override
	public void updateGroupDetail(GroupRes groupRes) {
		String hql = "from TSysDelgroupdetail t where t.groupSn = ? ";
		TSysDelgroupdetail tSysDelgroupdetail = (TSysDelgroupdetail) super.findUniqueObjByParam(hql,groupRes.getGroupSn());
		tSysDelgroupdetail.setErrCode(groupRes.getErrCode());
		tSysDelgroupdetail.setErrMsg(groupRes.getErrDesc());
		super.update(tSysDelgroupdetail);
	}
	
	
}
