package com.wf.model.imports.commonBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
* @Desc : import 注入通用 bean 流程
* @Author : Mr.WangF
**/

@Import(CommonBean.class)
public class ApplicationContextCommonBeanTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextCommonBeanTest.class);

		// 当我们使用Import进行导入的时候，容器所管理的类的名称成全限定类名
		CommonBean bean = annotationConfigApplicationContext.getBean("com.wf.model.imports.commonBean.CommonBean",CommonBean.class);
		System.out.println(bean);

		AnotherBean bean1 = annotationConfigApplicationContext.getBean(AnotherBean.class);
		System.out.println(bean1);
	}
	/**
	 *  ApplicationContext上下文，会帮我们注入我们的ConfigurationClassPostProcessor的后置处理器，实现了BeanDefinitionRegistryPostProcessor和 BeanFactoryPostProcessor接口，
	 *  在上下文刷新的时候,会委派给我们的PostProcessorRegistrationDelegate类进行实例化ConfigurationClassPostProcessor。并且调用相关回调方法
	 *  然后就在我们的ConfigurationClassPostProcessor中postProcessBeanDefinitionRegistry：
	 *  这里会从已存在的BeanNames中找到我们的配置类， 会根据BeanName获得到对应的BeanDefinition，然后得到对应的元信息，
	 *  判断是否存在@Configuration，@Component,@ComponentScan,@Import,@ImportResource注解判断是否
	 *
	 *
	 *  然后再处理我们的配置类，解析我们的配置类，变成对应的BeanDefinition注册到我们BeanDefinitionRegistry中
	 *
	 *
	 * org.springframework.context.annotation.ConfigurationClassParser#processImports() 进行处理我们的Imports逻辑
	 *
	 * ConfigurationClassPostProcessor 属于BeanFactoryPostProcessor,在刷新容器的时候，会首先进行ConfigurationClassPostProcessor的执行，
	 * 解析我们的配置类，从配置类中开始向外扩展处理我们的BeanDefinition，并进行注册。会委派给ConfigurationClassParser类进行处理。我们的Import相关
	 * 的逻辑就是在这里进行处理的。
	 *
	 *
	 */
}
