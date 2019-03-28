package com.wonhigh.i18n.ms.service.word;


import com.wonhigh.i18n.ms.common.model.WordList;
import com.wonhigh.i18n.ms.dal.database.WordListMapper;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/12/19 20:03
 * @copyright richmo998
 * @description:
 */
@Service("wordListService")
public class WordListServiceImpl  extends BaseCrudServiceImpl implements WordListService {

    private Logger logger = Logger.getLogger(WordListServiceImpl.class);

    @Autowired
    private WordListMapper wordListMapper;

    @Override
    public BaseCrudMapper init() {
        return wordListMapper;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception{
        return wordListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WordList record)throws Exception {
        return wordListMapper.insert(record);
    }

    @Override
    public int insertSelective(WordList record)throws Exception {
        return wordListMapper.insertSelective(record);
    }

    @Override
    public List<WordList> selectWordByMap(Map<String, Object> params)throws Exception {
        return wordListMapper.selectWordByMap(params);
    }

    @Override
    public List<WordList> selectWordsForList(WordList wordList)throws Exception {
        return wordListMapper.selectWordsForList(wordList);
    }

    @Override
    public WordList selectByPrimaryKey(Integer id)throws Exception {
        return wordListMapper.selectByPrimaryKey(id);
    }

    @Override
    public WordList selectBySimpleChineseKey(String key) throws Exception {
        return wordListMapper.selectBySimpleChineseKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(WordList record)throws Exception {
        return wordListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WordList record)throws Exception {
        return wordListMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WordList> selectWordsByPages(SimplePage simplePage, Map<String, Object> params) throws Exception {
        Map<String ,Object> map = new HashMap<>();
        map.put("page",simplePage);
        map.put("params",params);
        return wordListMapper.selectWordsByPages(map);
    }

    @Override
    public int selectMyCount(Map<String, Object> params) throws Exception {
        return wordListMapper.selectMyCount(params);
    }
}
