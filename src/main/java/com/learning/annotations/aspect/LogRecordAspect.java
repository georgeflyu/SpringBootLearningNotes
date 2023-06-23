package com.learning.annotations.aspect;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.learning.annotations.Log;
import com.learning.entity.LogRecord;


@Order(1)
@Aspect
@Component
@ConditionalOnBean(LogRecordAspect.LogRecordHandler.class)
public class LogRecordAspect {

    @Before("@annotation(com.learning.annotations.Log)")
    public void pointCut() {
    }

    @Resource(name = "LogRecordHandlerImpl")
    LogRecordHandler logRecordHandler;

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint pjp, Log log) throws Throwable {
        LogParam logParam = new LogParam(pjp, log, LocalDateTime.now(ZoneId.of("UTC")), null, null, null);
        Object result = null;
        try {
            result = pjp.proceed();
            logParam.setResult(result);
            logParam.setEndTime(LocalDateTime.now(ZoneId.of("UTC")));
            LogRecord logEntity = logParam.generateLogRecordEntity();
            new Thread(() -> {
                logRecordHandler.handle(logEntity);
            }).start();
        } catch (Throwable throwable) {
            logParam.setThrowable(throwable);
            logParam.setEndTime(LocalDateTime.now(ZoneId.of("UTC")));
            LogRecord logEntity = logParam.generateLogRecordEntity();
            new Thread(() -> {
                logRecordHandler.handle(logEntity);
            }).start();
            throw throwable;
        }
        return result;
    }

    @FunctionalInterface
    public interface LogRecordHandler {

        int handle(LogRecord logRecord);
    }
}
