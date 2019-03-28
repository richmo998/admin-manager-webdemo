package com.wonhigh.i18n.ms.web.controller;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController implements InitializingBean, DisposableBean  {

	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest req,Model model){
		return "index";
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {

	}


	@Override
	public void destroy() throws Exception {
		
	}
}
