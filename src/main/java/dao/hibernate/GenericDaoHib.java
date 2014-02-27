/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:dao.hibernate.GenericDaoHib
 * @description:实现数据访问，即数据库的增、删、改、查；并且支持泛型（不同的Pojo），确定具体的Pojo后即可操作相应的数据表
 * 
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-9     caiwenming       v1.0.0         create
 *
 */
package dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.dao.DataAccessException;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.SystemException;

import dao.GenericDao;

public class GenericDaoHib<T> extends HibernateDaoSupport implements GenericDao<T> {

	Logger log=Logger.getLogger(GenericDaoHib.class);
	
	/** 具体的实体类型 */
	private Class<T> type;
	
	/** 查询条件 */
	private String hql;

	/**
	 * 构造方法
	 * @param type
	 */
	public GenericDaoHib(Class<T> type) {
		this.type = type;
		this.hql = "from " + type.getName();	
	}
	
	/** 因为这个类没有继承HibernateDaoSupport,所以现在由Spring注入HibernateTemplate */
	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getHql() {
		return hql;
	}
	
	@Override
	/**
	 *
	 * @Description:根据id查找相应的记录,必须指定是哪个实体类
	 *
	 * @param clazz 某个Pojo类
	 * @param id 对象的id
	 * 
	 * @return T，取决于传入的Class参数
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 *
	 */
	public T find(int id){
		return (T)getHibernateTemplate().get(type, id);
	}

