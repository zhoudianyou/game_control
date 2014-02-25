/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.dao.DelGroupDao;
import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.entity.TSysDelgroup;
import com.cslc.eils.gameControl.entity.TSysImportgroup;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;

/**
 * @author tianhao
 *
 */
public class DelGroupDaoImpl extends HibernateDaoImpl implements DelGroupDao {
	private final Log log = LogFactory.getLog(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object addDelGroupRecord(REQDelGroup reqDelGroup) {
		TSysDelgroup tSysDelgroup = new TSysDelgroup();
		tSysDelgroup.setRequestId(reqDelGroup.getRequesId());
		try {
			tSysDelgroup.setRequestTime(DateUtil.parseDate(
					reqDelGroup.getRequestTime(), "yyyyMMddHHmmss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//设置状态-任务正在进行中
		tSysDelgroup.setStatus(Constants.TASK_STATUS_ONGOING);
		// 获得groupSn序列
		List<Long> groupSnLs = new ArrayList<Long>();
		for (int i = 0; i < reqDelGroup.getGroupList().size(); i++) {
			groupSnLs.add(reqDelGroup.getGroupList().get(i).getGroupSn());
		}
		tSysDelgroup.setGroupList(Arrays.toString(groupSnLs.toArray()));
		log.info("增加删除奖组记录,请求id为："+reqDelGroup.getRequesId());
		return super.insert(tSysDelgroup);
	}

	@Override
	public void updata2Completed(int requesId) {
		TSysDelgroup tSysDelgroup = (TSysDelgroup) super.find(TSysDelgroup.class, requesId);
		tSysDelgroup.setStatus(Constants.TASK_STATUS_COMPLETED);
		log.info("更新删除奖组记录,请求id为："+requesId);
		super.update(tSysDelgroup);
		
	}

	@Override
	public List<TSysDelgroup> findFailRecorder() {
		//TSysDelgroup中状态含义：0进行中,1已完成
		String hql = "from TSysDelgroup t where t.status <> 1";
		List<TSysDelgroup> ls = this.findByParam(hql, null);
		log.info("查找删除奖组异常记录！");
		return ls;
	}

}
