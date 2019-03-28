package com.wonhigh.i18n.ms.dal.database;



import com.wonhigh.i18n.ms.common.model.WordList;
import com.yougou.logistics.base.common.utils.SimplePage;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;

import java.util.List;
import java.util.Map;

public interface WordListMapper extends BaseCrudMapper {

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(WordList record)throws Exception;

    int insertSelective(WordList record)throws Exception;

    List<WordList> selectWordByMap(Map<String, Object> params)throws Exception;

    List<WordList> selectWordsForList(WordList wordList)throws Exception;

    WordList selectByPrimaryKey(Integer id)throws Exception;

    WordList selectBySimpleChineseKey(String key)throws Exception;


    int updateByPrimaryKeySelective(WordList record)throws Exception;

    int updateByPrimaryKey(WordList record)throws Exception;

    int selectMyCount(Map<String, Object> params)throws Exception;

    List<WordList> selectWordsByPages(Map<String, Object> params) throws Exception;

}