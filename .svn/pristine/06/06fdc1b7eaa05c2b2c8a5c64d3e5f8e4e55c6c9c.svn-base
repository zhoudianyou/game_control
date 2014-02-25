/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.util.List;

import com.cslc.eils.gameControl.entity.TSysImportgroup;

/**
 * @author tianhao
 *
 */
public interface ImpGroupDao {
	
	/*增加奖组导入明细*/
	public void insert(TSysImportgroup tg);
	
	/*更新导入日志状态*/
	public void updateImpGroupStatus(TSysImportgroup t);

	/*根据请求ID查询导入日志信息*/
	public TSysImportgroup findByRequestId(int requesId);

	/*查询记录状态为未完成记录*/
	public List<TSysImportgroup> findFailRecorder();
	
}
