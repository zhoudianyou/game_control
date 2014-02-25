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
	 * ����ɾ�������¼
	 * @return RecordId
	 */
	public Object addDelGroupRecord(REQDelGroup reqDelGroup);

	/**  
	 * ������־Ϊ�����
	 * @return RecordId
	 */
	public void updata2Completed(int requesId);
	
	/**
	 * ��ѯδ�ɹ���ɾ����־
	 * 
	 * @return
	 */
	public List<TSysDelgroup> findFailRecorder();


}
