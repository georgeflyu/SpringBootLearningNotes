package com.learning.annotations.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.learning.annotations.Authorization;
import com.learning.entity.ResponseData;

@Aspect
@Component
public class AuthorizationAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Around("@annotation(authorization)")
    public Object around(ProceedingJoinPoint pjp, Authorization authorization) throws Throwable {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        if (Arrays.asList("0:0:0:0:0:0:0:1", "127.0.0.1").contains(remoteAddr) && !authorization.debug()) {
            pjp.proceed();
        }
        Object[] args = pjp.getArgs();
        String allowUserListHandlerName = authorization.allowUserListHandler();
        String[] allowUserArray = authorization.allowUserArray();
        if (ArrayUtils.isNotEmpty(allowUserArray)) {
            AllowUserListHandler allowUserListHandler =
                (AuthorizationAspect.AllowUserListHandler) applicationContext.getBean(allowUserListHandlerName);
            if (allowUserListHandler.allowUserListHandle(allowUserArray)) {
                return pjp.proceed();
            }
        }

        String[] whiteListAdapterArray = authorization.whiteListAdapter();
        if (ArrayUtils.isNotEmpty(whiteListAdapterArray)) {
            for (String whiteListAdapterName : whiteListAdapterArray) {
                WhiteListAdapterHandler whiteListAdapterHandler = (WhiteListAdapterHandler) applicationContext.getBean(
                    whiteListAdapterName);
                if (whiteListAdapterHandler.WhiteListAdapterHandle(method, args)) {
                    return pjp.proceed();
                }
            }
        }
        return ResponseData.of(401, "Unauthorized", "未授权");
    }


    @FunctionalInterface
    public interface AllowUserListHandler {

        boolean allowUserListHandle(String[] userArray);
    }


    public interface WhiteListAdapterHandler {

        boolean WhiteListAdapterHandle(Method method, Object[] args);
    }
}
