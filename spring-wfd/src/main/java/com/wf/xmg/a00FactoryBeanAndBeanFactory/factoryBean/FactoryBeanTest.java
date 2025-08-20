package com.wf.xmg.a00FactoryBeanAndBeanFactory.factoryBean;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class FactoryBeanTest {

	public static void main(String[] args) {
		/**
		 * FactoryBean是一个bean,从这个bean中，我们可以得到其他不被spring管理的对象，但是FactoryBean需要被spring管理
		 * 这个FactoryBean 需要被BeanFactory进行管理
		 *
		 * 我们可以通过被管理的FactoryBean得到其中返回的对象，因为在创建spring创建对象之后，针对创建的实例FactoryBean做了特殊逻辑处理
		 * 会根据我们传递的bean名称判断我们是想要获取FactoryBean,还是要获取FactoryBean中的对象
		 * 在AbstractBeanFactory#doGetBean()中得到bean实例之后会进行getObjectForBeanInstance方法中得到我们真正想要的bean
		 *
		 *
		 * 会判断是否是合成对象，然后只会参与一个生命周期，执行一个后置处理器 AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization()
		 *
		 * FactoryBean中管理的对象会被缓存到 FactoryBeanRegistrySupport.factoryBeanObjectCache中
		 *
		 * 可以进行依赖注入，主要是因为 DefaultListableBeanFactory#resolveDependency()进行处理的时候，会进行BeanFactory逻辑的处理
		 *
		 *
		 *
		 *
		 */

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		//  ClassPathBeanDefinitionScanner 定义一个类路径下的beanDefinition扫描器，将扫描的BeanDefinition，进行注册到BeanDefinitionRegistry
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory); // 注册的是BeanDefinition,有了beanDefinition，才能产生实例bean
		scanner.setIncludeAnnotationConfig(false);
		scanner.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory.factoryBean"); // 扫描此包下面的


		// 注意这里获取的name 为什么是teacherFactoryBean ,而且我们的Teacher进行了实现Aware接口进行注入对应的beanName用于查看是否存在bean名称,aware接口的逻辑真的会走进去吗？
		Teacher teacher = beanFactory.getBean("teacherFactoryBean",Teacher.class);
		System.out.println("teacher: "+teacher);

		Teacher teacher2 = beanFactory.getBean(Teacher.class);
		System.out.println("teacher2: "+teacher2);
		System.out.println("=="+(teacher == teacher2));


		/*获得FactoryBean 缺只能通过 &teacherFactoryBean 进行得到了 */
		TeacherFactoryBean teacherFactoryBean = beanFactory.getBean("&teacherFactoryBean",TeacherFactoryBean.class);
		System.out.println("teacherFactoryBean: "+teacherFactoryBean);
		TeacherFactoryBean teacherFactoryBean2 = beanFactory.getBean(TeacherFactoryBean.class);
		System.out.println("teacherFactoryBean2: "+teacherFactoryBean2);
		System.out.println("=="+(teacherFactoryBean == teacherFactoryBean2));

		System.out.println("新工厂具备Autowire功能===================");

		DefaultListableBeanFactory autowireBeanFactory = new DefaultListableBeanFactory();
		ClassPathBeanDefinitionScanner scannerAutoWire = new ClassPathBeanDefinitionScanner(autowireBeanFactory); // 注册的是BeanDefinition,有了beanDefinition，才能产生实例bean
		scannerAutoWire.setIncludeAnnotationConfig(true);
		scannerAutoWire.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory.factoryBean");
		autowireBeanFactory.addBeanPostProcessor(autowireBeanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class));

		AutoContainDto autoContainDto = autowireBeanFactory.getBean("autoContainDto",AutoContainDto.class);
		System.out.println("可以依赖注入teacher吗？"+autoContainDto.getTeacher());
		System.out.println("可以依赖注入teacherFactoryBean吗？"+autoContainDto.getTeacherFactoryBean());

	}
}
