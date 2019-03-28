package com.wonhigh.i18n.ms.web.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.utils.Base64Utils;
import com.wonhigh.i18n.ms.manager.UserManager;
import com.wonhigh.i18n.ms.service.UserService;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.common.exception.ServiceException;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private UserManager userManager;
	@RequestMapping(value="/to_userdetail")
	public String getUserDetail(HttpServletRequest req){
		return "/detail/user_detail";
	}
	
 	@RequestMapping(value="/detail/userDetail")  
	@ResponseBody
	public Map<String, Object> queryDetail(HttpServletRequest req, Model model){
		Map<String, Object> paramMap = setParams(req);
		int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
		int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));
		//List<Object> smsHisList = null;
		int number=0;
		List<UserEntity> userList=null;
		
		try {
			try {
				number=	userService.findCount(paramMap);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userList=userManager.findUserRecordList(paramMap,pageNo,pageSize);
 		System.out.println(""+userList);
		} catch (ManagerException e1) {
			e1.printStackTrace();
		}
		
	
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("total", number);
		obj.put("rows",  userList);
		
		return obj;
	}
	
	@RequestMapping(value="detail/to_user_operator")  
	@ResponseBody
	public UserEntity  queryDetailOper(HttpServletRequest req, Model model) throws Exception{
		// String  user_id=req.getParameter("user_id");
		int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
		int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));
	
		 Map<String, Object> paramMap = setParams(req);   
    		List<UserEntity> userList=null;
 		   try {
		    	userList=userManager.findUserRecordList(paramMap,pageNo,pageSize);
 		        System.out.println(""+userList);
		           } catch (ManagerException e1) {
			            e1.printStackTrace();
	         	}
		    UserEntity obj=new UserEntity();
		    
		    if(!userList.isEmpty()){
		    	obj=userList.get(0);
		    }
		    
   		    return obj;   
   		
	}
	
	@RequestMapping(value="detail/modifyuserinformation")  
	@ResponseBody
	public Map<String, Object>  querModifyUser(HttpServletRequest req, Model model) throws Exception{
		 Map<String, Object> paramMap = setParams(req);  
		 int result;
	 	 Map<String, Object> obj = new HashMap<String, Object>();

  		   try {
  			   if(req.getParameter("userPwd")==null||req.getParameter("userPwd").length()<3){
  				 result=-1;
          	   obj.put("result", result);
         	       return obj; 
  			   }
  			   
  			 if(req.getParameter("userPwdComfirm").trim().length()<3){
          	   result=-1;
      	     obj.put("result", result);
     	       return obj; }
             if(!req.getParameter("userPwdComfirm").equals(req.getParameter("userPwd")))  {
          	   result=-1;
          	   obj.put("result", result);
         	       return obj; 
             }
 			  userManager.modifyUserRecord(paramMap);
 			 result=1;
        	   obj.put("result", result);
  		           } catch (ManagerException e1) {
			            e1.printStackTrace();
	         	}
   	       return obj; 
   		
	}
	
	
	
	@RequestMapping(value="/detail/saveuserinformation")  
	@ResponseBody
	public Map<String, Object>   querySaveUser(HttpServletRequest req, Model model) throws Exception{
		 Map<String, Object> paramMap = setParams(req);  
	 	 Map<String, Object> obj = new HashMap<String, Object>();
         int result;
   		   try {
               if(paramMap.get("status")==null||"".equals(paramMap.get("userPwd"))||paramMap.get("userPwd")==null||"".equals(paramMap.get("userName"))||paramMap.get("userName")==null||"".equals(paramMap.get("status"))){
            	   result=-1;
            	   obj.put("result", result);
           	       return obj; 
               }
               if(req.getParameter("userPwdCom").trim().length()<3){
            	   result=-1;
        	   obj.put("result", result);
       	       return obj; }
               if(!req.getParameter("userPwdCom").equals(req.getParameter("userPwd")))  {
            	   result=-1;
            	   obj.put("result", result);
           	       return obj; 
               }
               if(userManager.vaildUserName(paramMap)==0){
        			  userManager.addUserRecord(paramMap);
        			  result=1;
        			  obj.put("result", result);
        			 
               }
  		        } catch ( Exception e1) {
			            e1.printStackTrace();
	         	}
   		 return obj; 	
   		
	}
	

	@RequestMapping(value="/detail/validusername")  
	@ResponseBody
	public Map<String, Object>  queryValUserName(HttpServletRequest req, Model model) throws Exception{
		Map<String, Object> paramMap = setParams(req);
 		 int  result =userManager.vaildUserName(paramMap);
 		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("result", result);
		return obj;
 		 
 	}
	
	
	
	
	@RequestMapping(value="/detail/userDetail_")  
	@ResponseBody
	public Map<String, Object> queryDetail_Sub(HttpServletRequest req, Model model){
		Map<String, Object> paramMap = setParams(req);
	     String createTime =req.getParameter("createTime");
	     String updateTime =req.getParameter("updateTime");
	     String status =req.getParameter("status");
	     String userName =req.getParameter("userName");
		 paramMap.put("createTime", createTime);
		 paramMap.put("updateTime", updateTime);
		 paramMap.put("status", status);  
		 paramMap.put("userName", userName);
			int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
			int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));
		
   		List<UserEntity> userList=null;
		int number=0;
		try {	
			number=	userService.findCount(paramMap);
			userList=userManager.findUserRecordList(paramMap,pageNo,pageSize);
 		System.out.println(""+userList);
		} catch (ManagerException e1) {
			e1.printStackTrace();
		} catch (ServiceException e) {
 			e.printStackTrace();
		}
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("total", number);
		obj.put("rows",  userList);
		
		return obj;
	}

	private Map<String, Object> setParams(HttpServletRequest req){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if (req.getParameter("createTime") != null && !req.getParameter("createTime").equals("")) {
			paramMap.put("createTime", req.getParameter("createTime"));
		} 
		if (req.getParameter("updateTime") != null && !"".equals(req.getParameter("updateTime"))) {
			paramMap.put("updateTime", req.getParameter("updateTime"));
		} 
		if (req.getParameter("status") != null && !"".equals(req.getParameter("status"))) {
			paramMap.put("status", req.getParameter("status"));
		}
 		if (req.getParameter("userName") != null && !"".equals(req.getParameter("userName"))) {
			paramMap.put("userName", req.getParameter("userName"));
		}
 		if (req.getParameter("user_id") != null && !"".equals(req.getParameter("user_id"))) {
			paramMap.put("user_id", req.getParameter("user_id"));
		}
 		if (req.getParameter("userPwd") != null && !"".equals(req.getParameter("userPwd"))) {
			paramMap.put("userPwd", Base64Utils.encrytor(req.getParameter("userPwd")));
		}
 		if (req.getParameter("sendTime") != null && !"".equals(req.getParameter("sendTime"))) {
			paramMap.put("sendTime", req.getParameter("sendTime"));
		}
 		if (req.getParameter("sendTimeend") != null && !"".equals(req.getParameter("sendTimeend"))) {
			paramMap.put("sendTimeend", req.getParameter("sendTimeend"));
		}
 		if (req.getParameter("userId") != null && !"".equals(req.getParameter("userId"))) {
			paramMap.put("userId", req.getParameter("userId"));
		}
 		if (req.getParameter("userType") != null && !"".equals(req.getParameter("userType"))) {
			paramMap.put("userType", req.getParameter("userType"));
		}
		if(StringUtils.isNotBlank(req.getParameter("roleId") )){
			paramMap.put("roleId", req.getParameter("roleId"));
		}
		return	 paramMap;
	}

}
