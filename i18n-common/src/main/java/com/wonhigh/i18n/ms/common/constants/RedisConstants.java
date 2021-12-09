package com.wonhigh.i18n.ms.common.constants;

/**
 * redis的Key的常量值
 */
public class RedisConstants {
	/**
	 * 调用成功后 ，【腾讯流水号--明细表ID】
	 * 		用于取到回执来修改明细状态
	 */
	public static final String SMS_CALL_SEND_SUCCESS = "SMS_CALL_SEND_SUCCESS_";
	
	/**
	 * 语音发送成功后 ，【腾讯流水号--明细表ID】
	 * 		用于腾讯回调来修改明细状态
	 */
	public static final String VOICE_CALL_SEND_SUCCESS = "VOICE_CALL_SEND_SUCCESS_";
	
	public static final String VOICE_CALL_SEND_FAIL = "VOICE_CALL_SEND_FAIL_";
	
	/**
	 * 语音发送失败重试次数
	 * 		用于腾讯回调来判断是否超过重试次数
	 */
	public static final String VOICE_CALL_RESEND_COUNT = "VOICE_CALL_RESEND_COUNT_";
	
	/**
	 * 【短信群发失败--重试次数】
	 *   用于保存群发失败的重试次数
	 */
	public static final String BATCH_RESEND_COUNT ="BATCH_RESEND_COUNT_";
	
	/**
	 * 【语音发送失败--重试次数】
	 *   用于保存语音发送失败的重试次数
	 */
	public static final String VOICE_RESEND_COUNT ="VOICE_RESEND_COUNT_";
	
	/**
	 * 【明细ID--重试次数】
	 *      1、用于保存重试明细的重试次数
	 *      2、组装的时候，需要重试会把重试次数置成0，此时可作为是否重试的标志
	 */
	public static final String RESEND_COUNT ="RESEND_COUNT_";
	
	/**
	 * 重试短信明细IdList队列
	 */
	public static final String RESEND_RECEIVERIDLIST_QUEUE="RESEND_RECEIVERIDLIST_QUEUE";
	
	/**
	 * 重试短信明细IdList队列
	 */
	public static final String RESEND_VOICE_RECEIVER_ID_QUEUE="RESEND_VOICE_RECEIVER_ID_QUEUE";
	
	/**
	 * 修改明细表的队列
	 */
	public static final String UPDATE_RECEIVER_QUEUE ="UPDATE_RECEIVER_QUEUE";
	/**
	 * 高优先级短信发送队列
	 */
	public static final String SEND_SMS_QUEUE_HIGH ="SEND_SMS_QUEUE_HIGH";
	/**
	 * 低优先级短信发送队列
	 */
	public static final String SEND_SMS_QUEUE_LOW ="SEND_SMS_QUEUE_LOW";
	
	/**
	 * 修改语音明细表的队列
	 */
	public static final String UPDATE_VOICE_RECEIVER_QUEUE ="UPDATE_VOICE_RECEIVER_QUEUE";
	
	/**
	 * 高优先级语音发送队列
	 */
	public static final String SEND_VOICE_QUEUE_HIGH ="SEND_VOICE_QUEUE_HIGH";
	/**
	 * 低优先级语音发送队列
	 */
	public static final String SEND_VOICE_QUEUE_LOW ="SEND_VOICE_QUEUE_LOW";
	
	/**
	 * 发送高优先级短信
	 */
	public static final int TYPE_SEND_HIGH = 0;
	/**
	 * 发送低优先级短信
	 */
	public static final int TYPE_SEND_LOW = 1;
	/**
	 * 重发短信
	 */
	public static final int TYPE_RESEND_LOW = 2;
}
