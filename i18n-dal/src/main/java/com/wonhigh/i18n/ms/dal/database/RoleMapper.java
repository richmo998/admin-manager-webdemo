package com.wonhigh.i18n.ms.dal.database;

import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import org.apache.ibatis.annotations.Param;

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
public interface RoleMapper extends BaseCrudMapper {

	RoleEntity getRole(@Param("roleName") String roleName) throws DaoException;

	RoleEntity selectByPrimaryKey(Integer roleId) throws DaoException;

}