package com.wonhigh.i18n.ms.manager.role;

import com.wonhigh.i18n.ms.common.model.RoleAllocationEntity;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.dal.database.RoleAllocationMapper;
import com.wonhigh.i18n.ms.service.role.RoleAllocationService;
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
@Service("roleAllocationManager")
class RoleAllocationManagerImpl extends BaseCrudManagerImpl implements RoleAllocationManager {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleAllocationManagerImpl.class);

	@Autowired
	RoleAllocationService roleAllocationService;

	@Override
	public int updateRoleAllocation(RoleAllocationEntity role) throws ManagerException {
		int i=0;
		try {
			i = roleAllocationService.updateRoleAllocation(role);
		} catch (ServiceException e) {
			throw new ManagerException("更新角色分配表异常",e);
		}
		return i ;
	}

	@Override
	public int insertRoleAllocation(RoleAllocationEntity role) throws ManagerException {
		int i=0;
		try {
			i = roleAllocationService.insertRoleAllocation(role);
		} catch (ServiceException e) {
			throw new ManagerException("插入角色分配表异常",e);
		}
		return i ;
	}

	@Override
	public int deleteRoleAllocationByPrimaryKey(Integer id) throws ManagerException {
		int i=0;
		try {
			i = roleAllocationService.deleteRoleAllocationByPrimaryKey(id);
		} catch (ServiceException e) {
			throw new ManagerException("更新角色分配表异常",e);
		}
		return i ;
	}

	@Override
	public RoleAllocationEntity getRoleAllocation(String roleName) throws ManagerException {
		RoleAllocationEntity entity =null ;
		try {
			entity = roleAllocationService.getRoleAllocation(roleName);
		} catch (ServiceException e) {
			throw new ManagerException("获取单个角色分配异常",e);
		}
		return entity ;
	}

	@Override
	public List<RoleAllocationEntity> getRoleAllocationList(RoleAllocationEntity role, Map<String, Object> parms) throws ManagerException {
		List<RoleAllocationEntity>  roleAllocationList =null ;
		try {
			roleAllocationList = roleAllocationService.getRoleAllocationList(role, parms);
		} catch (ServiceException e) {
			throw new ManagerException("获取角色分配List异常",e);
		}
		return roleAllocationList ;
	}

	@Override
	public List<RoleAllocationEntity> queryRoleAllocationListForPage(Map<String, Object> paramMap, int pageNo, int pageSize)
			throws ManagerException {
		List<RoleAllocationEntity> cacheList = new ArrayList<RoleAllocationEntity>();

		try {
			int total=roleAllocationService.findCount(paramMap);
			SimplePage page = new SimplePage(pageNo, pageSize, (int)total);

			List<RoleAllocationEntity> roleList = roleAllocationService.findByPage(page,
					paramMap.get("mySelectOrder") == null ? "" : paramMap.get("mySelectOrder").toString(), null,
					paramMap);
			for (RoleAllocationEntity entity : roleList) {
				String remark="";
				//remark="<a href='detail/to_user_operator?user_id="+entity.getUserId()+"'><b>修改</b></a>";
				remark="<a class='easyui-linkbutton' href='javascript:void(0)'  onclick='operator("+entity.getId()+")'><b>修改</b></a>";
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
		return roleAllocationService;
	}
}