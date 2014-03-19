package service.impl;

import java.util.ArrayList;

import pojos.Department;
import service.DepartmentService;
import dao.DepartmentDao;

public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService{
	//必须要有该属性，并且与applicationContext.xml中的配置相对应
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public String deleteDept(String departmentIds) {
		/**如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4.... */
		String[] str=departmentIds.split(",");
		ArrayList<Department> departments= new ArrayList<Department>();
		String name="";
		/**遍历id，并实例化类型，在add到List */
		for(int i=0;i<str.length;i++){
			Department department = this.departmentDao.find(Integer.parseInt(str[i]));
//			/** 因为Department与其他实体类不存在多对一的关系，即数据表中没有外键，所有新建对象只许设置id即可 */
//			department.setDepartmentId(Integer.parseInt(str[i]));
			if(department.getStaffs().size()>0){
				if(i==0){
					name+=department.getDepartmentName();
				}else {
					name=name+"、"+department.getDepartmentName();
				}
			}else {
				departments.add(department);
			}
		}
		if (name=="") {
			this.departmentDao.removeAll(departments);
		}
		return name;
	}
	
}
