package com.wonhigh.i18n.ms.common.model;

import java.util.Date;

public class WordListLog {
    private Integer id;

    private String wordType;

    private String sysGroup;

    private String zhCn;

    private String operation;

    private String exContent;

    private String content;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getExContent() {
        return exContent;
    }

    public void setExContent(String exContent) {
        this.exContent = exContent == null ? null : exContent.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        return "WordListLog{" +
                "id=" + id +
                ", wordType='" + wordType + '\'' +
                ", sysGroup='" + sysGroup + '\'' +
                ", zhCn='" + zhCn + '\'' +
                ", operation='" + operation + '\'' +
                ", exContent='" + exContent + '\'' +
                ", content='" + content + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}