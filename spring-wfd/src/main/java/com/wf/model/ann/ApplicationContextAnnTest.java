package com.wf.model.ann;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextAnnTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等高级容器中存放了一个DefaultListableBeanFactory对象
		System.out.println("hell");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AnnConfiguration.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat);
		Person person = (Person) annotationConfigApplicationContext.getBean("person");
		System.out.println(person);




    }

	/**
	 * 关键的一些后置处理器
	 *
	 * ConfigurationClassPostProcessor 用于解析配置类的BeanFactory后置处理器，解析配置文件中的相关beanDefinition
	 * AutowiredAnnotationBeanPostProcessor 解析自动注册的BeanPost后置处理器，
	 * CommonAnnotationBeanPostProcessor 通用的
	 */

/**创建AnnotationConfigApplicationContext对象，
 * 1.this()
 * 		 1.0 调用AbstractApplicationContext构造器创建PathMatchingResourcePatternResolver解析资源的类
 * 		 	调用GenericApplicationContext构造器，创建DefaultListableBeanFactory实例
 * 		 	创建DefaultListableBeanFactory实例时，，调用父类AbstractAutowireCapableBeanFactory构造器，添加依赖注入的忽略项BeanNameAware,BeanFactoryAware,BeanClassLoaderAware
 * 		 	设置实例化策略为CglibSubclassingInstantiationStrategy
 * 		 1.1创建AnnotatedBeanDefinitionReader对象
 * 		 	1.1.0创建StandardEnvironment对象
 * 		 	1.1.1利用AnnotationConfigUtils.registerAnnotationConfigProcessors检查并创建一些BeanDefinitionRegistry所必须的属性
 * 		 	实例：AnnotationAwareOrderComparator/ContextAnnotationAutowireCandidateResolver(QualifierAnnotationAutowireCandidateResolver，Qualifier注解)
 * 		    设置对应所需的RootBeanDefinition: ConfigurationClassPostProcessor/AutowiredAnnotationBeanPostProcessor
 * 		    选择性注册CommonAnnotationBeanPostProcessor(JSR-250)/org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor(JPA)
 * 		    继续注册EventListenerMethodProcessor
 * 		1.2创建 ClassPathBeanDefinitionScanner 对象
 * 			1.2.1 利用ClassPathScanningCandidateComponentProvider.registerDefaultFilters注册默认的@Component，AnnotationTypeFilter
 *
 * 2.register()
 *  1.1 利用AnnotatedBeanDefinitionReader进行注册配置类doRegisterBean，将配置类翻译成AnnotatedGenericBeanDefinition,并且利用AnnotationScopeMetadataResolver/AnnotationBeanNameGenerator产生对应的信息
 *      在生成对应的beanName时，利用的jdk的Introspector.decapitalize(shortClassName)，此方法一般会将第一个大写转化为小写，但是如果遇到了前两个都是大写的就不做处理了
 *      利用AnnotationConfigUtils.processCommonDefinitionAnnotations(abd)进行填充其他属性Lazy,Primary,DependsOn,Role,Description
 *      填充之后根据@Scope的代理模式确定是否产生对应的代理beanDefinition,然后进行注册到我们的BeanDefinitionMap中
 *
 * 3.refresh()
 * 	3.1 prepareRefresh()
 * 		初始化applicationListeners、earlyApplicationEvents属性
 * 	3.2 obtainFreshBeanFactory()
 * 		获取一个FreshBeanFactory，注解模式下设置id(在xml模式下，会在此处解析文件生成bd)
 * 	3.3 prepareBeanFactory(beanFactory)
 * 		3.3.1解析器beanExpressionResolver设置，初始化SpelExpressionParser，用来解析对应的spel表达式
 * 		3.3.2设置propertyEditorRegistrars,用来设置自定义资源解析器 ResourceEditorRegistrar
 * 	    3.3.3***添加第一个ApplicationContextAwareProcessor后置处理器。并且在构造器中实例化EmbeddedValueResolver解析器
 * 	    3.3.4再次添加添加依赖注入的忽略项，这些接口可以通过其他方式进行注入 EnvironmentAware,EmbeddedValueResolverAware,ResourceLoaderAware,ApplicationEventPublisherAware,MessageSourceAware,ApplicationContextAware,ApplicationStartupAware
 * 	    3.3.5设置resolvableDependencies注册可以自动装配的属性, BeanFactory,ResourceLoader,ApplicationEventPublisher,ApplicationContext
 * 	    3.3.6***添加第二个ApplicationListenerDetector后置处理器
 * 	    3.3.7***试图添加第三个对应的LoadTimeWeaverAwareProcessor后置处理器
 * 	    3.3.8注册默认的单实例bean，这些bean没有bd,直接初始化扔进去的，environment，systemProperties,systemEnvironment,applicationStartup
 * 	3.4 postProcessBeanFactory()
 *
 * 	3.5 invokeBeanFactoryPostProcessors(beanFactory) 实例化并调用所有的BeanFactoryPostProcessor
 * 		3.5.1 通过PostProcessorRegistrationDelegate的invokeBeanFactoryPostProcessors获取beanFactory中对应的beanFactoryPostProcessors的FactoryPostProcessors进行调用,此时系统中的FactoryPostProcessors为空
 * 			 3.5.1.1先拿到底层默认有的BeanFactoryPostProcessor,此时系统中的FactoryPostProcessors为空
 * 			 3.5.1.2 通过beanFactory.getBeanNamesForType()获取BeanDefinitionRegistryPostProcessor类型的bean的名称(继承了BeanFactoryPostProcessor)
 * 			 	3.5.1.2.1 通过属性beanDefinitionNames获取到现在系统中的所有beanNames,然后进行循环与传入的BeanDefinitionRegistryPostProcessor进行对比，
 * 			  			 此时系统中的beanNames只有配置类AnnotatedGenericBeanDefinition类型和系统初始化的四个beanName，对应的就是RootBeanDefinition，org.springframework.context.annotation.internalConfigurationAnnotationProcessor, org.springframework.context.annotation.internalAutowiredAnnotationProcessor, org.springframework.context.event.internalEventListenerProcessor, org.springframework.context.event.internalEventListenerFactory, annConfiguration
 * 			  			 循环中
 * 			  			 1.getMergedLocalBeanDefinition()产生出对应新的RootBeanDefinition 并且放入到对应的mergedBeanDefinitions属性中，如果本来就是RBD,则会进行clone新对象放入属性中
 * 			  			 2.isFactoryBean()判断是否是FactoryBean, 会调用预测方法predictBeanType(beanName, mbd, FactoryBean.class)进行RBD的目标类型的预测，并将计算出的类型，设置到对应的RBD的resolvedTargetType属性中和isFactoryBean属性中
 * 			  			 此时在方法predictBeanType中还会循环我们系统中的InstantiationAwareBeanPostProcessors后置处理器，来进行判断是否有通过此种方式改变我们的beanName类型的，此时系统中只有两个BeanPostProcess,不会进行任何逻辑处理，注意此时会第一次通过beanPostProcessors属性初始化我们的BeanPostProcessCache,此时系统中值存在两个BeanPostProcess，但是只有一个postprocess对应两个缓存值ApplicationListenerDetector===>DestructionAwareBeanPostProcessor/MergedBeanDefinitionPostProcessor，
 * 			  			 3.不管是否是FactoryBean，则都会进行isTypeMatch的处理，这里会第一次进行getSingleton()判断当前的beanName是否是在创建中，如果不在，则会通过父子容器进行在判断,然后会在进行BeanDefinition检索，利用的就是合并的BeanDefinition属性,最后再通过predictBeanType()去预测下类型，此时又会进行后置处理器的调用
 * 			  			   3.1 如果是FactoryBean的话，会在beanName上添加&符号进行匹配
 * 			  	3.5.1.2.2 还要处理我们手动加入的手动注册的单例对象的名称列表进行匹配
 * 			3.5.1.3 获取到匹配类型的BeanNames后，会*循环*再进行匹配PriorityOrdered.class类型匹配。此时优先级最高的是获取到系统中有一个名为org.springframework.context.annotation.internalConfigurationAnnotationProcessor的	BeanDefinitionRegistryPostProcessor，这个就是 先前创建的ConfigurationClassPostProcessor
 * 		    3.5.1.4 然后会进行beanFactory.getBean()创建对应的	BeanDefinitionRegistryPostProcessor实例 注意这是第一个创建的实例时BeanFactoryProcess的后置处理器实例(如果我们自定义实现了BeanDefinitionRegistryPostProcessor，此时也会进行循环创建)
 * 		    3.5.1.5 *** 创建完对象之后，会紧接着将创建过对象的后置处理器进行排序按照优先级，然后再进行PostProcessorRegistrationDelegate#invokeBeanDefinitionRegistryPostProcessors()来执行对应的postProcessBeanDefinitionRegistry方法的后置处理器的逻辑 ***注意ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(registry)在这个逻辑中，会解析我们的配置类，然后将我们对应的信息变成对应的BeanDefinition
 * 			3.5.1.6 同3.5.1.2 再次调用getBeanNamesForType进行获取BeanDefinitionRegistryPostProcessor,此时是创建匹配Ordered.class优先级的后置处理器，然后再按照优先级进行执行，
 * 			3.5.1.7 同3.5.1.2 	再次调用getBeanNamesForType进行获取BeanDefinitionRegistryPostProcessor，此时是创建没有优先级的一般是我们自定义的，然后再进行执行对应的后置处理器。
 * 			3.5.1.8 执行完对应的postProcessBeanDefinitionRegistry()后置处理器之后，会再按照从上到下保存起来的后置处理器的invokeBeanFactoryPostProcessors()方法
 * 					***在ConfigurationClassPostProcessor#postProcessBeanFactory()中会注入我们系统中的第三个后置处理器ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor#ImportAwareBeanPostProcessor
 * 		    3.5.1.9 通过getBeanNamesForType进行获取BeanFactoryPostProcessor的beanName,然后进行遍历，分到三个组中，PriorityOrdered，Ordered和自定义组中，并且在分组的时候会先进行PriorityOrdered的实例化，
 * 		            然后排序进行执行PriorityOrdered组的invokeBeanFactoryPostProcessors方法，然后再初始化Ordered组的实例，排序调用，最后初始化自定义的实例，调用
 *
 *     3.5.2 通过
 *
 *  3.6 registerBeanPostProcessors() 注册所有的beanPostProcessors，将所有的beanPostProcessors进行实例化，并且放入到我们的属性中个·
 *  	3.6.1 通过PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this)方法进行实例化我们的beanPostProcess对象。
 *          3.6.1.1 通过 beanFactory.getBeanNamesForType 获取到系统中存在的BeanPostProcessor.class的beanNames，此时系统中存在一个org.springframework.context.annotation.internalAutowiredAnnotationProcessor
 *          3.6.1.2 创建BeanPostProcessorChecker对象，用来处理在创建beanPostProcessor过程中的beanPostProcess, 注意此时创建的BeanPostProcessorChecker对象 也是benaPostProcessor;
 *          3.6.1.3 将创建的BeanPostProcessor放入到我们的beanPostProcessors属性中，此时存在4个。ApplicationContextAwareProcessor,ApplicationListenerDetector,ImportAwareBeanPostProcessor(ConfigurationClassPostProcessor内部类)以及刚加入的BeanPostProcessorChecker
 *          3.6.1.4 循环我们的beanNames,对其进行分组排序，先装入PriorityOrdered，直接进行实例化，注意此时会进行判断实例化出来的对象是否是MergedBeanDefinitionPostProcessor，如果是会进行单独存放，用来处理存在父子关系的后置处理器,AutowiredAnnotationBeanPostProcessor会放入进去
 *      	3.6.1.5 将我们实例化的PriorityOrdered的beanPostProcessor放到我们的beanPostProcessors属性中去
 *       	3.6.1.6 处理我们分组的Ordered的beanPostProcessor的beanNames,将此类对象进行实例化并且判断是否是MergedBeanDefinitionPostProcessor,如果是都会放入到internalPostProcessors中，实例化之后，会将对象先排序再放到我们的beanPostProcessors属性中去
 *       	3.6.1.7 处理我们没有优先级的beanPostProcessor的信息，也是先进行实例化，判断是否是MergedBeanDefinitionPostProcessor,放入internalPostProcessors变量中，然后将实例对象添加到我们的beanPostProcessors属性中去。
 *       	3.6.1.8 最后我们会将我们的internalPostProcessors变量中的beanPostProcessor进行排序，然后重新放入到我们的beanPostProcessors中去，此步骤是为了将我们的mergerBeanDefinitionPostProcessor最后处理我们的bean
 *       	3.6.1.9 重新注册我们的ApplicationListenerDetector
 *             *** 此时我们的系统中存在内置的beanPostProcessors,并且按照顺序为：ApplicationContextAwareProcessor，ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor,PostProcessorRegistrationDelegate$BeanPostProcessorChecker
 *             			AutowiredAnnotationBeanPostProcessor,ApplicationListenerDetector
 *
 * 3.7
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *BeanFactoryPostProcessor
 *  -- BeanDefinitionRegistryPostProcessor -- ConfigurationClassPostProcessor(实现了BeanDefinitionRegistryPostProcessor)
 *
 *
 *BeanPostProcessor
 * -- ApplicationContextAwareProcessor
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * AnnotationConfigApplicationContext
 * DefaultResourceLoader
 * AbstractApplicationContext
 * PathMatchingResourcePatternResolver
 * GenericApplicationContext
 * DefaultListableBeanFactory
 * AbstractAutowireCapableBeanFactory
 * InstantiationStrategy
 * SimpleInstantiationStrategy
 * CglibSubclassingInstantiationStrategy
 * SimpleAutowireCandidateResolver
 *
 * StandardEnvironment
 * AnnotatedBeanDefinitionReader
 * AnnotationConfigUtils
 * BeanDefinitionRegistry
 * AnnotationAwareOrderComparator
 * ContextAnnotationAutowireCandidateResolver
 * QualifierAnnotationAutowireCandidateResolver
 * Qualifier
 * AnnotationConfigUtils
 * ConfigurationClassPostProcessor
 * BeanDefinitionHolder
 * AutowiredAnnotationBeanPostProcessor
 * CommonAnnotationBeanPostProcessor
 * PersistenceAnnotationBeanPostProcessor
 * EventListenerMethodProcessor
 * DefaultEventListenerFactory
 *
 * 1.2
 * ClassPathBeanDefinitionScanner
 * ClassPathScanningCandidateComponentProvider
 * AnnotationTypeFilter
 *
 * 2.1
 * AnnotatedGenericBeanDefinition
 * AnnotationMetadata
 * StandardAnnotationMetadata
 * MergedAnnotations
 * TypeMappedAnnotations
 * AnnotationScopeMetadataResolver
 * ScopeMetadata
 * AnnotationBeanNameGenerator
 * AnnotationConfigUtils
 * ClassUtils.getShortName(beanClassName)
 * AutowireCandidateQualifier
 * AnnotationConfigUtils.applyScopedProxyMode
 * ScopedProxyUtils.createScopedProxy
 * BeanDefinitionReaderUtils.registerBeanDefinition
 *
 * 3.3
 * StandardBeanExpressionResolver
 * SpelExpressionParser
 * SpelParserConfiguration
 * ResourceEditorRegistrar
 * PropertyResolver
 * ApplicationContextAwareProcessor
 * BeanExpressionContext
 * EmbeddedValueResolver
 * ApplicationListenerDetector
 * NativeDetector.inNativeImage()
 * LoadTimeWeaverAwareProcessor
 * ContextTypeMatchClassLoader
 * DefaultSingletonBeanRegistry.registerSingleton
 *
 * 3.5
 * PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors() 核心
 * BeanDefinitionRegistryPostProcessor
 * DefaultListableBeanFactory#getBeanNamesForType()
 * AbstractBeanFactory#isFactoryBean()
 * AbstractAutowireCapableBeanFactory#predictBeanType()
 * AbstractBeanFactory#isTypeMatch()
 * BeanFactoryUtils.transformedBeanName(name)
 * BeanFactoryUtils.isFactoryDereference(name)
 * DefaultSingletonBeanRegistry#getSingleton()
 * ResolvableType
 * PriorityOrdered
 * postProcessor.postProcessBeanDefinitionRegistry(registry)
 * postProcessor.postProcessBeanFactory(beanFactory);
 *
 *
 *
 *3.6
 * PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this)
 * BeanPostProcessorChecker
 *
 * MergedBeanDefinitionPostProcessor
 *
 * 3.7
 * ConversionService
 * StringValueResolver
 * ConfigurablePropertyResolver
 *
 *
 * LoadTimeWeaverAware
 *
 *
 *
 * org.springframework.beans.factory.support.AbstractBeanFactory#transformedBeanName(java.lang.String)
 *  * org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, boolean)
 *  * org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#isSingletonCurrentlyInCreation(java.lang.String)
 *  org.springframework.beans.factory.support.AbstractBeanFactory#getObjectForBeanInstance(java.lang.Object, java.lang.String, java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition)
 *  org.springframework.beans.factory.support.AbstractBeanFactory#adaptBeanInstance(java.lang.String, java.lang.Object, java.lang.Class)
 *  * TypeConverter
 */



}
