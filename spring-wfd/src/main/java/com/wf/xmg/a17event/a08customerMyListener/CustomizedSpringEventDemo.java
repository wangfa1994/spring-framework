package com.wf.xmg.a17event.a08customerMyListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 *  自定义 Spring 事件
 */
public class CustomizedSpringEventDemo {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();

		// 1.添加自定义 Spring 事件监听器
		// ListenerRetriever -> 0 .. N 个 ApplicationListener<MySpringEvent> 实例
		// MySpringEvent 以及它子孙类
		context.addApplicationListener(new MySpringEventListener());

		// 2.启动 Spring 应用上下文
		context.refresh();

		// 3. 发布自定义 Spring 事件
		// ListenerCacheKey -> MySpringEvent
		context.publishEvent(new MySpringEvent("Hello,World"));


		// 4. 关闭 Spring 应用上下文
		context.close();
	}
}
