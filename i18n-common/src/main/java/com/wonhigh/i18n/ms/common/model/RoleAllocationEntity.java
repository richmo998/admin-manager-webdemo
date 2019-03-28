package com.wonhigh.i18n.ms.common.model;

import java.io.Serializable;
import java.util.Date;

/**
角色分配表
 */
public class RoleAllocationEntity implements Serializable {
    private static final long serialVersionUID = 6208863603382483625L;
//       <id column="id" property="id" jdbcType="INTEGER" />
//		<result column="role_id" property="roleId" jdbcType="INTEGER" />
//		<result column="role_name" property="roleName" jdbcType="VARCHAR" />
//		<result column="uri" property="uri" jdbcType="VARCHAR" />
//		<result column="menu_name" property="menuName" jdbcType="VARCHAR" />
//		<result column="enable" property="enable" jdbcType="VARCHAR" />
//		<result column="remark" property="remark" jdbcType="VARCHAR" />
//		<result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
//		<result column="update_time" property="updateTime" jdbcType="TIMESTAMP" />


    private Integer id;
    private Integer roleId;
    private String roleName;
    private String uri;
    private String menuName;
    private String enable;
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        return "RoleAllocationEntity{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", uri='" + uri + '\'' +
                ", menuName='" + menuName + '\'' +
                ", enable='" + enable + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}