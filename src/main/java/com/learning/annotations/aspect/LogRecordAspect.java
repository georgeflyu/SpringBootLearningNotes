package com.learning.annotations.aspect;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.learning.annotations.LogRecord;
import com.learning.annotations.entity.LogRecordEntity;

@Order(1)
@Aspect
@Component
@ConditionalOnBean(LogRecordAspect.LogRecordHandler.class)
public class LogRecordAspect {

    @Before("@annotation(com.learning.annotations.LogRecord)")
    public void pointCut() {
    }

    @Resource
    LogRecordHandler logRecordHandler;

    @Around("@annotation(logRecord)")
    public Object around(ProceedingJoinPoint pjp, LogRecord logRecord) throws Throwable {
        LogParam logParam = new LogParam(pjp, logRecord, ZonedDateTime.now(ZoneId.of("UTC")), null, null, null);
        Object result = null;
        try {
            result = pjp.proceed();
            logParam.setResult(result);
            logParam.setEndTime(ZonedDateTime.now(ZoneId.of("UTC")));
            LogRecordEntity logRecordEntity = logParam.generateLogRecordEntity();
            new Thread(() -> {
                logRecordHandler.handle(logRecordEntity);
            }).start();
        } catch (Throwable throwable) {
            logParam.setThrowable(throwable);
            logParam.setEndTime(ZonedDateTime.now(ZoneId.of("UTC")));
            LogRecordEntity logRecordEntity = logParam.generateLogRecordEntity();
            new Thread(() -> {
                logRecordHandler.handle(logRecordEntity);
            }).start();
            throw throwable;
        }
        return result;
    }

    @FunctionalInterface
    public interface LogRecordHandler {
        int handle(LogRecordEntity logRecordEntity);
    }
}
