package com.learning.annotations.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.learning.annotations.LogRecord;
import com.learning.annotations.entity.LogRecordEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogParam {

    private ProceedingJoinPoint pjp;

    private LogRecord logRecord;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Throwable throwable;

    private Object result;

    public LogRecordEntity generateLogRecordEntity() {
        String logType = null;
        String logName = logRecord.name();
        String logDescription = logRecord.description();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Optional.ofNullable(attributes).map(ServletRequestAttributes::getRequest).orElse(null);
        if (Objects.nonNull(request)) {
            logType = "request";
        }
        String serverName = Optional.ofNullable(request).map(ServletRequest::getServerName).orElse(null);
        int serverPort = Optional.ofNullable(request).map(ServletRequest::getServerPort).orElse(0);
        String contextPath = Optional.ofNullable(request).map(HttpServletRequest::getContextPath).orElse(null);
        String queryString = Optional.ofNullable(request).map(HttpServletRequest::getQueryString).orElse(null);
        String requestUrl = Optional.ofNullable(request).map(item -> item.getRequestURL().toString()).orElse(null);
        String requestMethod = Optional.ofNullable(request).map(HttpServletRequest::getMethod).orElse(null);

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        if (method.getAnnotation(Scheduled.class) != null || method.getAnnotation(Schedules.class) != null) {
            logType = "schedule";
        }
        String classSimpleName = pjp.getTarget().getClass().getSimpleName();
        Parameter[] parameters = method.getParameters();
        Object[] args = pjp.getArgs();
        String methodArgs = Arrays.toString(args);
        String RequestBody = null;
        for (Parameter parameter : parameters) {
            org.springframework.web.bind.annotation.RequestBody RequestBodyObj = parameter.getAnnotation(RequestBody.class);
            if (RequestBodyObj != null) {
                RequestBody = JSONObject.toJSONString(RequestBodyObj);
                System.out.println(RequestBody);
            }
        }
        String exceptionMsg = null;
        if (throwable != null) {
            exceptionMsg = getExceptionChains(throwable);
        }

        return new LogRecordEntity(logType, logName, logDescription, requestMethod, requestUrl, serverName,
            serverPort,
            contextPath,
            queryString
            , RequestBody, classSimpleName, method.toString(), methodArgs, exceptionMsg, result, startTime, endTime,
            Duration.between(startTime,
                endTime).toMillis());
    }

    public String getExceptionChains(Throwable throwable) {
        return throwable.getMessage() + "::" + throwable.getClass().getSimpleName() + "::" + throwable.getStackTrace()[0];
    }
}
