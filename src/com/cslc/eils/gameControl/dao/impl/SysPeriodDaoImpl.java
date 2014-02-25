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
			log.error("开启游戏异常，DB中未找到此游戏！");
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
			log.error("暂停游戏异常，DB中未找到此游戏！");
		} else {
			log.error("暂停游戏异常，DB中未找到此游戏多条未暂停记录！");
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
