package com.wonhigh.i18n.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.wonhigh.i18n.ms.service.BizTypeService;
import com.yougou.logistics.base.common.exception.ServiceException;

/**
 * 业务类型控制器Controller
 *
 *
 * @since 2017年3月29日 下午4:45:00
 *
 */
@Controller
@RequestMapping("/biz/type")
public class BizTypeController {
	@Autowired
	private BizTypeService bizTypeService;
	
	/**
	 * 资源页面
	 *
	 *
	 * @since 2017年3月29日 下午4:44:54
	 * 
	 * @return String
	 */
	@RequestMapping
	public String index() {
		return "biz_type/biz_type";
	}
	
	/**
	 * 查询列表
	 *
	 *
	 * @since 2017年3月29日 下午4:45:39
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		//查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizNo", request.getParameter("bizNo"));
		params.put("bizName", request.getParameter("bizName"));
		params.put("msgReceiver", request.getParameter("msgReceiver"));
		params.put("filter00", request.getParameter("filter00"));

		//新增过滤参数
		params.put("enable",request.getParameter("enable"));
		params.put("smsType",request.getParameter("smsType"));
		params.put("signType",request.getParameter("signType"));

		//分页参数
		int page = StringUtils.isEmpty(request.getParameter("page")) ? 1 : Integer.parseInt(request.getParameter("page"));
		int size = StringUtils.isEmpty(request.getParameter("rows")) ? 20 : Integer.parseInt(request.getParameter("rows"));
		params.put("index", (page - 1) * size);
		params.put("size", size);
		
		try {
			int count = bizTypeService.count(params);
			List<BizTypeEntity> list = bizTypeService.list(params);

			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("total", count);
			returnData.put("rows", list);
			return returnData;
		} catch (ServiceException e) {
			return null;
		}
	}
	
	/**
	 * 添加
	 *
	 *
	 * @since 2017年3月29日 下午4:46:06
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String save(BizTypeEntity entity) {
		try {
		    //进行非空参数校验
            if(StringUtils.isBlank(entity.getSignType())){
                entity.setSignType("");
            }
            if(StringUtils.isBlank(entity.getSmsType())){
                entity.setSmsType("");
            }
			bizTypeService.save(entity);
			return "success";
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 删除
	 *
	 *
	 * @since 2017年3月29日 下午4:46:18
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public String remove(BizTypeEntity entity) {
		try {
			bizTypeService.remove(entity);
			return "success";
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 更新
	 *
	 *
	 * @since 2017年3月29日 下午4:46:40
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String update(BizTypeEntity entity) {
		try {
			bizTypeService.update(entity);
			return "success";
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}
}