package com.wonhigh.i18n.ms.common.constants;

/**
 * 通用常量
 */
public class ApiConstants {
    //签名算法HmacSha256
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String MD5 = "MD5";
    //编码UTF-8
    public static final String ENCODING = "UTF-8";
    //UserAgent
    public static final String USER_AGENT = "sms/java";
    //换行符
    public static final String LF = "\n";
    //串联符
    public static final String SPE1 = ",";
    //示意符
    public static final String SPE2 = ":";
    //连接符
    public static final String SPE3 = "&";
    //赋值符
    public static final String SPE4 = "=";
    //问号符
    public static final String SPE5 = "?";
    //默认请求超时时间,单位毫秒
    public static final int DEFAULT_TIMEOUT = 1000;
    //参与签名的系统Header前缀,只有指定前缀的Header才会参与到签名中
    public static final String CA_HEADER_TO_SIGN_PREFIX_SYSTEM = "X-Ca-";
    
    
    /** 公共参数字段  */
    public static final String APP_KEY = "appKey";
    public static final String APP_SECRET = "appSecret";
    public static final String TIMESTAMP = "timestamp";
    public static final String SIGN = "sign";
    /** 接口用户 */
    public static final int API_USER_TYPE = 1;
    
	/** 接口过期时间 */
	public static final long API_EXPIRATION_TIME = 60*1000*10L;
	/** 默认短信优先级 */
	public static final int DEFAULT_SMS_PRIORITY = 1;
	/** 默认每页显示条数  */
	public static final int DEFAULT_PAGE_SIZE = 20;
	/** 每页显示最大条数  */
	public static final int MAX_PAGE_SIZE = 200;
	
	
	/**  SMS 模块 错误码 从 600  */
	public static final int SMS  = 6_00;

    /**
     * 业务码启用
     */
    public static final String  API_BIZCODE_ENABLE = "1";
    /**
     * 业务码禁用
     */
    public static final String API_BIZCODE_DISENABLE = "0";
}
