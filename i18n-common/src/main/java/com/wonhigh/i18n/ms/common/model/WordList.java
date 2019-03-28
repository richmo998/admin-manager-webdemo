package com.wonhigh.i18n.ms.common.model;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class WordList {
    private Integer id;

    private String wordType;

    private String sysGroup;

    private String zhCn;

    private String zhHk;

    private String zhEn;

    private String createUser;

    private Integer createUserId;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType == null ? null : wordType.trim();
    }

    public String getSysGroup() {
        return sysGroup;
    }

    public void setSysGroup(String sysGroup) {
        this.sysGroup = sysGroup == null ? null : sysGroup.trim();
    }

    public String getZhCn() {
        return zhCn;
    }

    public void setZhCn(String zhCn) {
        this.zhCn = zhCn == null ? null : zhCn.trim();
    }

    public String getZhHk() {
        return zhHk;
    }

    public void setZhHk(String zhHk) {
        this.zhHk = zhHk == null ? null : zhHk.trim();
    }

    public String getZhEn() {
        return zhEn;
    }

    public void setZhEn(String zhEn) {
        this.zhEn = zhEn == null ? null : zhEn.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
       /* return "WordList{" +
                "id=" + id +
                ", wordType='" + wordType + '\'' +
                ", sysGroup='" + sysGroup + '\'' +
                ", zhCn='" + zhCn + '\'' +
                ", zhHk='" + zhHk + '\'' +
                ", zhEn='" + zhEn + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';*/
//        return "【"+sysGroup+"】，【"+zhCn+"】，【"+zhHk+"】，【"+zhEn+"】";
        return "【"+sysGroup+"】，【"+zhCn+"】，【"+zhHk+"】，"+ (StringUtils.isBlank(zhEn)?"【】":"【"+zhEn+"】");
    }
}