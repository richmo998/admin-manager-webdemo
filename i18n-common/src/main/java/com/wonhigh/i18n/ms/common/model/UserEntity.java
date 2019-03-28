package com.wonhigh.i18n.ms.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 请写出类的用途 
 * @author user
 * @date  2016-02-16 12:03:47
 * @version 1.0.0
 * @copyright (C) 2013 WonHigh Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the WonHigh technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
public class UserEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8427872896454170518L;

	/**
     * 
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 登录名
     */
    private String loginName;
    
    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户状态：0-禁用；1-正常
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;
    
    /**
     * 0:后台用户,1:接口用户
     */
    private Integer userType;
    /**
     * 用户角色id
     */
    private Integer roleId;

    /**
     * 用户角色
     */
    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 
     * {@linkplain #userId}
     *
     * @return the value of t_user.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * {@linkplain #userId}
     * @param userId the value for t_user.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     * {@linkplain #userName}
     *
     * @return the value of t_user.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * {@linkplain #userName}
     * @param userName the value for t_user.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * {@linkplain #userPwd}
     *
     * @return the value of t_user.user_pwd
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 
     * {@linkplain #userPwd}
     * @param userPwd the value for t_user.user_pwd
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 
     * {@linkplain #status}
     *
     * @return the value of t_user.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 
     * {@linkplain #status}
     * @param status the value for t_user.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 
     * {@linkplain #remark}
     *
     * @return the value of t_user.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * {@linkplain #remark}
     * @param remark the value for t_user.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * {@linkplain #createTime}
     *
     * @return the value of t_user.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * {@linkplain #createTime}
     * @param createTime the value for t_user.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * {@linkplain #updateTime}
     *
     * @return the value of t_user.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * {@linkplain #updateTime}
     * @param updateTime the value for t_user.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", phone='" + phone + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userType=" + userType +
                ", roleId=" + roleId +
                '}';
    }
}