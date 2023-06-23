package com.learning.utils.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MysqlDistributedLock {
    /*
    资源名称
     */
    String resourceName();

    /*
    详细描述
     */
    String description();

    /*
    尝试加锁时间
     */
    long tryLockTime();

    /*
    失效时间
     */
    long expireTime();

    /*
    是否阻塞
     */
    boolean isBlock();

    /*
    是否可重入
     */
    boolean isReentrant();
}
