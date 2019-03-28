package com.wonhigh.i18n.ms.service.word;

import com.wonhigh.i18n.ms.dal.database.WordListLogMapper;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/12/19 20:03
 * @copyright richmo998
 * @description:
 */
@Service("wordListLogService")
public class WordListLogServiceImpl  extends BaseCrudServiceImpl implements WordListLogService {

    @Autowired
    private WordListLogMapper wordListLogMapper;
    @Override
    public BaseCrudMapper init() {
        return wordListLogMapper;
    }
}
