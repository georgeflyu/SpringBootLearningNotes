package com.learning.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorization {

    String[] allowUserArray() default {};

    String allowUserListHandler() default "";

    String[] whiteListAdapter() default {};

    boolean debug() default false;
}
