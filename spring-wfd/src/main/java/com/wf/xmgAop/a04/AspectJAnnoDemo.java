package com.wf.xmgAop.a04;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * aspectJ 注解驱动
 */
@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Aspect // 生命是一个切面
//@Configuration // 不标注这个注解没有激活aspect 这个是为什么呢？ 要想使Aspect生效，首先的条件是要识别出这个类被spring管理了??
public class AspectJAnnoDemo {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AspectJAnnoDemo.class);

		applicationContext.refresh();

		Object proxy = applicationContext.getBean("aspectJAnnoDemo");

		System.out.println(proxy);


	}


}
