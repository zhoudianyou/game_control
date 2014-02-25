package com.cslc.eils.gameControl.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.entity.TTicketTransaction;

public interface ImportedDao {

	/* 根据奖组查询票 */
	public List<TTicketImported> findByGroupSn(long groupSn);

	/* 批量保存 */
	public void batchInsert(Collection<?> entities);

	/* 根据groupSn取所有未售票 */
	public ArrayList<TTicketImported> findUnsoldsByGroupSn(long groupSn);

	/*删除奖组中的票接口 更新所删除票状态为已失效*/
	public int delGroup(long groupSn);

	/* 更新已售票状态 */
	void updata4Sold(List<TTicketTransaction> soldTickets);

}
