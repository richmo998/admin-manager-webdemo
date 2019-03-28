package com.wonhigh.i18n.ms.service.role;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.utils.Base64Utils;
import com.wonhigh.i18n.ms.dal.database.RoleMapper;
import com.wonhigh.i18n.ms.dal.database.UserMapper;
import com.wonhigh.i18n.ms.service.UserService;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
@Service("roleService")
class RoleServiceImpl extends BaseCrudServiceImpl implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleMapper roleMapper;

	@Override
	public BaseCrudMapper init() {
		return roleMapper;
	}

	@Override
	public int updateRole(RoleEntity role) throws ServiceException {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public int insertRole(RoleEntity role) throws ServiceException {
		return roleMapper.insertSelective(role);
	}

	@Override
	public int deleteRoleByPrimaryKey(Integer roleId) throws ServiceException {
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public RoleEntity getRole(String roleName) throws ServiceException {
		RoleEntity role = null;
		try {
			 role = roleMapper.getRole(roleName);
		} catch (DaoException e) {
			logger.error("获取角色对象数据库出错【{}】",e);
			throw new ServiceException("获取角色对象数据库出错",e);
		}
		return role;
	}

	@Override
	public RoleEntity getRoleByRoleId(Integer roleId) throws ServiceException {
		RoleEntity role = null;
		try {
			role = roleMapper.selectByPrimaryKey(roleId);
		} catch (DaoException e) {
			logger.error("获取角色对象数据库出错【{}】",e);
			throw new ServiceException("获取角色对象数据库出错",e);
		}
		return role;
	}

	@Override
	public List<RoleEntity> getRoleList(RoleEntity roleEntity,Map<String,Object> params) throws ServiceException {
		return roleMapper.selectByParams(roleEntity,params);
	}

}