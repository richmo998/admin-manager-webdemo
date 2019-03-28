package com.wonhigh.i18n.ms.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.utils.Base64Utils;
import com.wonhigh.i18n.ms.service.UserService;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.manager.BaseCrudManagerImpl;
import com.yougou.logistics.base.service.BaseCrudService;

/**
 * 
 * 
 * @author wang.w
 * @date 2014-6-10 下午2:08:55
 * @version 0.9.1 
 * @copyright richmo998
 */
@Service("userManager")
public class UserManagerImpl extends BaseCrudManagerImpl implements UserManager {

	
	
	private final static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

	@Autowired
	private UserService userService;

	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<UserEntity> findUserRecordList(Map<String, Object> paramMap,int pageNo,int pageSize)
			throws ManagerException {
		List<UserEntity> cacheList = new ArrayList<UserEntity>();
		
		try {
			int total=userService.findCount(paramMap);
			SimplePage page = new SimplePage(pageNo, pageSize, (int)total);
            List<UserEntity> userList =userService.findByPage(page,paramMap.get("mySelectOrder") == null ? "" : paramMap.get("mySelectOrder").toString(), null,
                    paramMap);
			for (UserEntity entity : userList) {

				String remark="";
				//remark="<a href='detail/to_user_operator?user_id="+entity.getUserId()+"'><b>修改</b></a>";
				remark="<a class='easyui-linkbutton' href='javascript:void(0)'  onclick='operator("+entity.getUserId()+")'><b>修改</b></a>";
				entity.setRemark(remark);
				cacheList.add(entity);
				String userpwd= Base64Utils.decryptor(entity.getUserPwd());
				entity.setUserPwd(userpwd);
			}

			return cacheList;
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	

	@Override
	public void modifyUserRecord(Map<String, Object> paramMap)
			throws ManagerException {
		UserEntity user=new UserEntity();
		String userId=(String) paramMap.get("userId");
		String userPwd=(String) paramMap.get("userPwd");
		String status=(String) paramMap.get("status");
		String userType=(String) paramMap.get("userType");

		if(null != paramMap.get("roleId") && !"".equals( paramMap.get("roleId"))){
			user.setRoleId(Integer.parseInt(paramMap.get("roleId").toString()));
		}

        if(userId!=null && !"".equals(userId)){
        	user.setUserId(Integer.parseInt(userId));
        }
        if(userPwd!=null && !"".equals(userPwd)){
        	user.setUserPwd(userPwd);
        }
        if(status!=null && !"".equals(status)){
        	user.setStatus((byte) Integer.parseInt(status));
        }
        if(userType !=null && !"".equals(userType)){
        	user.setUserType(Integer.parseInt(userType));
        }
		user.setUpdateTime(new Date(System.currentTimeMillis())); 
		try {
			userService.updateUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendCommonUser(UserEntity user) throws ManagerException {
	}

	@Override
	public void sendModelUser(UserEntity user) throws ManagerException {
	}

	@Override
	public void transferUserRecord2History(List<UserEntity> user)
			throws ManagerException {
	}

	@Override
	public void modifyUserFail2Init(Map<String, Object> paramMap)
			throws ManagerException {
	}

	@Override
	public void removeHistoryRecord(Map<String, Object> paramsMap)
			throws ManagerException {
	}

	@Override
	protected BaseCrudService init() {
		return userService;
	}

	public int addUserRecord(Map<String, Object> paramMap)
			throws ManagerException {
		
	    String  userName=	(String) paramMap.get("userName");
	    String  userPwd=	(String) paramMap.get("userPwd");
	    String  status =	(String) paramMap.get("status");
	    UserEntity user=new UserEntity();
	    user.setCreateTime(new Date(System.currentTimeMillis()));
	    user.setUpdateTime(new Date(System.currentTimeMillis()));  
	    user.setStatus((byte) Integer.parseInt(status));
	    user.setUserName(userName);
		user.setUserPwd(userPwd);
		if(null != paramMap.get("roleId") && !"".equals( paramMap.get("roleId"))){
			user.setRoleId(Integer.parseInt(paramMap.get("roleId").toString()));
		}

		try {
		return	userService.add(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return  0;
		
	}

	@Override
	public int vaildUserName(Map<String, Object> paramMap)
			throws ManagerException {
       String username=(String)paramMap.get("userName");
		UserEntity user=new UserEntity();
		user.setUserName(username);  
		try {
			return userService.getUserValid(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return -1;
	} 

	
	
	

}
