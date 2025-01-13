package com.wf.xmg.a17event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// a06 关于将spring监听器与 数据源关联起来的 ApplicationEventPublisher
public class SpringEventPublisherDemo {

	public static void main(String[] args) {

		// ApplicationEventPublisher 事件发布接口 是 ApplicationContext的父接口，这样的话，ApplicationContext 就都存在了 事件发布功能
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		//context.publishEvent(); // 事件的话，要实现EventObject接口,在spring中针对EventObject又进行了抽象 ApplicationEvent ，这个里面

	}

}
