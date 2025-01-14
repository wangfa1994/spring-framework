package com.wf.xmg.a17event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// a06 关于将spring监听器与 数据源关联起来的 ApplicationEventPublisher
public class SpringEventPublisherDemo  implements ApplicationEventPublisherAware,ApplicationContextAware, InitializingBean {

	/**
	 * ApplicationEventPublisher接口通过publishEvent方法发布事件
	 *
	 * 通过 ApplicationEventPublisherAware 的接口回调得到我们的 ApplicationEventPublisher
	 *
	 * 由于我们的ApplicationContext抽象类继承了 ApplicationEventPublisher 所以，所有的ApplicationContext都可以进行发布事件
	 *
	 * spring 内置了对应的ApplicationEventPublisher ，可以直接自动注入
	 *
	 *
	 */

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;


	public static void main(String[] args) {

		// ApplicationEventPublisher 事件发布接口 是 ApplicationContext的父接口，这样的话，ApplicationContext 就都存在了 事件发布功能
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SpringEventPublisherDemo.class);

		// 添加监听
		context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println(String.format("监听到消息 %s",event));

			}
		});
		context.refresh();
		context.close();

	}

	// 获取我们的发布器，进行相关事件的发布
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new ApplicationEvent("ApplicationEventPublisherAware Event") {
		}); // 发布带类型的事件

		//观察 applicationEventPublisher接口的方法，发现他有发布不带Event的方法，这个又是什么样的事件呢？  PayloadApplicationEvent


	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		applicationContext.publishEvent(new ApplicationEvent("ApplicationContextAware Event") {
		}); // 发布带类型的事件
	}


	// 通过 InitializingBean 校验我们的发布器是否可以自动依赖注入
	@Override
	public void afterPropertiesSet() throws Exception {

		applicationEventPublisher.publishEvent(new ApplicationEvent("Autowired  applicationEventPublisher Event") {
		});
		applicationContext.publishEvent(new ApplicationEvent("Autowired  applicationContext Event") {
		});
	}


}
