package com.wf.xmg.a17event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

// 03  spring 中关于监听的注解  @EventListener (可以通过api，Bean定义 和 @EventListener 进行对应的监听器注册)
@EnableAsync
public class SpringEventAnnDemo {

	/**
	 * 使用  @EventListener 可以直接在方法中标记注解，然后这样的话，就可以一个类中进行多个监听器逻辑的书写，
	 * bean的定义只能控制一个，
	 * 存在多个的时候，我们是否可以调整顺序呢？order注解
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();
		context.register(SpringEventAnnDemo.class);
		context.refresh();
		context.start();
		context.stop();
		context.close();

	}



	@EventListener() // 参数方法怎么确定的呢？
	public void onStartedEvent0(ContextStartedEvent event){
		System.out.printf("[@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}


	@EventListener
	public void onStartedEvent1(ContextStartedEvent event){
		System.out.printf("[@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}

}
