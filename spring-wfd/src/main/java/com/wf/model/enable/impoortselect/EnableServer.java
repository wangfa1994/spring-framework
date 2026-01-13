package com.wf.model.enable.impoortselect;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ServerConfigSelector.class)
public @interface EnableServer {

	ServerType type();
}
