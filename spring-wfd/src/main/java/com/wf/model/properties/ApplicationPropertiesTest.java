package com.wf.model.properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationPropertiesTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等高级容器中存放了一个DefaultListableBeanFactory对象
		System.out.println("hello");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");

		System.out.println(cat.getCategory());

		String property = annotationConfigApplicationContext.getEnvironment().getProperty("hello");
		System.out.println("hello:"+property);


	}

	/**
	 *  spring的@Value 是怎么处理的
	 *
	 * 首先spring会通过 ConfigurationClassPostProcessor进行识别到对应的PropertySources 和 PropertySource 然后进行资源的解析和处理，存放到环境变量中，
	 * 然后再进行依赖处理的时候通过AutowiredAnnotationBeanPostProcessor进行处理，autowired的后置处理器会委托beanFactory进行处理依赖关系。
	 * 在DefaultListableBeanFactory 中进行处理掉@Value的值。
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */


}
