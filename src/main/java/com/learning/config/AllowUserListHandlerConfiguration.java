package com.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.annotations.aspect.AuthorizationAspect;

@Configuration
public class AllowUserListHandlerConfiguration {

    @Bean("w3")
    public AuthorizationAspect.AllowUserListHandler allowUserListHandler() {
        return (userList) -> {
            for (String s : userList) {
                if (s.equals("gwx1116577")) {
                    return true;
                }
            }
            return false;
        };
    }
}
