package com.wf.xmg.a18annotation.a02customerAnn;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component // 这个注解此时编程了元注解
public @interface MyComponent {
}
