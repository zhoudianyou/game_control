package com.cslc.eils.gameControl.dao.impl;

import java.util.List;

import com.cslc.eils.gameControl.dao.SysPeriodDao;
import com.cslc.eils.gameControl.entity.TInfoGame;
import com.cslc.eils.gameControl.entity.TSysPeriod;
import com.cslc.eils.gameControl.util.DateUtil;


public class SysPeriodDaoImpl extends HibernateDaoImpl implements SysPeriodDao {

	/**/
	private static final long serialVersionUID = 1L;

	@Override
	public void insertLogAtOpen(int gameId) {
		TInfoGame tInfoGame = (TInfoGame) super.find(TInfoGame.class, gameId);
		if(tInfoGame != null) {
			TSysPeriod p = new TSysPeriod();
			p.setStartSaleTime(DateUtil.getCurrentDate());
			p.setTInfoGame(tInfoGame);
			super.insert(p);
		}else{
			log.error("������Ϸ�쳣��DB��δ�ҵ�����Ϸ��");
		}
	}

	@Override
	public void updateLogAtClose(int gameId) {
		List<TSysPeriod> ls = this.findUnClosedByGameId(gameId);
		if (ls.size() == 1) {
			TSysPeriod p = ls.get(0);
			p.setEndTime(DateUtil.getCurrentDate());
			super.update(p);
		} else if (ls.size() == 0) {
			log.error("��ͣ��Ϸ�쳣��DB��δ�ҵ�����Ϸ��");
		} else {
			log.error("��ͣ��Ϸ�쳣��DB��δ�ҵ�����Ϸ����δ��ͣ��¼��");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TSysPeriod> findUnClosedByGameId(int gameId) {
		
		String hql = "from TSysPeriod t where t.endTime is null and t.TInfoGame.gameId = ?";
		List<TSysPeriod> ls = this.findByParam(hql, gameId);
		return ls;
	}

}
