package com.wf.xmg.a09beanLifecycle.a06applicationContextLife;

public class BeanApplicationContextTest {

	/**
	 *  ApplicationContext 是应用级别的BeanFactory，是对BeanFactory的包装
	 *
	 *  针对生命周期中，
	 *  1.合并beanDefinition，处理父子关系，将GenericBeanDefinition合并到RootBeanDefinition
	 *  2.合并之后开始进行实例化， 分为实例化前操作，实例化操作，实例化后操作
	 *  	实例化前操作，通过InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation进行实现
	 *  	实例化操作，通过SmartInstantiationAwareBeanPostProcessor#determineCandidateConstructors方法确定构造器进行实例化
	 *
	 *  3.针对ApplicationContext,在实例化操作之后，实例化后操作之前，会进行一个MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition合并beanDefinition的操作
	 *  (因为如果是配置文件，我们可以通过配置文件处理出对应的关系，而在注解中，我们在这里解析处理类与类之间的关系)
	 *
	 *  4.处理完BeanDefinition后，执行我们的实例化后操作，通过InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation进行实现
	 *
	 *  5.处理完之后进入我们的属性填充阶段，分为属性填充前，属性填充
	 *  	属性填充前操作，通过InstantiationAwareBeanPostProcessor#postProcessProperties进行实现
	 *  	属性填充，得到经过属性填充前操作处理的相关属性之后，开始进行赋值
	 *  6.属性填充完之后，进行我们的初始化阶段 分为初始化前，初始化 ，初始化后操作
	 *  	在初始化前阶段，会先执行容器的Aware相关接口的回调，设置容器中的相关属性，
	 *  	然后才执行我们的初始化前操作，由BeanPostProcessor#postProcessBeforeInitialization进行实现
	 *  7.然后开始执行我们的初始化阶段，由我们的InitializingBean#afterPropertiesSet 和 我们自定义的init-method 进行实现
	 *  8.初始化执行完成之后，执行我们的初始化后操作，由BeanPostProcessor#postProcessAfterInitialization进行实现
	 *
	 *
	 *
	 * 应用上下文中有四个重要的BeanPostProcessor
	 *
	 * AutowiredAnnotationBeanPostProcessor 会进行依赖注入的解析和处理，@Autowired 和 @Value 和 @Inject 等
	 *
	 *
	 * CommonAnnotationBeanPostProcessor 进行@Resource 和 @PostConstruct 的解析和处理等
	 *
	 * 1. CommonAnnotationBeanPostProcessor 实现了 postProcessMergedBeanDefinition(合并BeanDefinition) postProcessProperties(处理依赖) postProcessBeforeInitialization(注解父类实现的)
	 * 2. AutowiredAnnotationBeanPostProcessor 实现了 determineCandidateConstructors(确定构造器) postProcessMergedBeanDefinition(合并BeanDefinition) postProcessProperties(处理依赖)
	 *
	 * 3.ApplicationListenerDetector  实现了 postProcessMergedBeanDefinition(合并BeanDefinition)  postProcessAfterInitialization()
	 * 4.ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor 实现了postProcessProperties()  postProcessBeforeInitialization()
	 *
	 *  6.ApplicationContextAwareProcessor 实现了postProcessBeforeInitialization
	 *
	 *  7.PostProcessorRegistrationDelegate.BeanPostProcessorChecker 实现了postProcessAfterInitialization()
	 *
	 */

	public static void main(String[] args) {

	}
}
