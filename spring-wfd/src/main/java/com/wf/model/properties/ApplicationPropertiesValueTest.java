package com.wf.model.properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.UnsupportedEncodingException;

@ComponentScan({"com.wf.model.properties"})
@PropertySource({"springConfig.properties"})
public class ApplicationPropertiesValueTest {
	public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等高级容器中存放了一个DefaultListableBeanFactory对象
		System.out.println("hello");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationPropertiesValueTest.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");

		System.out.println(cat.getCategory());

		String property = annotationConfigApplicationContext.getEnvironment().getProperty("hello");
		System.out.println("hello:"+property);


	}

	/**
	 * PropertySource是如何加载的？
	 *
	 *
	 *
	 *
	 *
	 *  spring的@Value 是怎么处理的
	 *
	 * 首先spring会通过 ConfigurationClassPostProcessor进行识别到对应的PropertySources 和 PropertySource 然后进行资源的解析和处理，存放到环境变量中，
	 * 然后再进行依赖处理的时候通过AutowiredAnnotationBeanPostProcessor进行处理，autowired的后置处理器会委托beanFactory进行处理依赖关系。
	 * 在DefaultListableBeanFactory 中进行处理掉@Value的值。
	 *
	 *
	 * 上下文的环境属性是存放在AbstractApplicationContext的 environment变量中。从变量中我们可以得到对应的文件资源
	 *
	 * PropertyPlaceholderHelper 用于处理解析占位符
	 *
	 * PropertySourcesPropertyResolver 中也保存了我们的资源文件，真正的解析之后，获取配置值是在这里处理的
	 *
	 * applicationContext中的environment属性在进行构造器的时候，在进行AnnotatedBeanDefinitionReader实例化的时候进行初始化的。
	 *
	 * 针对ConfigurationClassPostProcessor这个工厂后置处理器，其实现了EnvironmentAware这个接口，所以在创建对象实例的时候通过这个接口的
	 * 回调，把我们容器中的environment变量又赋值给了这个工厂后置处理器，然后再进行PropertySources 和 PropertySource解析的时候，拿到的environment变量
	 * 其实就是容器中的环境上下文，又赋值给了ConfigurationClassParser进行处理，
	 * 所以最后解析出来的属性的值都会被放入到这个容器上下文的环境中，这样的话，在解析依赖的时候，就可以进行使用。
	 *
	 *
	 *
	 * PropertyEditor 属性编辑器	注意这个是来自java的类
	 * PropertyEditorSupport 属性编辑器支持者，注意这个是来自java的类
	 * PropertyResolver
	 *
	 * ResourceEditor
	 *
	 *
	 *
	 */
}
