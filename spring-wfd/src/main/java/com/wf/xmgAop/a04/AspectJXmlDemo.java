package com.wf.xmgAop.a04;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** spring aop 编程模型
 *
 * aspectJ xml驱动
 * spring Extensable xml authoring
 *
 * AOPNamespaceHandler  命名空间的解析handler
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
