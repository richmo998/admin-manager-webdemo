package com.wonhigh.i18n.ms.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务类型实体
 *
 *
 * @since 2017年3月29日 下午2:53:07
 *
 */
public class BizTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private long id;
	/**
	 * 业务编号
	 */
	private String bizNo;
	/**
	 * 业务名称
	 */
	private String bizName;
	/**
	 * 业务名称
	 */
	private String bizNameAndNo;
	/**
	 * 短信状态报告接收人
	 */
	private String msgReceiver;
	/**
	 * 描述信息
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 用户名对应t_user中的user_name
	 */
	private String userName;
	/**
	 * 是否启用，1:启用 0，禁用
	 */
	private String enable;
	/**
	 * 此业务可使用的短信类型
	 */
	private String smsType;
	/**
	 * 此业务可使用的签名
	 */
	private String signType;

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getBizNameAndNo() {
		return bizNameAndNo;
	}

	public void setBizNameAndNo(String bizNameAndNo) {
		this.bizNameAndNo = bizNameAndNo;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getMsgReceiver() {
		return msgReceiver;
	}

	public void setMsgReceiver(String msgReceiver) {
		this.msgReceiver = msgReceiver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}