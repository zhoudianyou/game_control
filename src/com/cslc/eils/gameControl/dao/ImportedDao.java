package com.cslc.eils.gameControl.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.entity.TTicketTransaction;

public interface ImportedDao {

	/* ���ݽ����ѯƱ */
	public List<TTicketImported> findByGroupSn(long groupSn);

	/* �������� */
	public void batchInsert(Collection<?> entities);

	/* ����groupSnȡ����δ��Ʊ */
	public ArrayList<TTicketImported> findUnsoldsByGroupSn(long groupSn);

	/*ɾ�������е�Ʊ�ӿ� ������ɾ��Ʊ״̬Ϊ��ʧЧ*/
	public int delGroup(long groupSn);

	/* ��������Ʊ״̬ */
	void updata4Sold(List<TTicketTransaction> soldTickets);

}
