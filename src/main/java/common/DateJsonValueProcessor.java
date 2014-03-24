/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:common.DateJsonValueProcessor
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-21     caiwenming       v1.0.0         create
 *
 */
package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 * @auhtor Lingo
 * @since 2007-08-02
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    /** * 默认的日期转换格式. */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /** * 日期转换器. */
    private DateFormat dateFormat;

    /**
     * 构造方法.
     *
     * @param datePattern 日期格式
     */
    public DateJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }

    /**
     * 转换数组？.
     *
     * @param value Object
     * @return Object
     */
    public Object processArrayValue(Object value, JsonConfig arg1) {
        return process(value);
    }

    /**
     * 转换对象.
     *
     * @param key String
     * @param value Object
     * @return Object
     */
    public Object processObjectValue(String key, Object value, JsonConfig arg1) {
        return process(value);
    }

    /**
     * 格式化日期.
     *
     * @param value Object
     * @return Object
     */
    private Object process(Object value) {
        try {
            return dateFormat.format((Date) value);
        } catch (Exception ex) {
            return null;
        }
    }

}
