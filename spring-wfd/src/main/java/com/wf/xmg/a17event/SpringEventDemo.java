package com.wf.xmg.a17event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

// 02 spring的事件机制 通过api 和 @bean模式
public class SpringEventDemo {

	public static void main(String[] args) {
		//apiEvent();
		//annotationBeanEvent();
		annotationBeanEventByStarted();
	}

	// 基于api的事件注册
	public static void apiEvent(){
		//Spring的事件机制是在引用上下文中
		GenericApplicationContext applicationContext = new GenericApplicationContext();

		// 在applicationContext对象上 添加一个我们的事件监听器,
		// 监听器ApplicationListener 是 EventListener 标记的接口 ，而 EventListener 是java的标准事件接口
		// 事件 ApplicationEvent 继承了 EventObject ，里面包含了触发事件的源对象， (他们两个是怎么绑定起来的呢？)
		applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				println("ApplicationListener - 接收到 Spring 事件：" + event);
			}
		});

		applicationContext.refresh(); // 刷新的时候会触发 刷新事件 ContextRefreshedEvent，然后通知到监听器进行处理
		applicationContext.start(); // 启动的时候会触发 开始事件 ContextStartedEvent，然后通知监听器进行处理
		applicationContext.stop(); // 停止的时候会触发 结束事件 ContextStoppedEvent，然后通知监听器进行处理
		applicationContext.close(); // 关闭的时候会触发 结束事件 ContextClosedEvent，然后通知监听器进行处理
		// 内置了四种事件
	}

	// 基于注解@Bean的事件注册
	public static void annotationBeanEvent(){
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();

		context.register(SpringEventDemo.class);// 通过注解方式，注入我们的事件监听器

		context.refresh(); // 刷新的时候会触发 刷新事件 ContextRefreshedEvent，然后通知到监听器进行处理
		context.start(); // 启动的时候会触发 开始事件 ContextStartedEvent，然后通知监听器进行处理
		context.stop(); // 停止的时候会触发 结束事件 ContextStoppedEvent，然后通知监听器进行处理
		context.close();
	}

	//@Bean // 通过注解方式，注入我们的事件监听器 这个是监听 ApplicationEvent
	public SpringEventListener springEventListener(){
		return new SpringEventListener(); // 这个是否可以更改为指定的事件监听器呢
	}


	// 基于注解@Bean的事件注册 注册指定数据源的事件 ContextStartedEvent事件
	private static void annotationBeanEventByStarted() {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();

		context.register(SpringEventDemo.class);// 通过注解方式，注入我们的事件监听器

		context.refresh();
		context.start(); // 此时只会触发一个 StartedEvent ,触发其实就是内部调用了一个发布事件的方法
		context.stop();
		context.close();
		System.out.println("我们的容器"+context);
	}

	@Bean // 通过注解方式，注入我们的事件监听器 这个是监听 ApplicationEvent
	public SpringEventStartedEventListener springEventStartedEventListener(){
		return new SpringEventStartedEventListener(); // 这个是否可以更改为指定的事件监听器呢
	}




	private static void println(Object printable) {
		System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
	}
}
