package com.learning.annotations.configuration;

import org.springframework.stereotype.Component;

import com.learning.annotations.aspect.LogRecordAspect.LogRecordHandler;
import com.learning.annotations.entity.LogRecordEntity;

@Component
public class LogRecordHandlerImpl implements LogRecordHandler {

    @Override
    public int handle(LogRecordEntity logRecordEntity) {
        System.out.println(logRecordEntity);
        return 0;
    }
}
