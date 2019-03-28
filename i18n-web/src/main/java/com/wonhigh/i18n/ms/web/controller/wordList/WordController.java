package com.wonhigh.i18n.ms.web.controller.wordList;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.wonhigh.i18n.ms.common.model.WordList;
import com.wonhigh.i18n.ms.common.model.WordListLog;
import com.wonhigh.i18n.ms.manager.multiThread.RecordOperationLogThread;
import com.wonhigh.i18n.ms.manager.word.WordListLogManager;
import com.wonhigh.i18n.ms.manager.word.WordListManager;
import com.wonhigh.i18n.ms.web.util.WebUtil;
import com.yougou.logistics.base.common.exception.ManagerException;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class WordController {

    private static final Logger logger = LogManager.getLogger(WordController.class);

    public static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Autowired
    private WordListManager wordListManager;
    @Autowired
    private WordListLogManager wordListLogManager;


    /**
     * 词条翻译界面
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/to_word_translate")
    public String getWordTranslateFtl(HttpServletRequest req) {
        return "/word/wordTranslate";
    }


    /**
     * 根据接口翻译词条
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/translate/translateFromApi")
    @ResponseBody
    public WordList translateFromApi(HttpServletRequest req, Model model) throws Exception {

        String key = req.getParameter("zhCn");
        if( StringUtils.isBlank(key)){
            logger.info("需要翻译的简体中文词条为空");
           return null;
        }
        WordList  wordList = new WordList();
        String zhHk = wordListManager.translateFromApi(key);
        wordList.setZhCn(key);
        wordList.setZhHk(zhHk);
        logger.info(key+":词条翻译成功");
        return wordList;

    }
    /**
     * 根据菜单地址找到
     * 词条的加载初始页面
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/to_word_list")
    public String getWordListFtl(HttpServletRequest req) {
        //前端page文件下，相关业务的初始页path
        return "/word/wordList";
    }

    @RequestMapping(value = "/detail/validWordKey")
    @ResponseBody
    public Map<String, Object> queryValWordKey(HttpServletRequest req, Model model) throws Exception {
        Map<String, Object> obj = new HashMap();
        obj.put("result", 0);
        String wordType = req.getParameter("wordType");
        if (StringUtils.isBlank(wordType)) {
            logger.info("查询参数为空");
            return obj;
        }
        WordList entity = wordListManager.selectBySimpleChineseKey(wordType);
        if (null != entity) {
            logger.info("当前词条【{}】已存在", wordType);
            obj.put("result", 1);
        }
        logger.info("【{}】词条唯一性校验成功",wordType);
        return obj;

    }

    /**
     * 根据主键查询词条信息
     *
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail/selectOneWordDetail")
    @ResponseBody
    public WordList selectOneWordDetail(HttpServletRequest req, Model model) throws Exception {
        WordList entity = null;
        String id = req.getParameter("id");
        if (StringUtils.isBlank(id)) {
            logger.info("查询词条的主键id缺失");
            return null;
        }
        entity = wordListManager.selectByPrimaryKey(Integer.parseInt(id));
        logger.info("【{}】:根据主键id查询成功",id);
        return entity;

    }

    /**
     * 添加词条信息
     *
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail/addWord")
    @ResponseBody
    public Map<String, Object> addWord(HttpServletRequest req, Model model) throws Exception {
        WordList wordList = toWordListEntity(req);
        int result;
        Map<String, Object> obj = new HashMap();

        try {
            if (StringUtils.isBlank(wordList.getZhCn())) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "简体中文词条为空");
                return obj;
            }
            logger.info("添加中文简体词条：【{}】",wordList.getZhCn());
            //从session中获取用户信息记录
//            UserEntity userEntity = (UserEntity) WebUtil.getSession().getAttribute(PublicConstants.SESSION_USER);
            UserEntity userEntity = (UserEntity) req.getSession().getAttribute(PublicConstants.SESSION_USER);
            wordList.setCreateUser(userEntity.getUserName());
            wordList.setCreateUserId(userEntity.getUserId());

            logger.info("用户【{}】进行了词条新增操作，data=【{}】", wordList.getCreateUser(), wordList.toString());
            result = wordListManager.insertSelective(wordList);
            obj.put("result", result);
            obj.put("errorMsg", "插入词条成功");

            //记录新增日志
            this.recordLog(wordList,PublicConstants.LOG_OPERATION_ADD);
            logger.info("记录新增日志成功");

        } catch (Exception e1) {
            e1.printStackTrace();
            obj.put("result", -1);
            obj.put("errorMsg", "插入词条失败："+e1.getMessage());
            return obj;
        }
        return obj;

    }

    /**
     * 记录日志
     *
     * @param wl
     * @param operation
     */
    private void recordLog(WordList wl, String operation) throws Exception {
        WordListLog wordListLog = new WordListLog();
        wordListLog.setWordType(wl.getWordType());
        wordListLog.setSysGroup(wl.getSysGroup());
        wordListLog.setZhCn(wl.getZhCn());
        wordListLog.setOperation(operation);

        if (operation.equalsIgnoreCase(PublicConstants.LOG_OPERATION_UPDATE)) {
            wordListLog.setContent(wl.toString());
            wordListLog.setExContent("词条id="+wl.getId()+"无原始记录");
            //更新则需要记录旧的信息
            WordList ExWord = wordListManager.selectByPrimaryKey(wl.getId());
            if(null != ExWord){
                wordListLog.setExContent(ExWord.toString());
            }
            wordListLog.setUpdateUser(wl.getUpdateUser());
        }else if(operation.equalsIgnoreCase(PublicConstants.LOG_OPERATION_ADD)){

            wordListLog.setContent(wl.toString());

            wordListLog.setCreateUser(wl.getCreateUser());
            wordListLog.setCreateUserId(wl.getCreateUserId());

        }
        executorService.execute(new RecordOperationLogThread(wordListLogManager, wordListLog));
    }

    /**
     * 更新词条信息
     *
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail/modifyWords")
    @ResponseBody
    public Map<String, Object> modifyWord(HttpServletRequest req, Model model) throws Exception {
        WordList wordList = toWordListEntity(req);
        int result;
        Map<String, Object> obj = new HashMap();

        try {
            if (null == wordList.getId()) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "更新词条主键为空");
                return obj;
            }
            //获取当前登录用户信息
            UserEntity userEntity = (UserEntity) req.getSession().getAttribute(PublicConstants.SESSION_USER);
            wordList.setUpdateUser(userEntity.getUserName());
            logger.info("用户【{}】进行了词条更新操作，data=【{}】", userEntity.getUserName(), wordList.toString());
            //更新前记录日志，主要获取修改前的信息
            this.recordLog(wordList,PublicConstants.LOG_OPERATION_UPDATE);

            result = wordListManager.updateByPrimaryKeySelective(wordList);
            obj.put("result", result);
            obj.put("errorMsg", "更新词条成功");
        } catch (ManagerException e1) {
            e1.printStackTrace();
            obj.put("result", -1);
            obj.put("errorMsg", "更新词条失败："+e1.getMessage());
            return obj;
        }
        return obj;

    }


    /**
     * 获取词条分页列表
     *
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/WordList")
    @ResponseBody
    public Map<String, Object> selectWordsByPages(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = setParams(req);

        Map<String, Object> obj = new HashMap();

        int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));

        List<WordList> wordLists = null;
        int number = 0;
        try {
            SimplePage simplePage = new SimplePage();
            simplePage.setPageNo(pageNo);
            simplePage.setPageSize(pageSize);
            number = wordListManager.selectMyCount(paramMap);
            wordLists = wordListManager.selectWordsByPages(simplePage, paramMap);

            logger.info("查询词条列表成功");
        } catch (Exception e1) {

            e1.printStackTrace();
            obj.put("result", -1);
            obj.put("errorMsg", "查询词条列表失败："+e1.getMessage());
            return obj;
        }

        obj.put("total", number);
        obj.put("rows", wordLists);
        return obj;
    }

    /**
     * 获取词条列表
     * 所有数据
     *
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/allWordList")
    @ResponseBody
    public Map<String, Object> selectWordsForList(HttpServletRequest req, Model model) {
        WordList entity = toWordListEntity(req);
        List<WordList> wordLists = null;
        try {
            wordLists = wordListManager.selectWordsForList(entity);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("wordList", wordLists);
        return obj;
    }


    private Map<String, Object> setParams(HttpServletRequest req) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String wordType = req.getParameter("wordType");
        String sysGroup = req.getParameter("sysGroup");
        String zhCn = req.getParameter("zhCn");
        String zhHk = req.getParameter("zhHk");
        String zhEn = req.getParameter("zhEn");
        String id = req.getParameter("id");

        String createTime = req.getParameter("createTime");
        String createTimeEnd = req.getParameter("createTimeEnd");
        String updateTime = req.getParameter("updateTime");
        String updateTimeEnd = req.getParameter("updateTimeEnd");


        if (StringUtils.isNotBlank(wordType)) {
            paramMap.put("wordType", wordType);
        }
        if (StringUtils.isNotBlank(sysGroup)) {
            paramMap.put("sysGroup", sysGroup);
        }
        if (StringUtils.isNotBlank(zhCn)) {
            paramMap.put("zhCn", zhCn);
        }
        if (StringUtils.isNotBlank(zhHk)) {
            paramMap.put("zhHk", zhHk);
        }
        if (StringUtils.isNotBlank(zhEn)) {
            paramMap.put("zhEn", zhEn);
        }
        if (StringUtils.isNotBlank(id)) {
            paramMap.put("id", id);
        }

        if (StringUtils.isNotBlank(createTime)) {
            paramMap.put("createTime", createTime);
        }
        if (StringUtils.isNotBlank(createTimeEnd)) {
            paramMap.put("createTimeEnd", createTimeEnd);
        }
        if (StringUtils.isNotBlank(updateTime)) {
            paramMap.put("updateTime", updateTime);
        }
        if (StringUtils.isNotBlank(updateTimeEnd)) {
            paramMap.put("updateTimeEnd", updateTimeEnd);
        }
        return paramMap;
    }

    /**
     * 转词条对象
     *
     * @param req
     * @return
     */
    private WordList toWordListEntity(HttpServletRequest req) {
        WordList wordList = new WordList();
        String wordType = req.getParameter("wordType");
        String sysGroup = req.getParameter("sysGroup");
        String zhCn = req.getParameter("zhCn");
        String zhHk = req.getParameter("zhHk");
        String zhEn = req.getParameter("zhEn");
        String id = req.getParameter("id");

        if (StringUtils.isNotBlank(wordType)) {
            wordList.setWordType(wordType);
        }
        if (StringUtils.isNotBlank(sysGroup)) {
            wordList.setSysGroup(sysGroup);
        }
        if (StringUtils.isNotBlank(zhCn)) {
            //去掉词条中间的所有空格
            wordList.setZhCn(zhCn.replace(" ",""));
            //默认词条标识和中文一致
            wordList.setWordType(zhCn.replace(" ",""));
        }
        if (StringUtils.isNotBlank(zhHk)) {
            wordList.setZhHk(zhHk.replace(" ",""));
        }
        if (StringUtils.isNotBlank(zhEn)) {
            wordList.setZhEn(zhEn);
        }
        if (StringUtils.isNotBlank(id)) {
            wordList.setId(Integer.parseInt(id));
        }

        return wordList;
    }


}
