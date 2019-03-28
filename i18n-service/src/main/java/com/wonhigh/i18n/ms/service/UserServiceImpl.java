package com.wonhigh.i18n.ms.service;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.RoleAllocationEntity;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.common.model.SubMenuEntity;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.utils.Base64Utils;
import com.wonhigh.i18n.ms.dal.database.RoleAllocationMapper;
import com.wonhigh.i18n.ms.dal.database.RoleMapper;
import com.wonhigh.i18n.ms.dal.database.UserMapper;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
@Service("userService")
class UserServiceImpl extends BaseCrudServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Resource
    private UserMapper userMapper;

    @Autowired
	private RoleMapper roleMapper;

    @Autowired
	private RoleAllocationMapper roleAllocationMapper ;


	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
    public BaseCrudMapper init() {
        return userMapper;
    }

	@Override
	public UserEntity login(String userName, String password) throws ServiceException {
		UserEntity userObj = userMapper.getUser(userName);
		if (userObj == null) {
            throw new ServiceException("用户不存在！");
        }
        String userPassword = userObj.getUserPwd();
        if (userObj.getStatus().equals(PublicConstants.USER_STATUS_NO)) {
        	throw new ServiceException("此用户已被禁用！");
		}
        if (!password.equals(Base64Utils.decryptor(userPassword))) {
            throw new ServiceException("用户密码不正确！");
        }
        return userObj;
	}

	@Override
	public String getMenuData(String basePath) throws ServiceException {
		JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new InputStreamReader(new FileInputStream(basePath + "/resources/common/menu.json"),"utf-8"));
            org.json.simple.JSONArray jsonArray  = (org.json.simple.JSONArray ) obj;
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException("读取菜单数据错误！",e);
        }
	}

    @Override
    public String getMenuData(String basePath, UserEntity userEntity) throws ServiceException {
        JSONParser parser = new JSONParser();
        try {
            //根据用户角色动态生成左侧菜单
//            if(null == userEntity || "admin".equalsIgnoreCase(userEntity.getUserName()) || null ==userEntity.getRoleId()){
//                //默认以及管理员加载所有菜单
//                Object obj = parser.parse(new InputStreamReader(new FileInputStream(basePath + "/resources/common/menu.json"),"utf-8"));
//                org.json.simple.JSONArray jsonArray  = (org.json.simple.JSONArray ) obj;
//                return jsonArray.toString();
//            }

            //其他角色根据模板配置动态生成
//            Object obj = parser.parse(new InputStreamReader(new FileInputStream(basePath + "/resources/common/menuModle.json"),"utf-8"));
//            JSONArray jsonArray  = (JSONArray ) obj;
            JSONArray jsonArray = getBaseMenuJSON(null);
            //1.根据用户角色查出所有分配属性
			Map<String ,Object> parms = new HashMap<>();
			parms.put("role_id",userEntity.getRoleId());
			List<RoleEntity> list = roleMapper.selectByParams(null, parms);

			if(null == list || list.isEmpty()){
				logger.info("根据角色id【{}】查询不到任务角色数据，请检查",userEntity.getRoleId());
				return jsonArray.toString();
			}

			String roleName = list.get(0).getRoleName();
			parms.clear();
			parms.put("roleName",roleName);
			List<RoleAllocationEntity> roleAllocationEntityList = roleAllocationMapper.selectByParams(null, parms);
			if(null == roleAllocationEntityList || roleAllocationEntityList.isEmpty()){
				logger.info("根据角色名称【{}】查询不到任务角色分配菜单数据，请检查",roleName);
				return jsonArray.toString();
			}

			logger.info("当前角色【{}】所有分配的菜单【{}】",roleName,roleAllocationEntityList.toString());
            jsonArray = getBaseMenuJSON(roleAllocationEntityList);
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException("读取菜单数据错误！",e);
        }
    }

    private JSONArray getBaseMenuJSON(List<RoleAllocationEntity> roleAllocationEntityList ){
        JSONArray rootJSONArray = new JSONArray();
        JSONObject rootJOSNObject = new JSONObject();
//        "parentId": 1,
//                "menuLevel": 2,
//                "state": "open",
//                "systemId": 111,
//                "systemName": null,
//                "childrenCount": 0,
//                "menuCode": null,
//                "attributes": {},
//        "id": 111,
//                "text": "多语言词库管理系统",
//                "remark": null,
//                "order": 1,
//                "isLeaf": "false",
//                "children": []
        //根菜单
        rootJOSNObject.put("parentId","1");
        rootJOSNObject.put("menuLevel","2");
        rootJOSNObject.put("state","open");
        rootJOSNObject.put("systemId","11");
        rootJOSNObject.put("id",111);
        rootJOSNObject.put("text","多语言词库管理系统");
        rootJOSNObject.put("order",1);
        rootJOSNObject.put("isLeaf","false");
        rootJOSNObject.put("text","多语言词库管理系统");

        List childrens = new ArrayList();
        JSONObject secondJOSNObject = new JSONObject();
//        "parentId": 111,
//                "menuLevel": null,
//                "state": "open",
//                "systemId": 111,
//                "systemName": "多语言词库管理系统",
//                "childrenCount": 0,
//                "menuCode": "1",
//                "attributes": {},
//        "id": 54999,
//                "text": "系统管理",
//                "remark": "",
//                "order": 1,
//                "isLeaf": "true",
//                "children": []
        secondJOSNObject.put("parentId","111");
        secondJOSNObject.put("state","open");
        secondJOSNObject.put("systemId","111");
        secondJOSNObject.put("systemName","多语言词库管理系统");
        secondJOSNObject.put("menuCode","1");
        secondJOSNObject.put("id",54999);
        secondJOSNObject.put("text","系统管理");
        secondJOSNObject.put("order",1);
        secondJOSNObject.put("text","系统管理");
        secondJOSNObject.put("isLeaf","true");
        //动态载入业务菜单
        if(null != roleAllocationEntityList && roleAllocationEntityList.size()>0){
            List<SubMenuEntity> menuList= new ArrayList<>();
            for(RoleAllocationEntity entity:roleAllocationEntityList){
                SubMenuEntity subMenuEntity = new SubMenuEntity();
                subMenuEntity.setId(System.currentTimeMillis()+"");
                subMenuEntity.setState("open");

                subMenuEntity.setText(entity.getMenuName());
                subMenuEntity.setRemark(entity.getRemark());

                JSONObject attr = new JSONObject();
                attr.put("url",entity.getUri());
                subMenuEntity.setAttributes(attr);
                menuList.add(subMenuEntity);
            }
            secondJOSNObject.put("children",menuList);
            //所有业务菜单加载完毕
        }

        childrens.add(secondJOSNObject);
        rootJOSNObject.put("children",childrens);
        rootJSONArray.put(rootJOSNObject);
        return rootJSONArray;
    }

    @Override
    public int updateUser(UserEntity user) throws ServiceException {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int getUserValid(UserEntity user) throws ServiceException {
		String username=user.getUserName();
		
		if("".equals(username)||username==null){
			return -1;
		}
	 UserEntity uses=	userMapper.getUser(username);
	 if(uses==null){
		 return 0;
	 }  
		return 1;
	}

	@Override
	public List<UserEntity> listPhone(Map<String, Object> params)
			throws ServiceException {
		try {
			return this.userMapper.listPhone(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserEntity getUser(String userName) throws ServiceException{
		try {
			return userMapper.getUser(userName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

    @Override
    public List<UserEntity> selectUserAndRoleByPage(SimplePage page, String orderByField, String orderType, Map<String, Object> params) throws ServiceException {
        try {
            params.put("startNo",page.getStartRowNum());
            params.put("size",page.getPageSize());
            params.put("orderByField",orderByField);
            params.put("orderType",orderType);
            return userMapper.selectUserAndRoleByPage(params);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}