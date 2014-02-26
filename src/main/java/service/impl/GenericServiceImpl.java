package service.impl;

import java.util.Collection;
import java.util.List;

import service.GenericService;
import dao.GenericDao;

public class GenericServiceImpl<T> implements GenericService<T> {

	private GenericDao<T> genericDao;
	
	public GenericDao<T> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao<T> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	public int save(T entity) {
		return genericDao.save(entity);
	}

	@Override
	public void remove(T entity) {
		genericDao.remove(entity);
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		return genericDao.findByProperty(propertyName, value);
	}

	@Override
	public List<T> findBysql(String sql) {
		return genericDao.findBySql(sql);
	}

	@Override
	public List<T> findByExample(T instance) {
		return genericDao.findByExample(instance);
	}

	@Override
	public void removeAll(Collection<T> entities) {
		genericDao.removeAll(entities);
	}
	
	@Override
	public void update(T entity) {
		genericDao.update(entity);
	}

	public T find(int id){
		return genericDao.find(id);
	}
	
	@Override
	public List<T> findAll() {
		return genericDao.findAll();
	}

	@Override
	public List<T> findByPage(int page, int size) {
		return genericDao.findByPage(page, size);
	}

	@Override
	public List<T> findByPage(int page, int size, String sql) {
		return genericDao.findByPage(page, size, sql);
	}

	@Override
	public int getTotalRows() {
		return genericDao.getTotalRows();
	}

	@Override
	public int getTotalRows(String propertyName, Object value) {
		return genericDao.getTotalRows(propertyName, value);
	}

	@Override
	public int getPageSize(int size) {
		return genericDao.getPageSize(size);
	}

	@Override
	public void attachDirty(T instance) {
		genericDao.attachDirty(instance);
	}

	@Override
	public void attachClean(T instance) {
		genericDao.attachClean(instance);
	}

	@Override
	public T merge(T detachedInstance) {
		return genericDao.merge(detachedInstance);
	}

}
