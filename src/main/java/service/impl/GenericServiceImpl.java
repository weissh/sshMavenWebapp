package service.impl;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import dao.GenericDao;
import service.GenericService;
/**
 * 
 * @author Abu
 *
 * @param <T>
 * @param <ID>
 */
public class GenericServiceImpl<T> implements
		GenericService<T> {
	private GenericDao<T> genericDao;
	public List<T> findAll() {		
		return genericDao.findAll();
	}

	public List<T> findByPage(int page, int size) {		
		return genericDao.findByPage(page, size);
	}
	public int getPageSize(int size) {		
		return genericDao.getPageSize(size);
	}
	public int getTotalRows() {		
		return genericDao.getTotalRows();
	}
	public void modify(T entity) {
		genericDao.modify(entity);		
	}
	public void remove(T entity) {
		genericDao.remove(entity);
	}
	public void removeAll(Collection<T> entities) {
		genericDao.removeAll(entities);		
	}
	
	public T save(T entity) {		
		return genericDao.save(entity);
	}
	
	public GenericDao<T> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao<T> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return this.genericDao.findByProperty(propertyName, value);
	}
	@Override
	public List findBysql(String sql) {
		// TODO Auto-generated method stub
		return this.genericDao.findBySql(sql);
	}
	@Override
	public List findByExample(T instance) {
		// TODO Auto-generated method stub
		return this.genericDao.findByExample(instance);
	}
	@Override
	public List<T> findByPage(int page, int size, String sql) {
		// TODO Auto-generated method stub
		return this.genericDao.findByPage(page, size, sql);
	}
	@Override
	public int getTotalRows(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return this.genericDao.getTotalRows(propertyName, value);
	}
	@Override
	public void attachDirty(T instance) {
		// TODO Auto-generated method stub
		this.genericDao.attachDirty(instance);
	}
	@Override
	public void attachClean(T instance) {
		// TODO Auto-generated method stub
		this.genericDao.attachClean(instance);
	}
	@Override
	public T merge(T detachedInstance) {
		// TODO Auto-generated method stub
		return this.genericDao.merge(detachedInstance);
	}	
}