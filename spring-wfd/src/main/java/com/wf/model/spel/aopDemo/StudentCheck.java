package com.wf.model.spel.aopDemo;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StudentCheck {

	String studentId() default "#studentId";
}
