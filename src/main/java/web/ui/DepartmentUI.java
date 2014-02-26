/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.DepartmentUI
 * @description:导出部门的excel表时，生成excel的第一行（数据标题）以及数据
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-12     caiwenming       v1.0.0         create
 *
 */
package web.ui;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import pojos.Department;

public class DepartmentUI {
	/** 需要导出的字段的get方法名，需与pojo中的一致 */
	private static String[] columnMethods = new String[]{"getDepartmentId","getDepartmentName","getManagerId","getTotalStaff","getCreateTime","getDescription"};
	
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
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-15    caiwenming      v1.0.0         create
	 */
	public static Vector<String> getHead(){
		Vector<String> head=new Vector<String>();
		head.add("部门编号");
		head.add("部门名称");
		head.add("部门经理");
		head.add("部门人数");
		head.add("成立时间");
		head.add("部门描述");
		return head;
	}
	
	/**
	 *
	 * @Description: 将得到的数据列表转换成向量型的数据
	 *
	 * @param departments （List）部门数据集
	 * @return 向良型的部门数据集
	 * @throws Exception
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-15 上午8:28:50
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-15    caiwenming      v1.0.0         create
	 */
	public static List<Vector<String>> getDataList(List<Department> departments) throws Exception{
		List<Vector<String>> dataList=new Vector<Vector<String>>();
		for(int i=0;i<departments.size();i++){
			Department department =departments.get(i);
			Vector<String> oneRow=new Vector<String>();
			/** 利用反射机制循环获取数据 */
			for(int j=0;j<columnMethods.length;j++){
				Method method;
				String value;
				method =department.getClass().getMethod(columnMethods[j]);
				Object object=method.invoke(department);
				if(object==null){
					value="";
				}else {
					value=object.toString();
				}
				oneRow.add(value);
			}
			dataList.add(oneRow);
		}
		return dataList;
	}
}