	@SuppressWarnings("unchecked")
	/**
	 *
	 * @Description:查找所有的记录
	 *
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findAll() {
		String hql = "from " + type.getName();
		return (List<T>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 *
	 * @Description:根据某个属性值查找
	 *
	 * @param propertyName 某个属性名称
	 * @param value 该属性的值
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findByProperty(String propertyName, Object value) {
		String hql = "from " + type.getName()+" as model where model."+propertyName + "= ?";
		return (List<T>) getHibernateTemplate().find(hql, value);
	}

	@SuppressWarnings("unchecked")
	/**
	 *
	 * @Description:不支持主键、不支持关联、不支持null、对于基本类型将会默认作为查询条件
	 * 
	 * @param instance 不含主键、外键、null的实例对象
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findByExample(T instance) {
		List<T> results = getHibernateTemplate().findByExample(instance);
		return results;
	}
	
	@Override
	/**
	 *
	 * @Description:根据某个属性查找
	 *
	 * @param propertyName 某个属性名称
	 * @param value 该属性的值
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	@Override
	/**
	 *
	 * @Description:根据某个实例对象删除相应的数据库记录
	 *
	 * @param entity 某个的实例对象
	 * 
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	@Override
	/**
	 *
	 * @Description:根据一个实例对象集，删除相应的数据库所有记录
	 *
	 * @param entities 一个实例对象集
	 * 
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public void removeAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);		
	}
	
	@Override
	/**
	 *
	 * @Description:根据一个实例对象保存
	 *
	 * @param entity 某个的实例对象
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public int save(T entity) {
		if(entity==null)
			throw new SystemException("对象为空！");
		try{
			return (Integer) getHibernateTemplate().save(entity);
		}catch (DataAccessException e) {
			log.error("保存方法发生错误:",e);
			throw new SystemException("保存方法发生错误:",e);
		}
	}
	
	@Override
	/**
	 *
	 * @Description:查找某个数据表中所有记录的条数
	 *
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public int getTotalRows() {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"));
		return ((Long) this.getHibernateTemplate().find(actualHql).get(0))
				.intValue();
	}
	
	
	/**
	 *
	 * @Description:获取某个查找操作返回结果的条数
	 * 
	 * @param sql 查询语句
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public int getTotalRows(String sql) {
		return findBySql(sql).size();
	}
	
	@Override
	/**
	 *
	 * @Description:获取根据某个属性查找出的记录的条数
	 * 
	 * @param propertyName 某个属性名称
	 * @param value 该属性的值
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public int getTotalRows(String propertyName,Object value) {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"))+" where "+ propertyName + " like %"+value+"%";
		return ((Long) this.getHibernateTemplate().find(actualHql).get(0))
				.intValue();
	}
	
	@Override
	/**
	 *
	 * @Description:用于前端分页，获取分页的总页数
	 * 
	 * @param size 一页显示数据的条数
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public int getPageSize(int size) {
		/** 最大页数 */
		int pageSize;
		/** 实际每页数据条数 */
		int actualSize;
		/** 总记录数 */
		int totalRows = this.getTotalRows();
		/** 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数 */
		actualSize = (size > totalRows) ? totalRows : size;
		if (totalRows > 0) {
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize)
					: (totalRows / actualSize + 1);
		} else {
			pageSize = 0;
		}
		return pageSize;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 *
	 * @Description:实现前端分页
	 * 
	 * @param page 页面的总数
	 * @param size 每个页面包含的数据条数
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findByPage(final int page, final int size) {
		final int pageSize = this.getPageSize(size);
		final int totalRows = this.getTotalRows();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				/** 实际页码 */
				int actualPage = (page > pageSize) ? pageSize : page;
				/** 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数 */
				int actualSize = (size > totalRows) ? totalRows : size;
				/** 计算请求页码的第一条记录的索引值 */
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize
						: 0;
				Query query = session.createQuery(hql);
				/** 设置第一条记录 */
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				return (List<T>) query.list();
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 *
	 * @Description:实现前端分页
	 * 
	 * @param page 页面的总数
	 * @param size 每个页面包含的数据条数
	 * @param sql 查询语句
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findByPage(final int page,final int size,final String sql) {
		final int pageSize = this.getPageSize(size);
		final int totalRows = this.getTotalRows();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				/** 实际页码 */
				int actualPage = (page > pageSize) ? pageSize : page;
				/** 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数 */
				int actualSize = (size > totalRows) ? totalRows : size;
				/** 计算请求页码的第一条记录的索引值,如果 */
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize
						: 0;
				/** Query query = session.createSQLQuery(sql+" limit "+startRow+" , "+ actualSize); */
				/** 设置第一条记录 */
				Query query = session.createQuery(sql);
				/** 设置第一条记录 */
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				return (List<T>) query.list();
//				return session.createSQLQuery(sql+" limit "+startRow+" , "+ actualSize).addEntity(type.getName()).list();
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 *
	 * @Description:根据sql语句进行查询
	 * 
	 * @param sql 查询语句
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public List<T> findBySql(final String sql) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(sql).list();
			}
		});
	}

	@Override
	/**
	 *
	 * @Description: 将传入的对象持久化并保存。 
	 * 如果对象未保存（Transient状态），调用save方法保存。如果对象已保存（Detached状态），
	 * 调用update方法将对象与Session重新关联。 
	 * 
	 * @param instance 某个实例对象
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public void attachDirty(T instance) {
		try {
			this.getHibernateTemplate().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	/**
	 *
	 * @Description: 将传入的对象状态设置为Transient状态 
	 * 
	 * @param instance 某个实例对象
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public void attachClean(T instance) {
		try {
			this.getHibernateTemplate().lock(instance, LockMode.NONE);
			//log.debug("attach successful");
		} catch (RuntimeException re) {
			//log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	/**
	 *
	 * @Description: 将传入的detached状态的对象的属性复制到持久化对象中，并返回该持久化对象。 
	 * 如果该session中没有关联的持久化对象，加载一个，如果传入对象未保存，保存一个副本并作为持久对象返回，
	 * 传入对象依然保持detached状态。 
	 * 
	 * @param instance 某个实例对象（瞬时状态）
	 * 
	 * @return
	 *
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 * 
	 */
	public T merge(T detachedInstance) {
		try {
			T result = (T) this.getHibernateTemplate().merge(
					detachedInstance);
			//log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			//log.error("merge failed", re);
			throw re;
		}
	}
	
}
