package com.wf.xmgAop.a04;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * aspectJ xml驱动
 *
 */
@Aspect
public class AspectJXmlDemo {


	public static void main(String[] args) {

		// xml 通过 <aop:aspectj-autoproxy></aop:aspectj-autoproxy> 配置激活aspect
		ClassPathXmlApplicationContext context  = new
				ClassPathXmlApplicationContext("classpath:/META-INF/aop/a04/aspect.xml");


		AspectJXmlDemo bean = context.getBean(AspectJXmlDemo.class);
		System.out.println(bean);



	}


}
