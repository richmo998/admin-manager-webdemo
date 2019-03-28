package com.wonhigh.i18n.ms.dal.database;

import com.wonhigh.i18n.ms.common.model.WordListLog;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;

import java.util.List;
import java.util.Map;

public interface WordListLogMapper extends BaseCrudMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WordListLog record);

    int insertSelective(WordListLog record);

    List<WordListLog> selectOperationLogByMap(Map<String, Object> params);

    List<WordListLog> selectOperationLogForList(WordListLog wordListLog);

    WordListLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordListLog record);

    int updateByPrimaryKey(WordListLog record);
}