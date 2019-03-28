package com.wonhigh.i18n.ms.dal.database;

import java.util.List;
import java.util.Map;

import com.yougou.logistics.base.common.utils.SimplePage;
import org.apache.ibatis.annotations.Param;

import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;

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
public interface UserMapper extends BaseCrudMapper {
	
	UserEntity getUser(@Param("userName") String userName);
	
	/**
	 * 获取用户手机号码列表
	 *
	 *
	 * @since 2017年3月17日 下午5:59:02
	 * 
	 * @param params
	 * @throws DaoException
	 * @return List<UserEntity>
	 */
	List<UserEntity> listPhone(@Param("params") Map<String, Object> params) throws DaoException;

	List<UserEntity> selectUserAndRoleByPage(@Param("params") Map<String, Object> params) throws DaoException;}