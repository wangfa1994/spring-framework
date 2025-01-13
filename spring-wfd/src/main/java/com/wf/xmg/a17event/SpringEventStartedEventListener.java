package com.wf.xmg.a17event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

// 监听 ContextStartedEvent
public class SpringEventStartedEventListener implements ApplicationListener<ContextStartedEvent> {
	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), event);
		//可以从事件中得到我们的触发事件的事件源
		ApplicationContext applicationContext = event.getApplicationContext();
		System.out.println("事件源"+applicationContext);
	}
}
