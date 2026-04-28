package com.wf.xmg.a17event.a07payloadApplicationEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

// 07 通过 ApplicationEventPublisher 可以发布默认的事件
public class PayloadApplicationEventDemo  implements ApplicationEventPublisherAware {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(PayloadApplicationEventDemo.class);

		// 添加监听  这个指定为 PayloadApplicationEvent 的事件监听，所以 refresh 和 close则不会被监听到
		// PayloadApplicationEvent 事件可以携带一些信息给我们的监听器，扩展了 ApplicationEvent 不仅指定了来源，也附带了额外信息
		context.addApplicationListener(new ApplicationListener<PayloadApplicationEvent<String>>() {
			@Override
			public void onApplicationEvent(PayloadApplicationEvent<String> event) {
				System.out.println(String.format("PayloadApplicationEvent 监听到消息 %s,附加信息为 %s",event.getSource(),event.getPayload()));
			}
		});

		context.addApplicationListener(new ApplicationListener<ContextRefreshedEvent>() {
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {
				System.out.println(String.format("ContextRefreshedEvent 监听到消息 %s",event.getSource()));
			}
		});
		context.refresh();
		context.close();

	}


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		//applicationEventPublisher.publishEvent(new ContextRefreshedEvent(new GenericApplicationContext()));

		applicationEventPublisher.publishEvent("hello word"); // 调用不被封装的publishEvent方法，会直接被封装为 PayloadApplicationEvent事件,由于PayloadApplicationEvent继承了ApplicationEvent
		// org.springframework.context.support.AbstractApplicationContext.publishEvent(java.lang.Object)

	}
}
