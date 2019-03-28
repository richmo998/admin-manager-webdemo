package com.wonhigh.i18n.ms.web.interceptor;

import com.wonhigh.i18n.ms.common.constants.ApiConstants;
import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.wonhigh.i18n.ms.service.BizTypeService;
import com.wonhigh.i18n.ms.web.util.WebUtil;
import com.yougou.logistics.base.common.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 记录经过nginx后的真实ip
 *
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/10/15 11:49
 * @copyright richmo998
 * @description:
 */
public class RecordRealRequestIpInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LogManager.getLogger(RecordRealRequestIpInterceptor.class);

    private BizTypeService bizTypeService;

    public void setBizTypeService(BizTypeService bizTypeService) {
        this.bizTypeService = bizTypeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("进行真实ip记录追踪-->");
        String host = request.getHeader("Host");
        String realIp = request.getHeader("X-Real-IP");
        String forwardedFor = request.getHeader("X-Forwarded-For");
        String proto = request.getHeader("X-Forwarded-Proto");

        if (StringUtils.isBlank(host)) {
            logger.info("请求没有经过nginx转发，直接获取host");
            host = request.getRemoteHost();
        }
        if (StringUtils.isBlank(realIp)) {
            logger.info("请求没有经过nginx转发，直接获取ip");
            realIp = request.getRemoteAddr();
        }

        //新接口获取业务编码
        String bizCode = request.getParameter("bizCode");
        if (StringUtils.isBlank(bizCode)) {
            //兼容老接口
            bizCode = request.getParameter("businessSystemCode");
            if (StringUtils.isBlank(bizCode)) {
                //新老接口都没有，则使用默认02编码
                bizCode = "02";
            }
        }
        logger.info("【追踪真实业务调用】：业务编码bizCode=【{}】,请求ip=【{}】,协议头=【{}】，主机名=【{}】," +
                "具体代理X-Forwarded-For=【{}】", bizCode, realIp, proto, host, forwardedFor);
        String smsType=request.getParameter("smsType");
        String signType =request.getParameter("smsSign");
        //根据业务编码查询当前业务编码是否启用
        Map<String, Object> stringObjectMap = checkBizCodeAndSmsTypeAndSignType(bizCode, smsType, signType);

        if(null == stringObjectMap) {
            return true;
        }
        //此处先不拦截，将结果往下层传
        HttpSession session = WebUtil.getSession();
        if(null != stringObjectMap.get("isBizCodeEnableUse")){
//            request.setAttribute("isBizCodeEnableUse",stringObjectMap.get("isBizCodeEnableUse"));
            session.setAttribute("isBizCodeEnableUse",stringObjectMap.get("isBizCodeEnableUse"));
        }
        if(null != stringObjectMap.get("isSmsTypeCanUse")){
//            request.setAttribute("isSmsTypeCanUse",stringObjectMap.get("isSmsTypeCanUse"));
            session.setAttribute("isSmsTypeCanUse",stringObjectMap.get("isSmsTypeCanUse"));
        }
        if(null != stringObjectMap.get("isSignTypeCanUse")){
//            request.setAttribute("isSignTypeCanUse",stringObjectMap.get("isSignTypeCanUse"));
            session.setAttribute("isSignTypeCanUse",stringObjectMap.get("isSignTypeCanUse"));
        }
        return true;
    }


    /**
     * 校验请求接口中的业务码以及规定使用的短信类型和签名类型
     * 与数据库配置是否相符
     * @param bizCode
     * @param smsType
     * @param signType
     * @return
     */
    private Map<String,Object> checkBizCodeAndSmsTypeAndSignType(String bizCode,String smsType,String signType){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("bizNo", bizCode);
        params.put("index", 0);
        params.put("size", 20);
        try {

            List<BizTypeEntity> list = bizTypeService.list(params);
            if(null == list || list.isEmpty()){
                return null;
            }
            BizTypeEntity bizTypeEntity = list.get(0);

            //对比校验一下短信类别和签名信息是否匹配，若不匹配给出ELK告警
            if(StringUtils.isNotBlank(bizTypeEntity.getSmsType())){
                //数据库非空则要对比前端传入是否有差异
                if(StringUtils.isBlank(smsType)){
                    logger.warn("可使用短信类型与前端传入不相符，请检查-前端传入为空");
                    resultMap.put("isSmsTypeCanUse","0:可使用短信类型与前端传入不相符，请检查-前端传入为空，后台规定范围【"+bizTypeEntity.getSmsType()+"】");
                }else if(!bizTypeEntity.getSmsType().contains(smsType)){
                    logger.warn("可使用短信类型与前端传入不相符，请检查-前端传入为【{}】，后台规定范围【{}】",smsType,bizTypeEntity.getSmsType());
                    resultMap.put("isSmsTypeCanUse","0:可使用短信类型与前端传入不相符，请检查-前端传入为【"+smsType+"】，后台规定范围【"+bizTypeEntity.getSmsType()+"】");
                }
            }

            if(StringUtils.isNotBlank( bizTypeEntity.getSignType())){
                //数据库非空则要对比前端传入是否有差异
                if(StringUtils.isBlank(signType)){
                    logger.warn("短信签名与前端传入不相符，请检查-前端传入为空");
                    resultMap.put("isSignTypeCanUse","0:短信签名与前端传入不相符，请检查-前端传入为空，后台规定范围【"+bizTypeEntity.getSignType()+"】");
                }else if(!bizTypeEntity.getSignType().contains(signType)){
                    logger.warn("短信签名与前端传入不相符，请检查-前端传入为【{}】，后台规定范围【{}】",signType,bizTypeEntity.getSignType());
                    resultMap.put("isSignTypeCanUse","0:短信签名与前端传入不相符，请检查-前端传入为【"+signType+"】，后台规定范围【"+bizTypeEntity.getSignType()+"】");
                }
            }

            //判断是否被禁用
            String enable = bizTypeEntity.getEnable();
            if(ApiConstants.API_BIZCODE_DISENABLE.equals(enable)){
                logger.warn("bizCode=【{}】，已经被禁用，请检查。",bizCode);
                resultMap.put("isBizCodeEnableUse","0:bizCode=【"+bizCode+"】，已经被禁用，请检查。");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
        return resultMap;
    }


}
