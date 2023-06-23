package com.learning.annotations.entity;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRecordEntity {

    private String logType;

    private String logName;

    private String logDescription;

    private String requestMethod;

    private String requestUrl;

    private String serverName;

    private int serverPort;

    private String contextPath;

    private String queryString;

    private String requestBody;

    private String className;

    private String methodName;

    private String methodArgs;

    private String exceptionMsg;

    private Object result;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private long consumeMills;
}
