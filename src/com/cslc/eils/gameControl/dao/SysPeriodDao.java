package com.cslc.eils.gameControl.dao;


import java.util.List;

import com.cslc.eils.gameControl.entity.TSysPeriod;

public interface SysPeriodDao {
	
	/* ��ʼ���� */
	public void insertLogAtOpen(int gameId);
	
	/* ��ͣ���� */
	public void updateLogAtClose(int gameId);
	
	/* ȡδ��ͣ���۵Ľ��� */
	public List<TSysPeriod> findUnClosedByGameId(int gameId);
	
	
}
