package com.sssunday.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解-token验证
 * @ClassName: TokenAccess
 * @author: sssunday
 * @date: 2016年9月27日 上午9:20:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenAccess {

}
