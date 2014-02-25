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
	
	/*���ӽ��鵼����ϸ*/
	public void insert(TSysImportgroup tg);
	
	/*���µ�����־״̬*/
	public void updateImpGroupStatus(TSysImportgroup t);

	/*��������ID��ѯ������־��Ϣ*/
	public TSysImportgroup findByRequestId(int requesId);

	/*��ѯ��¼״̬Ϊδ��ɼ�¼*/
	public List<TSysImportgroup> findFailRecorder();
	
}
