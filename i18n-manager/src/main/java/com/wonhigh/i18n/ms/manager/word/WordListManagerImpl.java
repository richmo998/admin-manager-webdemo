package com.wonhigh.i18n.ms.manager.word;


import com.spreada.utils.chinese.ZHConverter;
import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import com.wonhigh.i18n.ms.common.model.WordList;
import com.wonhigh.i18n.ms.service.word.WordListService;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.manager.BaseCrudManagerImpl;
import com.yougou.logistics.base.service.BaseCrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/12/19 20:03
 * @copyright richmo998
 * @description:
 */
@Service("wordListManager")
public class WordListManagerImpl   extends BaseCrudManagerImpl implements WordListManager {

    private Logger logger = Logger.getLogger(WordListManagerImpl.class);
    @Autowired
    private WordListService wordListService;

    @Override
    protected BaseCrudService init() {
        return wordListService;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return wordListService.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WordList record) throws Exception {
        return wordListService.insert(record);
    }

    @Override
    public int insertSelective(WordList record) throws Exception {
        return wordListService.insertSelective(record);
    }

    @Override
    public List<WordList> selectWordByMap(Map<String, Object> params) throws Exception {
        return wordListService.selectWordByMap(params);
    }

    @Override
    public List<WordList> selectWordsForList(WordList wordList) throws Exception {
        return wordListService.selectWordsForList(wordList);
    }

    @Override
    public WordList selectByPrimaryKey(Integer id) throws Exception {
        return wordListService.selectByPrimaryKey(id);
    }

    @Override
    public WordList selectBySimpleChineseKey(String key) throws Exception {
        return wordListService.selectBySimpleChineseKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(WordList record) throws Exception {
        return wordListService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WordList record) throws Exception {
        return wordListService.updateByPrimaryKey(record);
    }

    @Override
    public List<WordList> selectWordsByPages(SimplePage simplePage, Map<String,Object> params) throws Exception {

        List<WordList> byPage = wordListService.findByPage(simplePage, "update_time", "DESC", params);
            if(null == byPage){
            logger.info("根据条件没有查询到任何词条分页信息");
            return null;
        }
        for(WordList wordList:byPage){
            String remark="<a class='easyui-linkbutton' href='javascript:void(0)'  onclick='operator("+wordList.getId()+")'><b>修改</b></a>";
            wordList.setRemark(remark);
        }

        return byPage;
    }

    @Override
    public int selectMyCount(Map<String, Object> params) throws Exception {
//        return wordListService.selectMyCount(params);
        return wordListService.findCount(params);
    }

    /**
     * 简体转繁体
     * @param zhCn
     * @return
     * @throws Exception
     */
    @Override
    public String translateFromApi(String zhCn) throws Exception {
        return ZHConverter.convert(zhCn, PublicConstants.CONVERT_TYPE_HK);
    }
}
