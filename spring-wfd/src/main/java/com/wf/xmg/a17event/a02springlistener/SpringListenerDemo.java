package com.wf.xmg.a17event.a02springlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class SpringListenerDemo {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();

		// 注册监听器，只注册了监听器，事件是怎么发布出去的，监听器是怎么接收到事件的
		context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("接收到sprig的事件 ："+event);
			}
		});


		context.addApplicationListener(new ApplicationListener<ContextRefreshedEvent>() {
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {
				System.out.println("接收到sprig的事件 ："+event);
			}
		});

//		为什么我们只需要注册监听器就可以进行完成事件的监听，而事件是从哪里来的，是谁发布出来的呢？
		 context.refresh(); // refresh 方法进行事件处理
		//context.start();
		context.close();
	}
}
