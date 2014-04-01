
package web.ui.excel;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import pojos.Journal;
import pojos.Staff;
import web.ui.model.JournalModel;

public class JournalUI {
	/** 需要导出的字段的get方法名，需与pojo中的一致 */
	private static String[] columnMethods = new String[]{"getRecordDate","getStaffId","getStaffName",
		"getExecuteDate","getOperateMode","getUnitName","getCountry","getProvince","getAddress",
		"getContactObject","getLevel","getContactWay","getContactName","getContactPosition",
		"getContactPhone","getContactEmail","getStartTime","getEndTime","getWorkContent"};
	
	public static Vector<String> getHead(){
		Vector<String> head=new Vector<String>();
		head.add("记录时间");
		head.add("员工编号");
		head.add("员工姓名");
		head.add("工作日期");
		head.add("工作方式");
		head.add("单位名称");
		head.add("国家");
		head.add("省市");
		head.add("详细地址");
		head.add("客户/经销商");
		head.add("重要级别");
		head.add("联系途径");
		head.add("联系人姓名");
		head.add("联系人职务");
		head.add("联系人电话");
		head.add("联系人邮箱");
		head.add("开始时间");
		head.add("结束时间");
		head.add("商谈主要内容及结果");
		return head;
	}
	

	public static List<Vector<String>> getDataList(List<JournalModel> journals) throws Exception{
		List<Vector<String>> dataList=new Vector<Vector<String>>();
		for(int i=0;i<journals.size();i++){
			JournalModel journal =journals.get(i);
			Vector<String> oneRow=new Vector<String>();
			/** 利用反射机制循环获取数据 */
			for(int j=0;j<columnMethods.length;j++){
				Method method;
				String value;
				Object object;
				if(j==0||j==3){
					method =journal.getClass().getMethod(columnMethods[j]);
					object=method.invoke(journal);
					Date date=(Date) object;
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String datestring = df.format(date);
					if(object==null){
						value="";
					}else {
						value=datestring;
					}
					oneRow.add(value);
				}else{
					method =journal.getClass().getMethod(columnMethods[j]);
					object=method.invoke(journal);	
					if(object==null){
						value="";
					}else {
						value=object.toString();
					}
					oneRow.add(value);
				}
			}
			dataList.add(oneRow);
		}
		return dataList;
	}
}

