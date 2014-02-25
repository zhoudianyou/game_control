/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.util.List;

import com.cslc.eils.gameControl.entity.TSysImportgroup;
import com.cslc.eils.gameControl.entity.TSysImportgroupdetail;

/**
 * @author tianhao
 *
 */
public interface ImpGroupDetailDao {
	
	/*增加导入奖组明细记录*/
	public void insert(TSysImportgroupdetail tgd);

	/*批量更奖组明细*/
	public void updateImpGroupDetail(List<TSysImportgroupdetail> ls);
	
	/* 根据请求ID查询日志明细*/
	public List<TSysImportgroupdetail> findByRequestId(int requestId);
	
	/* 根据请求信息和groupsn查询日志明细*/
	public TSysImportgroupdetail findByRequestIdAndSn(TSysImportgroup t,long groupSn);

}
