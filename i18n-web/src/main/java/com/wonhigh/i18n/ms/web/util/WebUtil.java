package com.wonhigh.i18n.ms.web.util;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 获取web工程中的基础工具
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/10/19 14:50
 * @copyright richmo998
 * @description:
 */
public class WebUtil {

private static final Logger logger = LogManager.getLogger(WebUtil.class);
    /**
     * 从session中获取用户业务码数据
     * @return
     */
    public static List<BizTypeEntity> getUserBizTpyeFromSession(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        Object attribute = session.getAttribute(PublicConstants.SESSION_USER_BIZTYPE);
        List<BizTypeEntity> list = null;
        if(null != attribute){
            list = (List<BizTypeEntity>)attribute;
        }
        if(list!=null)
        	logger.info("从session中获取的业务码信息【{}】",list.toString());
        return  list;
    }
    /**
     * 从session中获取用户信息
     * @return
     */
    public static UserEntity getUserEntityFromSession(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        Object attribute = session.getAttribute(PublicConstants.SESSION_USER);
        UserEntity userEntity = null;
        if(null != attribute){
            userEntity = (UserEntity)attribute;
        }
        
        if(userEntity!=null)
        	logger.info("从session中获取的用户信息【{}】",userEntity.toString());
        return  userEntity;
    }

    /**
     * 获取当前系统session
     * @return
     */
    public static HttpSession getSession(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return session;
    }

}
