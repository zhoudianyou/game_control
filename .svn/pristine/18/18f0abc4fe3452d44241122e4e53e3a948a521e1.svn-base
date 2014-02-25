/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.cslc.eils.gameControl.dao.Dao;
import com.cslc.eils.gameControl.entity.TTicketImported;
import com.cslc.eils.gameControl.util.Constants;



/**
 * @author tianhao
 *
 */
public class HibernateDaoImpl extends HibernateDaoSupport implements Dao{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object insert(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Class<?> entityClass, Serializable id) {
		getHibernateTemplate().delete(find(entityClass, id));
		
	}

	@Override
	public void update(Object entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public Object find(Class<?> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	protected final Log log = LogFactory.getLog(this.getClass());

	public int count(final Class entityClass) {
		String queryString = "from " + entityClass.getName();
		return count(queryString);
	}

	public int count(final String queryString, Object[] params) {
		List list = findByParams("select count(*) " + queryString,params);
		Object o = list.get(0);
		Long c = (Long) o;
		int cou = c.intValue();
		return cou;
		// query("select count(*) " + queryString)
		// return ( (Integer)
		// .get(0)).intValue();
	}
	
	public int count(final String queryString) {
		List list = query("select count(*) " + queryString);
		Object o = list.get(0);
		Long c = (Long) o;
		int cou = c.intValue();
		return cou;
		// query("select count(*) " + queryString)
		// return ( (Integer)
		// .get(0)).intValue();
	}

	@Transactional
	public void deleteAllEntities(Collection entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}
	
	@Transactional
	public void batchInsertOrUpdate(final Collection entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
		this.getHibernateTemplate().flush();
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			this.getHibernateTemplate().evict(object);
		}
	}

	public List findByParams(String hql, Object[] params) {
		return getHibernateTemplate().find(hql, params);
	}


	public Iterator iterate(final String queryString) {
		return getHibernateTemplate().iterate(queryString);
	}

	public List query(final Class entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public List query(final Class entityClass, final Object entity) {
		return query(entityClass, entity, false, false);
	}

	public List query(final Class entityClass, final Object entity,
			boolean enableLike, boolean ignoreCase) {
		Example example = Example.create(entity);
		if (enableLike) {
			example = example.enableLike();
		}
		if (ignoreCase) {
			example = example.ignoreCase();
		}
		return getSession().createCriteria(entityClass).add(example).list();
	}

	public List query(final String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	public List queryBySql(final String sqlString) {
		Session session = this.getSession();
		List list = session.createSQLQuery(sqlString).list();
		releaseSession(session);
		return list;
	}

	public List query(final Class entityClass, int pageNo, int pageSize) {
		return query("from " + entityClass.getName(), pageNo, pageSize);
	}

	public List query(final String queryString, int pageNo, int pageSize) {
		return getSession().createQuery(queryString).setFirstResult(
				(pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	/**
	 * 功能：sql查询的方式获得指定查询条件的对象列表、带分页，适用于较复杂的查询
	 * @author Weishaoying
	 * @version 1.0
	 * @param queryString
	 * @param params 参数数组
	 * @param pageNo 页码
	 * @param pageSize 页容量
	 * @return
	 */
	public List queryBySqlWithPage(String queryString, int pageNo, int pageSize, Object... params) {
		SQLQuery query = getSession().createSQLQuery(queryString);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		return query.list();
	}
	
	/**
	 * 功能：通过hql分页查询结果列表，参数个数未知
	 * @author Weishaoying
	 * @version 1.0
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List queryByHqlWithPage(String hql, int pageNo, int pageSize, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		return query.list();
	}
	
	public List query(final String queryString,Object[] params, int pageNo, int pageSize) {
		 Query hqlquery = getSession().createQuery(queryString);
		 hqlquery.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		 if(params!=null){
			 int len = params.length;
			 for(int i=0;i<len;i++)
				 hqlquery.setParameter(i, params[i]);
		 }
		 return hqlquery.list();
	}

	@Transactional
	public void saveOrUpdate(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Transactional
	public void saveOrUpdateAndFlush(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().evict(entity);
	}

	@Transactional
	public void insertAndFlush(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		this.getHibernateTemplate().flush();
	}

	@Transactional
	public int batchUpdate(final String updateString) {
		return getHibernateTemplate().bulkUpdate(updateString);

	}

	@Transactional
	public int batchUpdate(final String updateString, final Object entity) {
		return getHibernateTemplate().bulkUpdate(updateString, entity);
	}

	@Transactional
	public int batchUpdate(final String updateString, final Object[] entities) {
		return getHibernateTemplate().bulkUpdate(updateString, entities);
	}
	
	@Transactional
	public int updateByHql(final String updateString) {
		return getSession().createSQLQuery(updateString).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List findByParam(String hql, Object... param) {
		return getHibernateTemplate().find(hql, param);
	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueObjByParam(Class<T> clazz, String hql, Object... param) {
		List list =  getHibernateTemplate().find(hql, param);
		if(list == null || list.size() == 0) {
			return null;
		}else {
			return (T) list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public Object findUniqueObjByParam(String hql, Object... param) {
		try {
			List list = getHibernateTemplate().find(hql, param);
			return (list != null && list.size() > 0) ? list.get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public int updateBySql(final String updateString,Object[] params) {
		Query hqlquery = getSession().createSQLQuery(updateString);
		if(params!=null){
			int len = params.length;
			for(int i=0;i<len;i++)
				hqlquery.setParameter(i, params[i]);
		}
		
		return hqlquery.executeUpdate();
	}

	@Transactional
	public int updateByHql(final String updateString,Object[] params) {
//		Query hqlquery = getSession().createSQLQuery(updateString);
		Query hqlquery = getSession().createQuery(updateString);
		if(params!=null){
			 int len = params.length;
			 for(int i=0;i<len;i++)
				 hqlquery.setParameter(i, params[i]);
		 }
		
		return hqlquery.executeUpdate();
	}

	//Weishaoying 2011-2-12
	@SuppressWarnings("unchecked")
	public List queryBySqlWithParams(String sqlStr, Object... params) {
		Query query = this.getSession().createSQLQuery(sqlStr);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				Object obj = params[i];
				query.setParameter(i, obj);
			}
		}
		return query.list();
	}
	
	/**
	 * 功能：通过hql和指定参数查询不带分页
	 * @author Weishaoying
	 * @version 1.0
	 * @param hqlStr
	 * @param params
	 * @return
	 */
	public List queryByHqlWithParams(String hqlStr, Object... params) {
		Query query = this.getSession().createQuery(hqlStr);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 功能：获取session在service中使用session的相关方法
	 * @author Weishaoying
	 * @version 1.0
	 * @return
	 */
	public Session getSess() {
		return this.getSession();
	}
	
	@Transactional
	public void merge(final Object entity){
		getHibernateTemplate().merge(entity);
	}
	/**
     * 取得Q_GLOBAL_ID的下一个值
     * @return
     */
    public Long getSeq(){
        String sql = "select Q_GLOBAL_ID.nextval from dual";
        return ((BigDecimal)queryBySql(sql).get(0)).longValue();
    }


}
