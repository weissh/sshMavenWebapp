/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:common.ObjectJsonValueProcessor
 * @description:避免出现hibernate死循环，用于过滤掉引起死循环的对象或对象中引起死循环的字段（即保留有用字段）；
 * 改变所有时间型字段为"yyy--MM--dd"
 * 
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-9     caiwenming       v1.0.0         create
 *
 */
package common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ObjectJsonValueProcessor implements JsonValueProcessor {
    
	private static Log logger = LogFactory.getLog(ObjectJsonValueProcessor.class);
	
    /** 默认的日期转换格式 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	
	/** 日期转换器 */
    private DateFormat dateFormat;
    
    /** 需要留下的字段数组 */
	private String [] properties=null;
	
	
    /** 需要做处理的复杂属性类型  */
	private Class<?> clazz;
	
	/**
	 * 构造函数
	 * @param datePattern
	 */
	public ObjectJsonValueProcessor(String datePattern){
        try {
            this.dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            logger.error(ex);
            this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
	}
	
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
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		/** 当没有过滤对象或对象的字段时，直接调用process，将时间型的字段改为"yyyy---MM--dd"；
		 *  否则，将过滤对象或字段转换成json格式，其中是时间型的字段也需改为"yyyy---MM--dd"
		*/
		if(properties==null){
			return process(value);
		}else{
			PropertyDescriptor propertyDescriptor=null;
			Method method=null;
			StringBuffer json=new StringBuffer("{");
			try{
				for(int i=0;i<this.properties.length;i++){
					propertyDescriptor=new PropertyDescriptor(properties[i], clazz);
					method=propertyDescriptor.getReadMethod();
					String v=String.valueOf(process(method.invoke(value)));
					json.append("'"+properties[i]+"':'"+v+"'");
					json.append(i!=properties.length-1?",":"");
				}
				json.append("}");
			}catch (Exception e) {
				e.printStackTrace();
			}
			return JSONObject.fromObject(json.toString());
		}
	}

	/**
	 *
	 * @Description:将时间型字段的格式改为"yyy--MM--dd"
	 *
	 * @param value
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午10:50:45
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	private Object process(Object value) {
	    try {
	        return dateFormat.format((Date) value);
	    } catch (Exception ex) {
	            logger.error(ex);
	        return value;
	    }
	}
}
