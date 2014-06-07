/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.excel.VisitReportUI
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-5-27     caiwenming       v1.0.0         create
 *
 */
package web.ui.excel;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import web.ui.model.VisitReportModel;

public class VisitReportUI {
	/** 需要导出的字段的get方法名，需与pojo中的一致 */
	private static String[] columnMethods = new String[]{"getProjectNo","getRecordDate","getVisitDate","getVisitPlace","getVisitAim",
		"getCustomer","getPhone","getEmail","getStaffName","getDepartmentName","getCompany","getTicket","getAccommodation",
		"getTransportation","getMeals","getSubsidy","getNumber","getSum"};
	
	public static Vector<String> getHead(){
		Vector<String> head = new Vector<String>();
		head.add("项目编号");
		head.add("记录时间");
		head.add("出差日期");
		head.add("出差地点");
		head.add("出差目的");
		head.add("对方联系");
		head.add("电话");
		head.add("邮箱");
		head.add("报告人");
		head.add("部门");
		head.add("同行人");
		head.add("机票");
		head.add("住宿");
		head.add("当地交通");
		head.add("餐费");
		head.add("差补");
		head.add("人数");
		head.add("总计");
		
		return head;
	}
	
	public static List<Vector<String>> getDataList(List<VisitReportModel> reportModels) throws Exception{
		List<Vector<String>> dataList=new Vector<Vector<String>>();
		for(int i=0;i<reportModels.size();i++){
			VisitReportModel report =reportModels.get(i);
			Vector<String> oneRow=new Vector<String>();
			/** 利用反射机制循环获取数据 */
			for(int j=0;j<columnMethods.length;j++){
				Method method;
				String value;
				method = report.getClass().getMethod(columnMethods[j]);
				Object object = method.invoke(report);
				if(object == null){
					value = "";
				}else{
					if(object instanceof Date){
						Date date = (Date) object;
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						value = df.format(date);
					}else {
						value = object.toString();
					}
				}
				oneRow.add(value);
			}
			dataList.add(oneRow);
		}
		return dataList;
	}
}
