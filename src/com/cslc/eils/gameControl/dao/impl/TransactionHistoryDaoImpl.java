/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.util.List;
import com.cslc.eils.gameControl.dao.TransactionHistoryDao;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.entity.TTicketpoolHistory;

/**
 * @author tianhao
 *
 */
public class TransactionHistoryDaoImpl extends HibernateDaoImpl implements TransactionHistoryDao {

	/**/
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.cslc.eils.gameControl.dao.TransactionHistoryDao#addTransRecord(com.cslc.eils.gameControl.pojo.TransRecord)
	 */
	@Override
	public String addTransRecord(TTicketTransaction transRecord) {
		return (String)super.insert(transRecord);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTicketTransaction> findUnupdata() {
		log.info("寻找未更新的销售记录。");
		String hql = "from TTicketTransaction t where updated = ?";
		return (List<TTicketTransaction>)super.findByParam(hql, false);
	}
	
	@Override
	public int addTicketpoolHistory(TTicketpoolHistory tcketpoolHistory){
		return (Integer)super.insert(tcketpoolHistory);
	}

	@Override
	public long getCurrentSerialNumber(int gameId) {
		log.info("寻找当前SerialNumber。");
		String hql = "select max(t.serialNumber) from TTicketTransaction t where gameId = ?";
		try{
			 return (Long) super.findUniqueObjByParam(hql, gameId);
		}catch(NullPointerException e){
			//e.printStackTrace();
			return 0;
		}
	}

	@Override
	public TTicketTransaction findSoldTicketbySn(String soldTicketSn ,int step ) {
		String hql = " from TTicketTransaction t where soldTicketSn = ? and step = ?";
		Object[] objParam = new Object[]{soldTicketSn,step};
		return (TTicketTransaction) super.findUniqueObjByParam(hql, objParam);
	}

	@Override
	public TTicketTransaction findByTid(String transactionID) {
		String hql = "from TTicketTransaction t where transactionId = ?";
		Object[] objectParm = new Object[]{transactionID};
		List<TTicketTransaction> ls = super.findByParam(hql, objectParm);
		if(ls != null && ls.size() > 0){
			TTicketTransaction tTransaction = ls.get(0);
			return tTransaction;
		}else{
			return null;
		}
	}

	@Override
	public void updataSoldStatus() {
		log.info("更新销售流水中已售票状态！");
		String hql = "update  TTicketTransaction t SET t.updated=? WHERE t.updated=?";
		Object[] objectParm = new Object[]{true,false};
		super.updateByHql(hql,objectParm);
		
	}

	@Override
	public TTicketTransaction findUnPayTicketbySn(String tsn) {
		String hql = " from TTicketTransaction t where  prizeLevel>0 and payFlag = false  and soldTicketSn=? order by step asc";
		Object[] objParam = new Object[]{tsn};
		List o =  super.findByParams(hql, objParam);
		if(o.size()==1){
			 return (TTicketTransaction) o.get(0);
		}else if(o.size()==2){
			return (TTicketTransaction) o.get(1);
		}else{
			return null;
		}
		
				 
	}

	@Override
	public void updataPayStatus(String tsn, boolean flag) {
		log.info("更新兑奖状态！");
		String hql = "update  TTicketTransaction t SET t.payFlag=true ,t.endFlag=true  WHERE t.soldTicketSn=?";
		Object[] objectParm = new Object[]{tsn};
		super.updateByHql(hql,objectParm);
		
	}
	
}
