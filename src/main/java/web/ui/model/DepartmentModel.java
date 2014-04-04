/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.DepartmentModel
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-26     caiwenming       v1.0.0         create
 *
 */
package web.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Department;

public class DepartmentModel {

	private Integer departmentId;
	private String departmentName;
	private Date createTime;
	private Integer managerId;
	private String managerName;
	private Integer totalStaff;
	private String description;
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getTotalStaff() {
		return totalStaff;
	}
	public void setTotalStaff(Integer totalStaff) {
		this.totalStaff = totalStaff;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public DepartmentModel(Department department){
		this.departmentId=department.getDepartmentId();
		this.departmentName = department.getDepartmentName();
		this.createTime=department.getCreateTime();
		this.managerId = department.getManagerId();
		this.managerName=department.getManagerName();
		this.totalStaff = department.getTotalStaff();
		this.description = department.getDescription();
	}
	
//	public void toDepartmentModel(Department department){
//		this.departmentId=department.getDepartmentId();
//		this.departmentName = department.getDepartmentName();
//		this.createTime=department.getCreateTime();
//		this.managerId = department.getManagerId();
//		this.managerName=department.getManagerName();
//		this.totalStaff = department.getTotalStaff();
//		this.description = department.getDescription();
//	}
	
	public static List<DepartmentModel> toDepartmentModels(List<Department> departments){
		List<DepartmentModel> departmentModels=new ArrayList<DepartmentModel>();
//		DepartmentModel departmentModel;
		for(int i=0;i<departments.size();i++){
			DepartmentModel departmentModel=new DepartmentModel(departments.get(i));
//			departmentModel =new DepartmentModel();
//			departmentModel.toDepartmentModel(departments.get(i));
			departmentModels.add(departmentModel);
		}
		return departmentModels;
	}
}
