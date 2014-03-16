package web.ui;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import net.sf.json.JsonConfig;

import common.ObjectJsonValueProcessor;

import pojos.Cost;
import pojos.Department;
import pojos.Staff;

public class CostDepUI {
	private static String[] columnMethods = new String[]{"getCostId","getRecordDate","getStaff","getExecuteDate","getPayWay","getCurrency","getMoney","getCostUnitName","getCostCountry","getCostProvince","getCostAddress","getCostContactName","getCostContactPosition","getCostContactPhone","getCostContactEmail","getUsage1","getDescription1"};

	
	public static Vector<String> getHead(){
		Vector<String> head=new Vector<String>();
		head.add("记录编号");
		head.add("记录时间");
		head.add("员工编号");
		head.add("员工姓名");
		head.add("支出日期");
		head.add("支出方式");
		head.add("币种");
		head.add("支出金额");
		head.add("国家");
		head.add("省市");
		head.add("详细地区");
		head.add("相关单位名称");
		head.add("联系人姓名");
		head.add("联系人职务");
		head.add("联系人电话");
		head.add("联系人邮箱");
		head.add("用途");
		head.add("描述");
		return head;
	}
	
	public static List<Vector<String>> getDataList(List<Cost> costs) throws Exception{
		List<Vector<String>> dataList=new Vector<Vector<String>>();
		for(int i=0;i<costs.size();i++){
			Cost cost =costs.get(i);
			Vector<String> oneRow=new Vector<String>();
			/** 利用反射机制循环获取数据 */
			for(int j=0;j<columnMethods.length;j++){
				Method method;
				String value;
				Object object;
				if(j==2)
				{
					method =cost.getClass().getMethod(columnMethods[j]);
					object=method.invoke(cost);
					Staff staff= (Staff) object;
					String StaffId=staff.getStaffId()+"";
					String StaffName=staff.getStaffName();
					oneRow.add(StaffId);
					oneRow.add(StaffName);
//					sonConfig jsonConfig =new JsonConfig();
//					jsonConfig.registerJsonValueProcessor(Staff.class, new ObjectJsonValueProcessor(new String[]{"staffId","staffName"}, Staff.class));
//						
				}
				else{
					method =cost.getClass().getMethod(columnMethods[j]);
					object=method.invoke(cost);	
					if(object==null){
						value="";
					}else {
						value=object.toString();
					}
//					if(j==4){
//						value=GetPayWay(Integer.parseInt(object.toString())-1);
//					}
//					if(j==5){
//						value=GetCurrency(Integer.parseInt(object.toString())-1);
//					}
					oneRow.add(value);
				}										
			}
			dataList.add(oneRow);
		}
		return dataList;
	}
	
//	public static String GetCurrency(int i)
//	{
//		String value;
//		String[] pay=new String[]{"人民币","美元","英镑","欧元","港元","加元","日元","澳大利亚元","瑞士法郎","法国法郎"};
//		value=pay[i];
//		return value;
//	}
//	
//	public static String GetPayWay(int i)
//	{
//		String value;
//		String[] pay=new String[]{"现金","银行转账","汇款","支票","本票","汇票","汇兑"};
//		value=pay[i];
//		return value;
//	}
}