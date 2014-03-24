package service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 基本上与泛型DAO的通用接口一致,请参见GenericDao
 * <p>
 * 
 * @author Abu
 * 
 * @param <T> :持久化的实体Bean
 * @param <ID> :实体Bean的id
 */

public interface GenericService<T> {
	
	/**
	 * 保存实体
	 * 
	 * @param entity : 实体
	 * @return 保存后得到的id
	 */
	public int save(T entity);
	
	/**
	 * <p>
	 * 删除实体
	 * </p>
	 * @param entity : 实体
	 */
	public void remove(T entity);
	
	public List<T> findByProperty(String propertyName,Object value);
	
	public List<T> findBysql(String sql);
	
	public List<T> findByExample(T instance);
	
	/**
	 * <p>
	 * 删除实体集合
	 * </p>
	 * @param entities :实体
	 */
	public void removeAll(Collection<T> entities);
	
	/**
	 * <p>
	 * 修改实体
	 * </p>
	 * @param entity :实体
	 */
	public void update(T entity);
	
	/**
	 * <p>
	 * 通过名字查找
	 * </p>
	 * 
	 * @param id :id
	 * @return 找到的实体
	 */
	public T find(int id);
	
	/**
	 * <p>
	 * 查找全部实体
	 * <p>
	 * 
	 * @return 所有实体的列表
	 */
	public List<T> findAll();
	
	
	/**
	 * <p>
	 * 根据给定的hql语句进行分页查找
	 * <p>
	 * 
	 * @param page :要查询的页码
	 * @param size :每页记录条数
	 * @return 匹配的实体列表
	 */
	public List<T> findByPage(final int page, final int size);
	
	public List<T> findByPage(final int page,final int size,final String sql);
	
	/**
	 * <p>
	 * 计算匹配查询条件的记录总数,如果没有注入或者设置hql语句,将使用默认的查询语句返回数据库中所有记录
	 * </p>
	 * 
	 * @return 记录总数
	 */
	public int getTotalRows();
	
	public int getTotalRows(String sql);
	
	public int getTotalRows(String propertyName,Object value);
	/**
	 * <p>
	 * 根据每页记录的数量,计算出总的分页数
	 * </p>
	 * 
	 * @param size 每页记录的数量
	 * @return 分页总数
	 */
	public int getPageSize(int size);
	
	public void attachDirty(T instance);
	
	public void attachClean(T instance);
	
	public T merge(T detachedInstance);
}
