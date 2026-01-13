package com.wf.model.stereotype;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;


// 自定义派生注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StringRepository {

	String value() default "";
}
