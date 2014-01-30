package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供一个Json日期值的转换. <p>
 *
 * @author： hzg ,政企软件中心 - 项目一部，广东数据通信网络有限公司.
 * @since： 2009-3-3下午04:59:44
 * @modify:
 * @主要功能：
 * @see com.gdcn.core.utils.JsonUtils
 * @version 1.0
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

         private static Log logger = LogFactory.getLog(DateJsonValueProcessor.class);
         
        /** * 默认的日期转换格式. */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /** * 日期转换器. */
    private DateFormat dateFormat;

    /**
     * 构造器，设置默认日期格式.
     * @param datePattern
     */
    public DateJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            logger.error(ex);
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }
   
    /**
     * 转换数组值.
     * @param value 对象.
     * @param jsonConfig json 配置.
     */
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }


    public Object processObjectValue(String key, Object value,
            JsonConfig jsonConfig) {
            return process(value);
        }
   

        private Object process(Object value) {
        try {
            return dateFormat.format((Date) value);
        } catch (Exception ex) {
                logger.error(ex);
            return null;
        }
    }

}


