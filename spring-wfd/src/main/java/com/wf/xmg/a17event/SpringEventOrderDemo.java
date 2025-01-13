package com.wf.xmg.a17event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

// 04  spring 中关于监听器的顺序 @Order
public class SpringEventOrderDemo {
	/**
	 * 我们可以通过order注解进行排序的处理
	 * order 可以处理排序， 是否还有控制异步的呢？
	 * @Async
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();
		context.register(SpringEventOrderDemo.class);
		context.refresh();
		context.start();
		context.stop();
		context.close();

	}



	@Order(2)
	@EventListener
	public void onStartedEvent0(ContextStartedEvent event){ //制定的都为 ContextStartedEvent 事件
		System.out.printf("[0@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}


	@Order(1) //越小优先级越高
	@EventListener
	public void onStartedEvent1(ContextStartedEvent event){ //制定的都为 ContextStartedEvent 事件

		System.out.printf("[1@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}

}
