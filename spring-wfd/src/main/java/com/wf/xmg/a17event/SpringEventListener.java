package com.wf.xmg.a17event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

// 监听ApplicationEvent 的事件监听器  ApplicationEvent 包括四类
public class SpringEventListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}
}
