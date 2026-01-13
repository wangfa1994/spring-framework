package com.wf.model.enable.ibdr;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ServerRegistrar.class)
public @interface EnableServer {

	ServerType type();
}
