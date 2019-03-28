package com.wonhigh.i18n.ms.manager.word;

import com.wonhigh.i18n.ms.dal.database.WordListLogMapper;
import com.wonhigh.i18n.ms.service.word.WordListLogService;
import com.wonhigh.i18n.ms.service.word.WordListService;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.manager.BaseCrudManager;
import com.yougou.logistics.base.manager.BaseCrudManagerImpl;
import com.yougou.logistics.base.service.BaseCrudService;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/12/19 20:03
 * @copyright richmo998
 * @description:
 */
@Service("wordListLogManager")
public class WordListLogManagerImpl extends BaseCrudManagerImpl implements WordListLogManager {

    private Logger logger = Logger.getLogger(WordListLogManagerImpl.class);
    @Autowired
    private WordListLogService wordListLogService;

    @Override
    protected BaseCrudService init() {
        return wordListLogService;
    }
}
