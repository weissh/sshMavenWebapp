/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.StaffUI
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-19     caiwenming       v1.0.0         create
 *
 */
package web.ui.excel;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import web.ui.model.StaffModel;

public class StaffUI {
	/** 需要导出的字段的get方法名，需与pojo中的一致 */
	private static String[] columnMethods = new String[] { "getStaffId",
			"getStaffName", "getDepartmentName", "getRoleName", "getEntryTime",
			"getPosition", "getPhone", "getEmail", "getUrgentContact",
			"getUcPhone", "getGender", "getNationality", "getPoliticalStatus",
			"getAge", "getBirthday", "getMaritalStatus", "getIdNo",
			"getPassportNo", "getNativePlace", "getDomicilePlace",
			"getDateOfRecruitment", "getCurrentAddress", "getZipCode",
			"getGraduateSchool", "getHightestEdu", "getHightestDegree",
			"getMajor", "getSchoolSystem", "getUserName", "getPassword" };

	/**
	 * 
	 * @Description: 设置导出的excel表的列名
	 * 
	 * @return 列名（向量）
	 * 
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-15 上午8:28:03
	 * 
	 *                 Modification History: Date Author Version Description
	 *                 ----
	 *                 ------------------------------------------------------
	 *                 ------- 2014-2-15 caiwenming v1.0.0 create
	 */
	public static Vector<String> getHead() {
		Vector<String> head = new Vector<String>();
		head.add("员工编号");
		head.add("员工姓名");
		head.add("部门名称");
		head.add("部门名称");
		head.add("入职时间");
		head.add("职位");
		head.add("手机号码");
		head.add("邮箱");
		head.add("紧急联系人");
		head.add("紧急联系人手机");
		head.add("性别");
		head.add("国籍");
		head.add("政治面貌");
		head.add("年龄");
		head.add("出生日期");
		head.add("婚姻状况");
		head.add("身份证号");
		head.add("护照号");
		head.add("籍贯");
		head.add("户口地址");
		head.add("参加工作时间");
		head.add("现居住地");
		head.add("邮编");
		head.add("毕业院校");
		head.add("最高学历");
		head.add("最高学位");
		head.add("专业");
		head.add("学制");
		head.add("用户名");
		head.add("密码");
		return head;
	}

	/**
	 * 
	 * @Description: 将得到的数据列表转换成向量型的数据
	 * 
	 * @param staffModels
	 *            （List）部门数据集
	 * @return 向良型的部门数据集
	 * @throws Exception
	 * 
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-15 上午8:28:50
	 * 
	 *                 Modification History: Date Author Version Description
	 *                 ----
	 *                 ------------------------------------------------------
	 *                 ------- 2014-2-15 caiwenming v1.0.0 create
	 */
	public static List<Vector<String>> getDataList(List<StaffModel> staffModels)
			throws Exception {
		List<Vector<String>> dataList = new Vector<Vector<String>>();
		for (int i = 0; i < staffModels.size(); i++) {
			StaffModel staffModel = staffModels.get(i);
			Vector<String> oneRow = new Vector<String>();
			/** 利用反射机制循环获取数据 */
			for (int j = 0; j < columnMethods.length; j++) {
				Method method;
				String value;
				method = staffModel.getClass().getMethod(columnMethods[j]);
				Object object = method.invoke(staffModel);
				if (object == null) {
					value = "";
				} else {
					value = object.toString();
				}
				oneRow.add(value);
			}
			dataList.add(oneRow);
		}
		return dataList;
	}
}
