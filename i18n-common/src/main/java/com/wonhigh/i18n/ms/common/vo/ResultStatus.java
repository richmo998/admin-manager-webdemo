package com.wonhigh.i18n.ms.common.vo;

import com.wonhigh.i18n.ms.common.constants.ApiConstants;

/**
 * 自定义返回状态码，所有前端返回，请在这里定义状态码
 */
public enum ResultStatus {
	/**
	 * 公共
	 */
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    BAD_REQUEST(400,"参数错误"),
    
    /** 公共参数  */
    PUBLIC_APP_KEY_NOT_NULL(551,"appKey不能为空"),
    PUBLIC_SIGN_NOT_NULL(552,"sign签名信息不能为空"),
    PUBLIC_TIMESTAMP_NOT_NULL(553,"timestamp不能为空"),
    PUBLIC_TIMESTAMP_BEYOND_THE_LIMIT(554,"timestamp超出限制"),
    PUBLIC_TIMESTAMP_INVALID(555,"timestamp格式错误"),
    PUBLIC_APP_KEY_NOT_FOUND(556,"appKey不存在"),
    PUBLIC_SIGN_INVALID(557,"签名信息错误"),
    
    /** 600 */
    SMS_RECEIVEPHONES_NOT_EMPTY(ApiConstants.SMS+1,"收信人不能为空"),
    SMS_CONTENT_NOT_EMPTY(ApiConstants.SMS+2,"短信内容不能为空"),
    SMS_CONTENT_LENGTH_OVERRUN(ApiConstants.SMS+3,"短信内容长度超限"),
    SMS_SCHEDULETIME_INVALID_FORMAT(ApiConstants.SMS+4,"计划发送时间格式错误"),
    SMS_PHONE_NUMBER_INVALID(ApiConstants.SMS+5,"手机号码无效"),
    SMS_BIZCODE_NOT_EMPTY(ApiConstants.SMS+6,"业务编码不能为空"),
    SMS_PRIORITY_NUMBER_INVALID(ApiConstants.SMS+23,"优先级类型错误"),
    SMS_SENDER_NOT_EMPTY(ApiConstants.SMS+24,"发送人不能为空"),
    SMS_SMSTYPE_NUMBER_INVALID(ApiConstants.SMS+25,"短信类型类型错误"),
    SMS_PHONE_NUMBER_MAX(ApiConstants.SMS+30,"手机号码数量不能超过200"),
    
    
    SMS_ISRESEND_NUMBER_INVALID(ApiConstants.SMS+26,"是否重发类型【0,1】错误"),
    SMS_ISFILTER_NUMBER_INVALID(ApiConstants.SMS+27,"是否替换内容类型错误"),
    SMS_MSG_NOT_EMPTY(ApiConstants.SMS+28,"短信内容不能为空"),
    SMS_BIZCODE_LENGHTH_INVALID(ApiConstants.SMS+29,"业务编码长度不能大于4"),
    SMS_PRIORITY_MIN_MAX_INVALID(ApiConstants.SMS+29,"优先级必须【0~20】之内"),
    
    SMS_QUERY_BEGINTIME_INVALID(ApiConstants.SMS+7,"开始时间格式错误"),
    SMS_QUERY_ENDTIME_INVALID(ApiConstants.SMS+8,"结束时间格式错误"),
    SMS_QUERY_PRIORITY_INVALID(ApiConstants.SMS+9,"优先级类型错误"),
    SMS_QUERY_SENDSTATUS_INVALID(ApiConstants.SMS+10,"发送状态类型错误"),
    SMS_QUERY_PAGE_INVALID(ApiConstants.SMS+11,"页码类型错误"),
    SMS_QUERY_PERPAGE_INVALID(ApiConstants.SMS+12,"每页的记录数类型错误"),
    SMS_QUERY_RECEIVEPHONE_LIMIT(ApiConstants.SMS+13,"手机号码个数超出限制"),
    
    
    VOICE_ISRESEND_NUMBER_INVALID(ApiConstants.SMS+30,"是否播放次数类型错误"),
    VOICE_MSG_NUMBER_MAX(ApiConstants.SMS+31,"语音内容长度超限【100个】"),
    VOICE_PRIORITY_NUMBER(ApiConstants.SMS+32,"优先级必须为数字"),
    
    
    MAIL_MAIN_ADDR_NOT_EMPTY(ApiConstants.SMS+28,"收件人不能为空")
    
    
    ;
    
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
