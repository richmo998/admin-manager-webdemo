package com.wonhigh.i18n.ms.manager.role;

import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.manager.BaseCrudManager;

import java.util.List;
import java.util.Map;

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
public interface RoleManager extends BaseCrudManager {

    int updateRole(RoleEntity role)throws ManagerException;
    int insertRole(RoleEntity role)throws ManagerException;
    int deleteRoleByPrimaryKey(Integer roleId)throws ManagerException;
    public RoleEntity getRole(String roleName) throws ManagerException;
    public RoleEntity getRoleByRoleId(Integer roleId) throws ManagerException;
    public List<RoleEntity> getRoleList(RoleEntity roleEntity, Map<String, Object> parms) throws ManagerException;
    public List<RoleEntity> queryRoleListForPage(Map<String, Object> paramMap, int pageNo, int pageSize) throws ManagerException ;
}