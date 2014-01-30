package common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import pojos.Department;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ObjectJsonValueProcessor implements JsonValueProcessor {
    
	/** 
     * 需要留下的字段数组 
     */ 
	private String [] properties;
	
	
    /** 
     * 需要做处理的复杂属性类型 
     */
	private Class<?> clazz;
	
    /** 
     * 构造方法,参数必须 
     * @param properties 
     * @param clazz 
     */	
	public ObjectJsonValueProcessor(String[] properties ,Class<?> clazz){
		this.properties=properties;
		this.clazz=clazz;
	}
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return "";
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		PropertyDescriptor propertyDescriptor=null;
		Method method=null;
		StringBuffer json=new StringBuffer("{");
		try{
			for(int i=0;i<this.properties.length;i++){
				propertyDescriptor=new PropertyDescriptor(properties[i], clazz);
				method=propertyDescriptor.getReadMethod();
				String v=String.valueOf(method.invoke(value));
				json.append("'"+properties+"':'"+v+"'");
				json.append(i!=properties.length-1?",":"");
			}
			json.append("}");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(json.toString());
	}

	public static void main(String[] args) {
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{}, Department.class));
	}
}
