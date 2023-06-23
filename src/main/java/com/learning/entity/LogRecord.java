package com.learning.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (LogRecord)实体类
 *
 * @author makejava
 * @since 2023-06-23 13:43:48
 */
@Data
@NoArgsConstructor
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 166226635161998201L;

    private Integer id;

    private String logType;

    private String logName;

    private String logDescription;

    private String requestMethod;

    private String requestUrl;

    private String serverName;

    private Integer serverPort;

    private String contextPath;

    private String queryString;

    private String requestBody;

    private String className;

    private String methodName;

    private String methodArgs;

    private String exceptionMsg;

    private String result;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer consumeMillis;

    public LogRecord(String logType, String logName, String logDescription, String requestMethod, String requestUrl,
                     String serverName, Integer serverPort, String contextPath, String queryString, String requestBody,
                     String className, String methodName, String methodArgs, String exceptionMsg, String result,
                     LocalDateTime startTime, LocalDateTime endTime, Integer consumeMillis) {
        this.logType        = logType;
        this.logName        = logName;
        this.logDescription = logDescription;
        this.requestMethod  = requestMethod;
        this.requestUrl     = requestUrl;
        this.serverName     = serverName;
        this.serverPort     = serverPort;
        this.contextPath    = contextPath;
        this.queryString    = queryString;
        this.requestBody    = requestBody;
        this.className      = className;
        this.methodName     = methodName;
        this.methodArgs     = methodArgs;
        this.exceptionMsg   = exceptionMsg;
        this.result         = result;
        this.startTime      = startTime;
        this.endTime        = endTime;
        this.consumeMillis  = consumeMillis;
    }
}

