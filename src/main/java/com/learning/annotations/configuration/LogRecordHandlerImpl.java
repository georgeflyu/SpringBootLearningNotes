package com.learning.annotations.configuration;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.learning.annotations.aspect.LogRecordAspect.LogRecordHandler;
import com.learning.dao.db1.LogRecordDao;
import com.learning.entity.LogRecord;

@Component(value = "LogRecordHandlerImpl")
public class LogRecordHandlerImpl implements LogRecordHandler {

    @Resource
    LogRecordDao logRecordDao;

    @Override
    public int handle(LogRecord logRecord) {
        return logRecordDao.insert(logRecord);
    }
}
