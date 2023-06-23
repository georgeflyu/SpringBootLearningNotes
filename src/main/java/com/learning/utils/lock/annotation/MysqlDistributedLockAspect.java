package com.learning.utils.lock.annotation;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.learning.utils.lock.mysql.MysqlLockImpl;

@Component
@Aspect
public class MysqlDistributedLockAspect {

    @Resource
    private MysqlLockImpl mysqlLock;

    @Pointcut("@annotation(com.learning.utils.lock.annotation.MysqlDistributedLock)")
    private void mysqlDistributedLockPointCut() {
    }


    @Around("mysqlDistributedLockPointCut()")
    public void mysqlDistributedLock(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        MysqlDistributedLock mysqlDistributedLock = signature.getMethod().getAnnotation(MysqlDistributedLock.class);
    }
}
