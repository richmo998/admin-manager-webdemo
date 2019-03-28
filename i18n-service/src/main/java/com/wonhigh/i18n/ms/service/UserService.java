package com.wonhigh.i18n.ms.service;

import java.util.List;
import java.util.Map;

import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.service.BaseCrudService;

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
public interface UserService extends BaseCrudService {
	
	UserEntity login(String userName, String password) throws ServiceException;

    String getMenuData(String basePath) throws ServiceException;

    /**
     * 重载获取菜单方法
     * 可根据角色进行动态加载
     * @param basePath
     * @param userEntity 用户信息
     * @return
     * @throws ServiceException
     */
    String getMenuData(String basePath,UserEntity userEntity) throws ServiceException;
    
    int updateUser(UserEntity user)throws ServiceException;
    
    int getUserValid(UserEntity user) throws ServiceException;
    
    /**
     * 获取用户手机号码列表
     *
     *
     * @since 2017年3月17日 下午5:56:22
     * 
     * @param params
     * @throws ServiceException
     * @return List<UserEntity>
     */
    List<UserEntity> listPhone(Map<String, Object> params) throws ServiceException;
    
    
    public UserEntity getUser(String userName) throws ServiceException;

    /**
     * 聚合用户以及角色信息
     * @param page
     * @param orderByField :需要排序的字段
     * @param orderType ：排序的方式 DESC或ASC
     * @param params
     * @return
     * @throws ServiceException
     */
    List<UserEntity> selectUserAndRoleByPage(SimplePage page, String orderByField, String orderType,Map<String, Object> params)throws  ServiceException;
    
}