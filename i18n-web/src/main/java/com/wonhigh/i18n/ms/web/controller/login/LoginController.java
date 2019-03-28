package com.wonhigh.i18n.ms.web.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.wonhigh.i18n.ms.service.BizTypeService;
import com.wonhigh.i18n.ms.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.service.UserService;
import com.yougou.logistics.base.common.exception.ServiceException;


@Controller
public class LoginController {
	
    @Resource
    private UserService userService;
    @Autowired
    private BizTypeService bizTypeService;
    
    @Autowired(required=false)
	private ServletContext context;

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> index(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserEntity userObj = userService.login(userName, password);
			HttpSession session = request.getSession();
			session.setAttribute(PublicConstants.SESSION_USER, userObj);

            map.clear();
			map.put(PublicConstants.SUCCESS, "true");
		} catch (ServiceException e) {
			map.put(PublicConstants.SUCCESS, "false");
			map.put(PublicConstants.MESSAGE, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String index(){
		return "login";
	}
	
	@RequestMapping(value = "/get_menu_data")
	@ResponseBody
	public String getMenuData() {
		String basePath = context.getRealPath("");
		String data = "";

//		UserEntity userEntity = WebUtil.getUserEntityFromSession();
		try {
			data =  userService.getMenuData(basePath);
//			data =  userService.getMenuData(basePath,userEntity);
		} catch (ServiceException e) {
			e.printStackTrace();
			data = "";
		}
		return data;
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
//		session.removeAttribute(PublicConstants.SESSION_USER);
		return "redirect:/login";
	}
}
