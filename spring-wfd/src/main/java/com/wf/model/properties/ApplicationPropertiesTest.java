package com.wf.model.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.UnsupportedEncodingException;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
@Configuration
@PropertySource({"springConfig.properties"}) //propertySource也可以加载yml配置文件，看ymlProperties包
public class ApplicationPropertiesTest {

	@Value("${cat.category}")
	private String category;

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等高级容器中存放了一个DefaultListableBeanFactory对象
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationPropertiesTest.class);
		ApplicationPropertiesTest test = (ApplicationPropertiesTest) annotationConfigApplicationContext.getBean("applicationPropertiesTest");
		System.out.println("category:"+test.category);

		String property = annotationConfigApplicationContext.getEnvironment().getProperty("hello");
		System.out.println("hello:"+property);


	}

	/**
	 * @PropertySource是如何加载的？
	 *
	 * 先看propertySource注解中可以配置的属性，
	 *  name:名称 value:文件路径，ignoreResourceNotFound:资源加载失败时是不是进行忽略，encoding:文件的编码，factory:解析资源的工厂类
	 *
	 *  在进行容器初始化的时候，会进行 ConfigurationClassPostProcessor 这个beanFactoryPostProcessor的加载，加载完之后，通过回调方法会进行解析对应的配置文件
	 *
	 *  ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry()进行配置文件的解析，然后会委派给ConfigurationClassParser进行解析我们的propertySource
	 *  ConfigurationClassParser#processPropertySource() //进行解析我们的资源
	 *
	 *  在这里会进行@PropertySource 注解的解析，根据配置的相关属性，然后通过resourceClassLoad进行资源加载，然后会把解析出来的数据存放到对应的环境的属性中。
	 *
	 *  不同的资源，通过配置不同的 PropertySourceFactory 工厂进行得到不同的 PropertySource
	 *
	 *
	 *
	 * DefaultPropertySourceFactory类 默认的资源加载
	 * ResourcePropertySource类 资源文件
	 * PropertySource 抽象类
	 *
	 * PropertySourceFactory 创建我们的 PropertySource
	 *
	 *
	 *
	 *
	 */


}
