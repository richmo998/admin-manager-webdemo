package com.wonhigh.i18n.ms.service.role;

import com.wonhigh.i18n.ms.common.model.RoleAllocationEntity;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.service.BaseCrudService;

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
public interface RoleAllocationService extends BaseCrudService {

    int updateRoleAllocation(RoleAllocationEntity role)throws ServiceException;
    int insertRoleAllocation(RoleAllocationEntity role)throws ServiceException;
    int deleteRoleAllocationByPrimaryKey(Integer id)throws ServiceException;
    public RoleAllocationEntity getRoleAllocation(String roleName) throws ServiceException;
    public List<RoleAllocationEntity> getRoleAllocationList(RoleAllocationEntity role,Map<String, Object> parms) throws ServiceException;
    
}