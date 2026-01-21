package com.wf.model.fullandlite;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.wf.model.fullandlite")
public class ApplicationContextTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(ApplicationContextTest.class);

		Person configPersonA = (Person)context.getBean("configPersonA");
		Person configPersonB = (Person)context.getBean("configPersonB");
		System.out.println("configPersonA cat "+ configPersonA.getCat());
		System.out.println("configPersonB cat "+ configPersonB.getCat()); // 相同，因为采用的是full模式

		Person componentPerson1 = (Person)context.getBean("componentPerson1");
		Person componentPerson2 = (Person)context.getBean("componentPerson2");
		System.out.println("componentPerson2 cat "+ componentPerson1.getCat());
		System.out.println("componentPerson2 cat "+ componentPerson2.getCat()); //不同，因为采用的是lite模式

		// 在解析配置文件产生BeanDefinition的时候进行判断，

	}
}
