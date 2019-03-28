package com.wonhigh.i18n.ms.common.vo.api;

import java.io.Serializable;

/**
 * 对外API 公共参数
 * 
 * @author tan.yf
 * @date 2017年6月6日 上午10:57:40
 * @version 0.1.0 
 * @copyright wonhigh.cn
 */
public class PublicApiVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 系统分配的应用Key */
	private String appKey;
	
	/** 随机数 和 时间戳结合防止接口回放  */
	private String nonce;
	
	/** 签名信息 */
	private String sign;
	
	/** 时间毫秒（UTC时间）*/
	private long timestamp;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
