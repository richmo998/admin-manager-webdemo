package com.wonhigh.i18n.ms.common.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 系统最小级菜单属性
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/10/19 15:44
 * @copyright richmo998
 * @description:
 */
public class SubMenuEntity implements Serializable {

    //            {
//                "parentId": null,
//                    "menuLevel": null,
//                    "state": "open",
//                    "systemId": null,
//                    "systemName": null,
//                    "childrenCount": 0,
//                    "menuCode": null,
//                    "attributes": {
//                "url": "qSmsSender/index"
//            },
//                "id": 2653,
//                    "text": "短信明细查询",
//                    "remark": null,
//                    "order": null,
//                    "isLeaf": null,
//                    "children": null
//            }

    /**
     * 父id
     */
    private String parentId;
    /**
     * 子菜单id
     */
    private String id;
    /**
     * 菜单界别
     */
    private String menuLevel;
    /**
     * 状态
     */
    private String state="open";
    /**
     *系统id
     */
    private String systemId;
    /**
     *系统名称
     */
    private String systemName;
    /**
     * 下级菜单总数
     */
    private String childrenCount;
    /**
     * 菜单code
     */
    private String menuCode;
    /**
     *菜单属性
     */
    private JSONObject attributes;
    /**
     *菜单名称
     */
    private String text;
    /**
     *备注
     */
    private String remark;
    /**
     *是否叶子
     */
    private String isLeaf;
    /**
     *子菜单
     */
    private String children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(String childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SubMenuEntity{" +
                "parentId='" + parentId + '\'' +
                ", id='" + id + '\'' +
                ", menuLevel='" + menuLevel + '\'' +
                ", state='" + state + '\'' +
                ", systemId='" + systemId + '\'' +
                ", systemName='" + systemName + '\'' +
                ", childrenCount='" + childrenCount + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", attributes=" + attributes +
                ", text='" + text + '\'' +
                ", remark='" + remark + '\'' +
                ", isLeaf='" + isLeaf + '\'' +
                ", children='" + children + '\'' +
                '}';
    }
}
