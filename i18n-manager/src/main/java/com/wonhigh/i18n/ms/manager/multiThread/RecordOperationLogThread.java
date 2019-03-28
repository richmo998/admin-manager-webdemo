package com.wonhigh.i18n.ms.manager.multiThread;

import com.wonhigh.i18n.ms.common.model.WordListLog;
import com.wonhigh.i18n.ms.manager.word.WordListLogManager;
import com.wonhigh.i18n.ms.manager.word.WordListLogManagerImpl;
import com.yougou.logistics.base.common.exception.ManagerException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 异步记录操作日志
 * @author moyongfeng
 * @email: 605669690@qq.com
 * @date 2018/12/26 17:29
 * @copyright richmo998
 * @description:
 */
public class RecordOperationLogThread implements Runnable {
    private Logger logger = Logger.getLogger(RecordOperationLogThread.class);
    private WordListLogManager wordListLogManager= null ;
    private WordListLog wordListLog = null;

    public RecordOperationLogThread(WordListLogManager wordListLogManager,WordListLog wordListLog){

        this.wordListLog=wordListLog;
        this.wordListLogManager = wordListLogManager;
    }

    @Override
    public void run() {
        if(null == wordListLog){
            return ;
        }
        if(StringUtils.isBlank(wordListLog.getOperation())){
            logger.info("修改日志中无任何操作类型，无需记录");
            return ;
        }

        try {
            wordListLogManager.add(wordListLog);
        } catch (ManagerException e) {
            logger.info("异步记录日志失败");
            e.printStackTrace();
        }
        logger.info("异步记录日志完成");
    }
}
