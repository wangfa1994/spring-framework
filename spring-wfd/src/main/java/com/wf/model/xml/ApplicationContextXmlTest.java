package com.wf.model.xml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextXmlTest {

    public static void main(String[] args) {

		 // AbstractApplicationContext容器的实现类，根据不同的业务场景选择不同的容器实现，策略模式 和装饰者模式
		//AnnotationConfigApplicationContext FileSystemXmlApplicationContext XmlWebApplicationContext等

        BeanFactory classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beanXml.xml");



        Cat cat = (Cat) classPathXmlApplicationContext.getBean("cat");
        System.out.println("cat:\t" + cat);


        Person person = (Person) classPathXmlApplicationContext.getBean("person");
        System.out.println("person:\t" + person);
        System.out.println("person的cat:\t" + person.getCat());


		Person person2 = (Person) classPathXmlApplicationContext.getBean("person");
		System.out.println("person2:\t" + person2);



    }

	/**
	 * BeanDefinitionReader体系：
	 * 		XmlBeanDefinitionReader
	 *
	 *
	 * 	DocumentLoader
	 * 	DefaultDocumentLoader
	 * BeanDefinitionDocumentReader
	 *
	 *
	 *
	 *
	 *
	 * 系统默认添加的Processor
	 * ApplicationContextAwareProcessor
	 * ApplicationListenerDetector
	 *
	 *
	 *
	 * BeanFactory的后置处理器 ，与bean定义进行交互。用于实例化bean执行之前，新增beanDefinition,改变beanDefinition的属性，
	 * 	BeanDefinitionRegistryPostProcessor ：主要用来处理新增，修改我们工厂中的beanDefinition,经典实用案例：ConfigurationClassPostProcessor
	 * 	BeanFactoryPostProcessor ：主要用来处理我们的beanFactory的属性， ,经典实用案例 PropertyResourceConfigurer
	 *
	 *
	 * 自定义的注册BeanPostProcessors的时候不会执行对应的后置处理器增强，当我们的beanPostProcessors的getBean执行完成产生对象之后，才将我们对应的实例放入对应的列表中。
	 * 如果对象实现了不同的优先级接口，创建完成之后，会直接放到列表中，在创建优先级低的时候，则会进行执行对应的后置处理器。
	 *
	 *
	 *
	 *
	 *doCreateBean()方法流程
	 * --createBeanInstance() 构造器相关的后置增强
	 * 		-- 执行后置处理器SmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors()，创建实例化对象时，自定义所需要的构造器
	 * --applyMergedBeanDefinitionPostProcessors() 实例化对象之后，beanDefinition的相关修改增强
	 * 		-- 执行后置处理器MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition()
	 *
	 *
	 * --populateBean() 属性赋值的相关增强，利用的是属性值增强
	 * 		-- 执行后置处理器InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation(),
	 * 		-- 执行后置处理器InstantiationAwareBeanPostProcessor.postProcessProperties(PropertyValues pvs, Object bean, String beanName)
	 * 		-- 执行后置处理器InstantiationAwareBeanPostProcessor.postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) // 已废弃
	 *
	 *
	 * --initializeBean() 属性的相关增强，利用的是bean对象的增强
	 * 	    --invokeAwareMethods 执行内置的BeanNameAware、BeanClassLoaderAware、BeanFactoryAware接口
	 * 		--执行后置处理器BeanPostProcessor.postProcessBeforeInitialization()，其中Aware接口的回调是第一个ApplicationContextAwareProcessor的postProcessBeforeInitialization执行的。
	 * 		--执行创建对象实现InitializingBean接口的afterPropertiesSet()
	 * 		--执行后置处理器的BeanPostProcessor.postProcessAfterInitialization
	 */

}
