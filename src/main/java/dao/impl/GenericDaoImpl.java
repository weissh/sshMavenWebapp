package dao.impl;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


import dao.GenericDao;
/**
 * 
 * @author Abu
 *
 * @param <T>
 * @param <ID>
 */
public class GenericDaoImpl<T> implements
		GenericDao<T> {
	// 具体的实体类型
	//	private static final Logger log = LoggerFactory.getLogger(StudentDaoImpl.class);
	private Class<T> type;
	// Spring提供的Hibernate工具类
	private HibernateTemplate hibernateTemplate;
	// 查询条件
	private String hql;
	

	/**
	 * <p>
	 * 必须提供的构造方法,以便创建实例的时候就知道具体实体的类型
	 * <p>
	 * 
	 * @param type :实体类型
	 */
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
		this.hql = "from " + type.getName();	
	}
	/**
	 * <p>
	 * 因为这个类没有继承HibernateDaoSupport,所以现在由Spring注入HibernateTemplate
	 * </p>
	 * 
	 * @param hibernateTemplate :Spring提供的Hibernate工具类
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public String getHql() {
		return hql;
	}
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "from " + type.getName();
		return (List<T>) hibernateTemplate.find(hql);
	}
    
	
	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		String hql = "from " + type.getName()+" as model where model."+propertyName + "= ?";
		return this.hibernateTemplate.find(hql, value);
	}
	
	@Override
	public List findByExample(T instance) {
		// TODO Auto-generated method stub
		List results = hibernateTemplate.findByExample(instance);
		return results;
	}
	
	public void modify(T entity) {
		hibernateTemplate.update(entity);
	}
	public void remove(T entity) {
		hibernateTemplate.delete(entity);
	}
	
	public void removeAll(Collection<T> entities) {
		hibernateTemplate.deleteAll(entities);		
	}
	@SuppressWarnings("unchecked")
	public T save(T entity) {
		return (T) hibernateTemplate.save(entity);
	}
	public int getTotalRows() {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"));
		return ((Long) this.hibernateTemplate.find(actualHql).get(0))
				.intValue();
	}
	
	public int getTotalRows(String sql) {
		
		return findBySql(sql).size();
	}
	
	public int getTotalRows(String propertyName,Object value) {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"))+" where "+ propertyName + " like %"+value+"%";
		return ((Long) this.hibernateTemplate.find(actualHql).get(0))
				.intValue();
	}
	
	public int getPageSize(int size) {
		// 最大页数
		int pageSize;
		// 实际每页数据条数
		int actualSize;
		// 总记录数
		int totalRows = this.getTotalRows();
		// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
		actualSize = (size > totalRows) ? totalRows : size;
		if (totalRows > 0) {
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize)
					: (totalRows / actualSize + 1);
		} else {
			pageSize = 0;
		}
		return pageSize;
	}
	@SuppressWarnings("unchecked")
	public List<T> findByPage(final int page, final int size) {
		final int pageSize = this.getPageSize(size);
		final int totalRows = this.getTotalRows();
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 实际页码
				int actualPage = (page > pageSize) ? pageSize : page;
				// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
				int actualSize = (size > totalRows) ? totalRows : size;
				// 计算请求页码的第一条记录的索引值,如果
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize
						: 0;
				Query query = session.createQuery(hql);
				// 设置第一条记录
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				return (List<T>) query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(final int page,final int size,final String sql) {
		// TODO Auto-generated method stub
		final int pageSize = this.getPageSize(size);
		final int totalRows = this.getTotalRows();
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 实际页码
				int actualPage = (page > pageSize) ? pageSize : page;
				// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
				int actualSize = (size > totalRows) ? totalRows : size;
				// 计算请求页码的第一条记录的索引值,如果
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize
						: 0;
				//Query query = session.createSQLQuery(sql+" limit "+startRow+" , "+ actualSize);
				// 设置第一条记录
				return session.createSQLQuery(sql+" limit "+startRow+" , "+ actualSize).addEntity(type.getName()).list();
			}
		});
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List findBySql(final String sql) {
		// TODO Auto-generated method stub
		return hibernateTemplate.executeFind(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).addEntity(type.getName()).list();
			}
		});
	}
	@Override
	/** 
	* 将传入的对象持久化并保存。 
	* 如果对象未保存（Transient状态），调用save方法保存。如果对象已保存（Detached状态），调用update方法将对象与Session重新关联。 
	*/  
	public void attachDirty(T instance) {
		// TODO Auto-generated method stub
		try {
			this.getHibernateTemplate().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	@Override
	/** 
	* 将传入的对象状态设置为Transient状态 
	*/
	public void attachClean(T instance) {
		// TODO Auto-generated method stub
		try {
			this.getHibernateTemplate().lock(instance, LockMode.NONE);
			//log.debug("attach successful");
		} catch (RuntimeException re) {
			//log.error("attach failed", re);
			throw re;
		}
	}
	@Override
	// 关于merge() attachDirty() attachClean()三种方法下面做一个简单的介绍  
	/** 
	* 将传入的detached状态的对象的属性复制到持久化对象中，并返回该持久化对象。 
	* 如果该session中没有关联的持久化对象，加载一个，如果传入对象未保存，保存一个副本并作为持久对象返回，传入对象依然保持detached状态。 
	*/  
	public T merge(T detachedInstance) {
		// TODO Auto-generated method stub
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