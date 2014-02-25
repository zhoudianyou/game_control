/**
 * 
 */
package com.cslc.eils.gameControl.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tianhao
 *
 */
public interface Dao extends Serializable{
	
	/**
	   * 将给定的持久化对象实例插入到数据库中
	   * @param entity Object
	   * @return Serializable
	   */
	Object insert(final Object entity);
	
	/**
	   * 根据id删除某个持久化对象的实例
	   * @param entityClass Class
	   * @param id Serializable
	   */
	  void delete(final Class<?> entityClass, final Serializable id);
	  
	  /**
	   * 将给定的持久化对象实例更新到数据库中
	   * @param entity Object
	   */
	  void update(final Object entity);
	  
	  /**
	   * 根据id获得某个持久化对象的实例，如果不存在，则返回null
	   * @param entityClass Class
	   * @param id Serializable
	   * @return Object
	   */
	  Object find(final Class<?> entityClass, final Serializable id);

	  
		/**
		 * 获得持久化对象的实例数
		 * 
		 * @param entityClass
		 *            Class
		 * @return int
		 */
		int count(final Class entityClass);

		/**
		 * 获得符合查询条件的持久化对象的实例数
		 * 
		 * @param queryString
		 *            String
		 * @return int
		 */
		int count(final String queryString);
		
		/**
		 * 获得符合查询条件的持久化对象的实例数
		 * 
		 * @param queryString
		 * @param params
		 * @return
		 */	
		int count(final String queryString, Object[] params);
		
		
		void batchInsertOrUpdate(Collection entities);
		/**
		 * 由于很多情况下只有一个参数，故封装未知个参数的查询方法，使用起来更灵活简单
		 * @param hql
		 * @param param 查询参数
		 * @return 符合条件的结果集列表
		 * @author Weishaoying
		 */
		List findByParam(String hql, Object... param);
		
		
		/**
		 * 通过指定类型、hql、查询参数查询唯一实体对象
		 * @param <T>
		 * @param clazz
		 * @param hql
		 * @param param
		 * @return
		 * @author Weishaoying
		 */
		<T> T findUniqueObjByParam(Class<T> clazz, String hql, Object... param);
		
		/**
		 * 如果hql是多个对象属性的拼接即不需要明确指定数据类型时使用此方法
		 * @param hql
		 * @param param
		 * @return
		 */
		Object findUniqueObjByParam(String hql, Object... param);
		
		
		List findByParams(String hql, Object[] params);

		/**
		 * 根据条件执行查询，返回一个Iterator对象
		 * 
		 * @param queryString
		 *            String
		 * @return Iterator
		 */
		Iterator iterate(final String queryString);

		/**
		 * 获得持久化对象的全部实例
		 * 
		 * @param entityClass
		 *            Class
		 * @return List
		 */
		List query(final Class entityClass);

		/**
		 * 获得符合查询条件的持久化对象的全部实例
		 * 
		 * @param queryString
		 *            String
		 * @return List
		 */
		List query(final String queryString);

		
		/**
		 * 通过sql和指定参数查询:适用于业务逻辑比较复杂或sql依赖于底层数据库的情况
		 * @param sqlStr sql语句
		 * @param params 参数
		 * @return
		 * WeiShaoying 
		 */
		List queryBySqlWithParams(String sqlStr, Object...params);
		
		/**
		 * 功能：通过hql和指定参数查询不带分页
		 * @author Weishaoying
		 * @version 1.0
		 * @param hqlStr
		 * @param params
		 * @return
		 */
		List queryByHqlWithParams(String hqlStr, Object...params);
		
		
		//List queryUniqueObjBySql(String sqlStr, Object...params);
		
		/**
		 * 根据实体类的属性获得符合条件的全部实例
		 * 
		 * @param entityClass
		 *            Class
		 * @param entity
		 *            Object
		 * @return List
		 */
		List query(final Class entityClass, final Object entity);

		/**
		 * 根据实体类的属性获得符合条件的全部实例，可以选择是否模糊匹配和忽略大小写
		 * 
		 * @param entityClass
		 *            Class
		 * @param entity
		 *            Object
		 * @param enableLike
		 *            boolean
		 * @param ignoreCase
		 *            boolean
		 * @return List
		 */
		List query(final Class entityClass, final Object entity,
				boolean enableLike, boolean ignoreCase);

		/**
		 * 获得给定页码的持久化对象的实例
		 * 
		 * @param entityClass
		 *            Class
		 * @param pageNo
		 *            int
		 * @param pageSize
		 *            int
		 * @return List
		 */
		List query(final Class entityClass, int pageNo, int pageSize);

		/**
		 * 获得符合查询条件的给定页码的持久化对象的实例
		 * 
		 * @param queryString
		 *            String
		 * @param pageNo
		 *            int
		 * @param pageSize
		 *            int
		 * @return List
		 */
		List query(final String queryString, int pageNo, int pageSize);
		
		/**
		 *  获得符合查询条件的给定页码的持久化对象的实例
		 * @param queryString
		 * @param params
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		List query(final String queryString,Object[] params, int pageNo, int pageSize);
		
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
		List queryBySqlWithPage(String queryString, int pageNo, int pageSize, Object... params);

		/**
		 * 将给定的持久化对象实例插入或更新到数据库中
		 * 
		 * @param entity
		 *            Object
		 */
		@Transactional
		void saveOrUpdate(final Object entity);

		/**
		 * 批量更新持久化对象
		 * 
		 * @param updateString
		 *            String 批量更新的hql语句，可以是update，也可以是delete
		 * @return int 被批量更新的持久化对象数量
		 */
		int batchUpdate(String updateString);

		/**
		 * 批量更新持久化对象，带参数
		 * 
		 * @param updateString
		 *            String 批量更新的hql语句，可以是update，也可以是delete，带一个参数
		 * @param entity
		 *            Object 参数值
		 * @return int 被批量更新的持久化对象数量
		 */
		int batchUpdate(String updateString, Object entity);

		/**
		 * 批量更新持久化对象，带参数
		 * 
		 * @param updateString
		 *            String 批量更新的hql语句，可以是update，也可以是delete，带多个参数
		 * @param entities
		 *            Object[] 参数值数组
		 * @return int 被批量更新的持久化对象数量
		 */
		int batchUpdate(String updateString, Object[] entities);

		/**
		 * 通过sql语句查询，获得结果列表
		 * 
		 * @param sqlString
		 *            String
		 * @return List author :wanghao
		 */
		List queryBySql(final String sqlString);

		int updateByHql(final String updateString);
		int updateByHql(final String updateString,Object[] params);
		int updateBySql(final String updateString,Object[] params);
		/**
		 * 功能：通过hql分页查询结果列表，参数个数未知，使用时不用刻意创建数组或list
		 * @author Weishaoying
		 * @version 1.0
		 * @param hql
		 * @param pageNo
		 * @param pageSize
		 * @param params
		 * @return
		 */
		List queryByHqlWithPage(String hql, int pageNo, int pageSize, Object... params);

		void saveOrUpdateAndFlush(final Object entity);
		void insertAndFlush(final Object entity);
		
		
		/**
		 * 功能：获取session在service中使用session的相关方法
		 * @author Weishaoying
		 * @version 1.0
		 * @return
		 */
		public Session getSess();


		/**
		 * 将给定的对象以merge的方法把信息更新到数据库
		 * @param entity
		 */
		public void merge(final Object entity);
		/**
	     * 取得Q_GLOBAL_ID的下一个值
	     * @return
	     */
	    public Long getSeq();
}
