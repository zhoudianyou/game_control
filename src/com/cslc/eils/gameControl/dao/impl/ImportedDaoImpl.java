/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cslc.eils.gameControl.dao.ImportedDao;
import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.entity.TTicketTransaction;
import com.cslc.eils.gameControl.util.Constants;

/**
 * @author tianhao
 *
 */
public class ImportedDaoImpl extends HibernateDaoImpl implements ImportedDao{

	private static final Log log = LogFactory.getLog(ImportedDaoImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void batchInsert(final Collection entities) {
		this.getHibernateTemplate().execute(new HibernateCallback() { 
			public Object doInHibernate(Session session) 
			throws HibernateException, SQLException {
				Connection conn = session.connection(); 
				conn.setAutoCommit(false);
				try{
					long a = System.currentTimeMillis();
				String sql = "insert into t_ticket_imported(TicketSn,GameId,GroupSn,BetValue,TicketContent,Summary,TicketStatus,ImportTime)"  //
						+ " values(?,?,?,?,?,?,?,?)"; 
						PreparedStatement stmt = conn.prepareStatement(sql); 
						int i = 0;
						for (Object obj : entities) {
							i++;
							TTicketImported t = (TTicketImported)obj;
							
							stmt.setString(1, t.getTicketSn());
							stmt.setInt(2, t.getGameId());
							stmt.setLong(3, t.getGroupSn());
							stmt.setInt(4, t.getBetValue());
							stmt.setString(5, t.getTicketContent());
							stmt.setString(6, t.getSummary());
							stmt.setInt(7, Constants.TICKET_STATUS_ONSALE);
							stmt.setObject(8, t.getImportTime());
							
							stmt.addBatch();
							if(i%50000 == 0){
								long aa = System.currentTimeMillis();
								stmt.executeBatch(); 
								conn.commit();
								long bb = System.currentTimeMillis();
								System.out.println("@@@@@@@@@@"+(bb-aa)+"@@@@@@@@@@@@");
							}	

						}
						stmt.executeBatch(); 
						conn.commit();
						long b = System.currentTimeMillis();
						System.out.println("============================"+(b-a)+"======================");
			  }catch(Exception e){
				  e.printStackTrace();
			  }finally{
				  conn.rollback();
				  conn.close();
			  }
				return null;
				
			}
	  });
}
	
	@Override
	public ArrayList<TTicketImported> findUnsoldsByGroupSn(long groupSn) {
		log.info("获取奖组未售票，奖组sn为："+groupSn+".");
		String hql = "from TTicketImported t where ticketStatus = ? and groupSn = ? ";
		Object[] objParam = new Object[]{Constants.TICKET_STATUS_ONSALE,groupSn};
		ArrayList<TTicketImported> ts = (ArrayList)super.findByParams(hql, objParam);
		log.info("获取奖组未售票，奖组sn为："+groupSn+".共获取票数为："+ts.size()+".");
		return ts;
	}

	@Override
	public int delGroup(long groupSn) {
		String hql = "update TTicketImported t set ticketStatus = ?, invalidTime = ? where groupSn = ?";
		Object[] objParam = new Object[]{Constants.TICKET_STATUS_INVALID,new Date(),groupSn};
		return super.updateByHql(hql,objParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updata4Sold(final List<TTicketTransaction> soldTickets) {
		log.info("更新已导入票中的已售票状态。");
		this.getHibernateTemplate().execute(new HibernateCallback() { 
			public Object doInHibernate(Session session) 
			throws HibernateException, SQLException {
				Connection conn = session.connection(); 
				conn.setAutoCommit(false);
				try{
					long a = System.currentTimeMillis();
				String sql = "update t_ticket_imported t set t.TicketStatus = ? ,t.SoldTime = ? where t.TicketSn = ? ";
						PreparedStatement stmt = conn.prepareStatement(sql); 
						int i = 0;
						for (Object obj : soldTickets) {
							i++;
							TTicketTransaction t = (TTicketTransaction)obj;
							
							stmt.setInt(1, Constants.TICKET_STATUS_SOLD);
							stmt.setObject(2, t.getSoldTime());
							stmt.setString(3,t.getSoldTicketSn());
							
							stmt.addBatch();
							if(i%50000 == 0){
								long aa = System.currentTimeMillis();
								stmt.executeBatch(); 
								conn.commit();
								long bb = System.currentTimeMillis();
								System.out.println("@@@@@@@@@@"+(bb-aa)+"@@@@@@@@@@@@");
							}	

						}
						stmt.executeBatch(); 
						conn.commit();
						long b = System.currentTimeMillis();
						System.out.println("============================"+(b-a)+"======================");
			  }catch(Exception e){
				  e.printStackTrace();
			  }finally{
				  conn.rollback();
				  conn.close();
			  }
				return null;
				
			}
	  });
		
	}

	@Override
	public List<TTicketImported> findByGroupSn(long groupSn) {
		String hql = "from TTicketImported t where groupSn = ? ";
		Object[] objParam = new Object[]{groupSn};
		ArrayList<TTicketImported> ts = (ArrayList)super.findByParams(hql, objParam);
		return ts;
	}

	

}
