package com.wonhigh.i18n.ms.web.controller.wordListLog;

import com.wonhigh.i18n.ms.common.model.WordListLog;
import com.wonhigh.i18n.ms.manager.word.WordListLogManager;
import com.yougou.logistics.base.common.utils.SimplePage;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WordLogController {

    private static final Logger logger = LogManager.getLogger(WordLogController.class);
    @Autowired
    private WordListLogManager wordListLogManager;

    /**
     * 根据菜单地址找到
     * 系统日志界面
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/to_word_list_log")
    public String getRoleIndex(HttpServletRequest req) {
        //前端page文件下，相关业务的初始页path
        return "/word/wordListLog";
    }


    /**
     * 获取系统日志分页列表
     *
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/wordListLog")
    @ResponseBody
    public Map<String, Object> queryWordListLogForPage(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = setParams(req);

        int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));

        List<WordListLog> wordListLogList = null;
        int number = 0;
        try {
            SimplePage simplePage = new SimplePage();
            simplePage.setPageSize(pageSize);
            simplePage.setPageNo(pageNo);
            number = wordListLogManager.findCount(paramMap);
            wordListLogList = wordListLogManager.findByPage(simplePage,"update_time", "DESC",paramMap);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Map<String, Object> obj = new HashMap();
        obj.put("total", number);
        obj.put("rows", wordListLogList);
        logger.info("查询系统操作日志完成");
        return obj;
    }


    private Map<String, Object> setParams(HttpServletRequest req) {
        Map<String, Object> paramMap = new HashMap();

        String createUser = req.getParameter("createUser");
        String operation = req.getParameter("operation");
        String zhCn = req.getParameter("zhCn");
        String createTime = req.getParameter("createTime");
        String updateTime = req.getParameter("updateTime");


        if (StringUtils.isNotBlank(zhCn)) {
            paramMap.put("zhCn", zhCn);
        }
        if (StringUtils.isNotBlank(createUser)) {
            paramMap.put("createUser", createUser);
        }
        if (StringUtils.isNotBlank(operation)) {
            paramMap.put("operation", operation);
        }
        if (StringUtils.isNotBlank(createTime)) {
            paramMap.put("createTime", createTime);
        }

        if (StringUtils.isNotBlank(updateTime)) {
            paramMap.put("updateTime", updateTime);
        }

        return paramMap;
    }

}
