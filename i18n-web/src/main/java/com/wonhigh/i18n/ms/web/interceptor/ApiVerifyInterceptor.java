package com.wonhigh.i18n.ms.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonhigh.i18n.ms.common.constants.ApiConstants;
import com.wonhigh.i18n.ms.common.utils.DateUtil;
import com.wonhigh.i18n.ms.common.utils.SignUtil;
import com.wonhigh.i18n.ms.common.vo.ResultModel;
import com.wonhigh.i18n.ms.common.vo.ResultStatus;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 对外API，全局参数校验,拦截器
 */
public class ApiVerifyInterceptor extends HandlerInterceptorAdapter {
	protected static final XLogger logger = XLoggerFactory.getXLogger(ApiVerifyInterceptor.class);
//	private SmsApiManager apiManager;
//
//	public void setApiManager(SmsApiManager apiManager) {
//		this.apiManager = apiManager;
//	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 参数列表
		Enumeration<?> enu = request.getParameterNames();
		Map<String,String> parameter = new HashMap<String,String>();
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement(); 
			parameter.put(paraName, request.getParameter(paraName));
		}
		// 时间戳
		String timestamp = parameter.get(ApiConstants.TIMESTAMP);
		if (!StringUtils.hasText(timestamp)) {
			outJson(response, ResultModel.error(ResultStatus.PUBLIC_TIMESTAMP_NOT_NULL));
			return false;
		}else{
			long clientTimestamp = 0;
			try {
				clientTimestamp = Long.valueOf(timestamp).longValue();
			} catch (NumberFormatException e) {
				outJson(response, ResultModel.error(ResultStatus.PUBLIC_TIMESTAMP_INVALID));
			}
			long serverTimestamp = DateUtil.getCurrentTimestampUTC();
			long s = Math.abs(serverTimestamp - clientTimestamp);
			// 十分钟
			if (s > ApiConstants.API_EXPIRATION_TIME ){
				outJson(response, ResultModel.error(ResultStatus.PUBLIC_TIMESTAMP_BEYOND_THE_LIMIT));
				return false;
			}
		}
		// APPKEY
		String appKey = parameter.get(ApiConstants.APP_KEY);
		if (!StringUtils.hasText(appKey)) {
			outJson(response, ResultModel.error(ResultStatus.PUBLIC_APP_KEY_NOT_NULL));
			return false;
		}
		// SIGN
		String clientSign = parameter.get(ApiConstants.SIGN);
		if (!StringUtils.hasText(clientSign)) {
			outJson(response, ResultModel.error(ResultStatus.PUBLIC_SIGN_NOT_NULL));
			return false;
		}
		
		// 根据appKey 查询 密钥 appSecret
//		String appSecret = apiManager.getAppSecret(appKey);
		String appSecret = "";
		if(!StringUtils.hasText(appSecret)){
			outJson(response, ResultModel.error(ResultStatus.PUBLIC_APP_KEY_NOT_FOUND));
			return false;
		}
		String httpMethod = request.getMethod();
		String requestURI = request.getRequestURI();

		parameter.remove(ApiConstants.SIGN);
		String serverSign = SignUtil.sign(appSecret, httpMethod, requestURI, parameter);

		if (!clientSign.equals(serverSign)) {
			outJson(response, ResultModel.error(ResultStatus.PUBLIC_SIGN_INVALID));
			return false;
		}

		return true;
	}

	private void outJson(HttpServletResponse response, ResultModel resultModel) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		logger.error(resultModel.getMessage());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper= new ObjectMapper();
		response.getWriter().print(mapper.writeValueAsString(resultModel));
	}
}
