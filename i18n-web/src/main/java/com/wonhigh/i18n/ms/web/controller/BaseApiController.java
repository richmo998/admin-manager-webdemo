package com.wonhigh.i18n.ms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonhigh.i18n.ms.common.vo.ResultModel;
import com.wonhigh.i18n.ms.common.vo.ResultStatus;

/**
 * 对外短信发送API  异常处理类
 */
@Controller
public abstract class BaseApiController {
	protected static final XLogger logger = XLoggerFactory.getXLogger(BaseApiController.class);

	// 统一异常处理
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ResultModel> exception(Exception ex, HttpServletRequest request) {
		String message = ex.getMessage();
		logger.error(message, ex);
		HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		ResultStatus error = ResultStatus.ERROR;
		if(ex instanceof ServletRequestBindingException){
			statusCode = HttpStatus.BAD_REQUEST;
			error = ResultStatus.BAD_REQUEST;
		}
		return new ResponseEntity<ResultModel>(ResultModel.error(error,message),statusCode);
	}

}
