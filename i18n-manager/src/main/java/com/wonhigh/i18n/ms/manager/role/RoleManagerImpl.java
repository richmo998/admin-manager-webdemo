package com.wonhigh.i18n.ms.manager.role;

import com.mchange.v2.resourcepool.ResourcePool;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.utils.Base64Utils;
import com.wonhigh.i18n.ms.dal.database.RoleMapper;
import com.wonhigh.i18n.ms.service.role.RoleService;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.manager.BaseCrudManager;
import com.yougou.logistics.base.manager.BaseCrudManagerImpl;
import com.yougou.logistics.base.service.BaseCrudService;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Service("roleManager")
class RoleManagerImpl extends BaseCrudManagerImpl implements RoleManager {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleManagerImpl.class);

	@Autowired
	RoleService roleService;
	@Override
	public int updateRole(RoleEntity role) throws ManagerException {
		int i=0;
		try {
			i = roleService.updateRole(role);
		} catch (ServiceException e) {
			throw new ManagerException("更新角色表异常",e);
		}
		return i ;
	}

	@Override
	public int insertRole(RoleEntity role) throws ManagerException {
		int i=0;
		try {
			i = roleService.insertRole(role);
		} catch (ServiceException e) {
			throw new ManagerException("插入角色表异常",e);
		}
		return i ;
	}

	@Override
	public int deleteRoleByPrimaryKey(Integer roleId) throws ManagerException {
		int i=0;
		try {
			i = roleService.deleteRoleByPrimaryKey(roleId);
		} catch (ServiceException e) {
			throw new ManagerException("删除角色表异常",e);
		}
		return i ;
	}

	@Override
	public RoleEntity getRole(String roleName) throws ManagerException {
		RoleEntity role = null;
		try {
			 role = roleService.getRole(roleName);
		} catch (ServiceException e) {
			throw new ManagerException("获取单个角色异常",e);
		}
		return role;
	}

	@Override
	public RoleEntity getRoleByRoleId(Integer roleId) throws ManagerException {
		RoleEntity role = null;
		try {
			role = roleService.getRoleByRoleId(roleId);
		} catch (ServiceException e) {
			throw new ManagerException("获取单个角色异常",e);
		}
		return role;
	}

	@Override
	public List<RoleEntity> getRoleList(RoleEntity roleEntity, Map<String, Object> parms) throws ManagerException {
		List<RoleEntity> roleList =  null;
		try {
		   roleList = roleService.getRoleList(roleEntity, parms);
		} catch (Exception e) {
			throw new ManagerException("获取List角色异常",e);
		}
		return roleList;
	}
	@Override
	public List<RoleEntity> queryRoleListForPage(Map<String, Object> paramMap,int pageNo,int pageSize)
			throws ManagerException {
		List<RoleEntity> cacheList = new ArrayList<RoleEntity>();

		try {
			int total=roleService.findCount(paramMap);
			SimplePage page = new SimplePage(pageNo, pageSize, (int)total);

			List<RoleEntity> roleList = roleService.findByPage(page,
					paramMap.get("mySelectOrder") == null ? "" : paramMap.get("mySelectOrder").toString(), null,
					paramMap);
			for (RoleEntity entity : roleList) {
				String remark="";
				//remark="<a href='detail/to_user_operator?user_id="+entity.getUserId()+"'><b>修改</b></a>";
				remark="<a class='easyui-linkbutton' href='javascript:void(0)'  onclick='operator("+entity.getRoleId()+")'><b>修改</b></a>";
				entity.setRemark(remark);
				cacheList.add(entity);
			}

			return cacheList;
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}

	@Override
	protected BaseCrudService init() {
		return roleService;
	}
}