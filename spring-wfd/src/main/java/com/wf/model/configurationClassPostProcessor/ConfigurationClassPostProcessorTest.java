package com.wf.model.configurationClassPostProcessor;

import com.wf.model.ann.ApplicationContextAnnTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;

// ConfigurationClassPostProcessor 工厂后置处理器的加载与使用
@Configuration //只有标注了这个注解的才会被当做配置类，才能会进行这个类的解析，才能解析出其他的配置信息
@Component
public class ConfigurationClassPostProcessorTest {



	public static void main(String[] args) {

		ConfigurationClassPostProcessor configurationClassPostProcessor = new ConfigurationClassPostProcessor();


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClassPostProcessorTest.class); //注册我们的配置类

		ConfigurationClassPostProcessorTest bean = (ConfigurationClassPostProcessorTest)context.getBean("configurationClassPostProcessorTest");
		System.out.println("===="+bean);
		/**
		 *  ConfigurationClassPostProcessor 主要是工厂的后置处理器，在上下文进行刷新的时候得到容器之后，会首先进行工厂后置处理器的创建，
		 *  创建之后会直接进行回调方法的调用，然后这个工厂后置处理器就会开始解析我们的配置相关，生成对应的beanDefinition,用于在后续的bean的创建。
		 *
		 *  创建AnnotationConfigApplicationContext对象的时候，在构造器中会进行AnnotatedBeanDefinitionReader的实例化，在这个实例化中会进行
		 *  注解所需要的后置处理器的注册，通过 AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry)会进行默认的后置处理器注册
		 *  这里就会把对应的ConfigurationClassPostProcessor的BeanDefinition进行注册到对应的beanDefinitionMap中
		 *  然后在创建AnnotationConfigApplicationContext的第三步refresh的时候，在对应的BeaFactory的逻辑方法中 invokeBeanFactoryPostProcessors方法中
		 *  进行了关于工厂后置处理器的实例化和调用，首先委派给PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors进行调用
		 *  ConfigurationClassPostProcessor 属于 BeanDefinitionRegistryPostProcessor 属于 BeanFactoryPostProcessor
		 *  实现了postProcessBeanDefinitionRegistry，进行解析我们的BeanDefinition并进行注册到注册中心去，
		 *  至此就完成了ConfigurationClassPostProcessor注册所有的BeanDefinition。
		 *
		 *  configurationClassPostProcessor 是如何解析配置文件的？ ConfigurationClassPostProcessor#processConfigBeanDefinitions()
		 *  首先从注册中心获取到我们的所有BeanDefinitionNames,然后遍历这些找出属于我们的配置类，这里只会找出我们进行手动注册的配置类，这里需要注意
		 *  的是，我们的配置类一定要添加@Configuration注解，这样的话才能被识别成(完全体)配置类
		 *  (其实如果含有其他四个注解Component/ComponentScan/Import/ImportResource,或者类中存在@Bean注解的方法也会任务是精简配置类)，
		 *  不然的话，即使是手动注册了类，也没有办法识别出是配置类
		 *  然后就会用这个类作为起点进行处理，在进行处理的时候会委派给对象 ConfigurationClassParser 进行处理，在进行处理的时候会将配置类的注解元信息
		 *  和beanName进行封装成我们的ConfigurationClass类，再封装成SourceClass，然后开始进行处理
		 *  首先处理标注了@Component注解,会进行这个类的成员类的处理
		 *  再处理任何@PropertySources和@PropertySource的注解 ，这里解析出来的资源会直接放入到环境变量中去
		 *  然后处理任何@ComponentScans 和 @ComponentScan的注解 ，这里解析出来的beanDefinition会直接进行注册到注册中心
		 *  再处理@Import的注解 ， 这里import 可以导入三类，导入的类的ConfigurationClass类中会存放对应的来自哪个类到importedBy变量中，后面我们通过这个进行处理
		 *  再处理@ImportResource的注解
		 *  再处理配置类中标注了@Bean的注解
		 *  再处理接口中的默认方法中的@Bean方法
		 *  判断是否存在父类，进行父类的再解析
		 *
		 *  解析完我们的配置类之后，会形成一个set类型的configurationClass的集合，然后再通过ConfigurationClassBeanDefinitionReader 进行读取
		 *  beanDefinition然后进行注册。 主要处理 import methodBean 和 importResource三类
		 *
		 *
		 *
		 *
		 * 重要类ConfigurationClass类中会存在一些变量，用来记录解析的信息，然后再解析完成之后，会进行处理成我们的BeanDefinition
		 *
		 *
		 */


	}

	class InnerClass{

		/**
		 * 在Spring框架中，解析配置类时获取当前配置类的memberClasses（成员类）是为了全面识别和处理配置类中的内部类。以下是具体原因：
		 * 内部类作为组件：配置类中的成员类（静态内部类或非静态内部类）可能被注解为组件（如@Component、@Configuration等），这些成员类同样需要被Spring容器管理。
		 * 增强配置灵活性：允许开发者将相关的bean定义逻辑封装在配置类的内部类中，使代码结构更加清晰，逻辑更加紧凑。
		 * 自动装配与代理：成员类可能参与自动装配或者需要创建代理对象，确保它们能正确地被Spring上下文识别和初始化。
		 * memberClasses是指配置类中定义的所有公共成员类（即内部类）。通过sourceClass.getMemberClasses()方法可以获取到这些成员类的集合，并进一步检查它们是否包含配置信息或组件注解，从而进行相应的注册和处理
		 */

	}
}
