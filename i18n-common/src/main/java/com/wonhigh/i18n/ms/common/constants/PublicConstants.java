package com.wonhigh.i18n.ms.common.constants;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 常量
 * 
 * @author wang.w
 * @date 2014-6-11 下午5:40:02
 * @version 0.9.1
 * @copyright richmo998
 */
public class PublicConstants {

	// 短信的时间格式
	public static final String DATE_FORMAT_1 = "yyyyMMddhhmmss";

	// 程序功能开关
	public static final String MODULE_FUNCTION_BUTTON_CLOSE = "0";
	public static final String MODULE_FUNCTION_BUTTON_OPEN = "1";

	public static final String SESSION_USER = "session_user";

    public static final String SESSION_USER_BIZTYPE = "session_user_biztype";	
    public static final String SUCCESS = "success";
	public static final String MESSAGE = "message";

	public static final Byte USER_STATUS_YES = 1;
	public static final Byte USER_STATUS_NO = 0;

	/**本机ip最后一段     '192.168.15.25'--'25'**/
	public static String IMPORT_PART_IP_HOST;

	/**存储产生4位随机数--不重复**/
	public static Set<Integer> NO_REPEAT_RANDOM_FOUR_SET= new ConcurrentSkipListSet<Integer>();


    /**
     * 操作日志类型
     */
	public static final String LOG_OPERATION_ADD = "ADD";
	public static final String LOG_OPERATION_DELETE = "DELETE";
	public static final String LOG_OPERATION_UPDATE = "UPDATE";
	public static final String LOG_OPERATION_QUERY = "QUERY";


    /**
     * 中文繁体
     */
    public static final int  CONVERT_TYPE_HK = 0;


}
