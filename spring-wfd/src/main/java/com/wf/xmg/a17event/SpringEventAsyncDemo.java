package com.wf.xmg.a17event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

// 05 使用  @Async 进行异步的控制
@EnableAsync
public class SpringEventAsyncDemo {
	/**
	 * 以及使用 @Async进行异步处理 ,进行异步处理的时候，又怎么配置我们的线程池呢?
	 *
     *  我们只是通过简单的注解或者配置进行了我们的事件添加，这个底层是怎么处理的呢？他是怎么把事件和事件源关联起来的呢？
	 *
	 *  ApplicationEventPublisher 和 ApplicationEventMulticaster
	 */

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();
		context.register(SpringEventAsyncDemo.class);
		context.refresh();
		context.start();
		context.stop();
		context.close();

	}



	@EventListener
	@Async
	@Order(2)
	public void onStartedEvent0(ContextStartedEvent event){
		System.out.printf("[@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}


	@EventListener
	@Async
	@Order(1)
	public void onStartedEvent1(ContextStartedEvent event){
		System.out.printf("[@EventListener 线程：%s] : %s\n", Thread.currentThread().getName(), event);
	}

	@Bean
	public Executor taskExecutor() {
		ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-1"));
		return taskExecutor;
	}

	@Bean
	@Primary //为什么使用的是taskExecutor 而不是 taskExecutor2呢？ 貌似是根据名称来进行设置的哦 注入了taskExecutor名称
	public Executor taskExecutor2() {
		ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-2"));
		return taskExecutor;
	}

}
