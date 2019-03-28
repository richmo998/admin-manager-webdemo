package com.wonhigh.i18n.ms.service.role;

import com.wonhigh.i18n.ms.common.model.RoleAllocationEntity;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.dal.database.RoleAllocationMapper;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("roleAllocationService")
class RoleAllocationServiceImpl extends BaseCrudServiceImpl implements RoleAllocationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleAllocationServiceImpl.class);

	@Autowired
	RoleAllocationMapper roleAllocationMapper;

	@Override
	public BaseCrudMapper init() {
		return roleAllocationMapper;
	}

	@Override
	public int updateRoleAllocation(RoleAllocationEntity role) throws ServiceException {
		return roleAllocationMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public int insertRoleAllocation(RoleAllocationEntity role) throws ServiceException {
		return roleAllocationMapper.insertSelective(role);
	}

	@Override
	public int deleteRoleAllocationByPrimaryKey(Integer id) throws ServiceException {
		return roleAllocationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public RoleAllocationEntity getRoleAllocation(String roleName) throws ServiceException {
		RoleAllocationEntity role = null;
		try {
			role = roleAllocationMapper.getRoleAllocation(roleName);
		} catch (DaoException e) {
			logger.error("获取角色分配菜单数据库出错【{}】",e);
			throw new ServiceException("获取角色分配菜单数据库出错",e);
		}
		return role;
	}

	@Override
	public List<RoleAllocationEntity> getRoleAllocationList(RoleAllocationEntity role, Map<String, Object> parms) throws ServiceException {
		return roleAllocationMapper.selectByParams(role,parms);
	}
}