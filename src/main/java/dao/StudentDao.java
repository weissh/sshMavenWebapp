package dao;


import dao.GenericDao;

import entity.*;

public interface StudentDao extends GenericDao<Student, Integer>{
	/**
	 * <p>
     * 根据用户名精确查找
	 * </p>
	 * @param uname : 用户名
	 * @return : 匹配的实体
	 */
	public Student findByNameExact(String uname);
}