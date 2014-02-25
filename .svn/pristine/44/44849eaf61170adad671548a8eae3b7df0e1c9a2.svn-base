/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.util.List;

import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.entity.TSysDelgroup;

/**
 * @author tianhao
 *
 */
public interface DelGroupDao {
	
	/**
	 * Map<gameId,Map<value ,ArrayList<Group>> > 
	 * 增加删除奖组记录
	 * @return RecordId
	 */
	public Object addDelGroupRecord(REQDelGroup reqDelGroup);

	/**  
	 * 更新日志为已完成
	 * @return RecordId
	 */
	public void updata2Completed(int requesId);
	
	/**
	 * 查询未成功的删除日志
	 * 
	 * @return
	 */
	public List<TSysDelgroup> findFailRecorder();


}
