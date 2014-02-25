package com.cslc.eils.gameControl.dao;


import java.util.List;

import com.cslc.eils.gameControl.entity.TSysPeriod;

public interface SysPeriodDao {
	
	/* 开始销售 */
	public void insertLogAtOpen(int gameId);
	
	/* 暂停销售 */
	public void updateLogAtClose(int gameId);
	
	/* 取未暂停销售的奖组 */
	public List<TSysPeriod> findUnClosedByGameId(int gameId);
	
	
}
