package com.wf.xmg.a18annotation.a04alias;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface MyComponentScan {


	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages") // 隐形别名传递
	String[] scanBasePackages() default {};
}
