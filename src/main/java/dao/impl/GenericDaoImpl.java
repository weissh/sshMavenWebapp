package dao.impl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
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
public class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {
	// 具体的实体类型
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
	 * @param type :
	 *            实体类型
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
	 * @param hibernateTemplate :
	 *            Spring提供的Hibernate工具类
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
    
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T) hibernateTemplate.get(type, id);
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
	public ID save(T entity) {
		return (ID) hibernateTemplate.save(entity);
	}
	public int getTotalRows() {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"));
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
}