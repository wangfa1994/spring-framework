package com.wf.xmg.a09lifecycle.a03instantiation;


import com.wf.xmg.a09lifecycle.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

// bean 实例化周期测试
// 实例化前 实例化中  实例化后
public class BeanInstantiationTest {

	/**
	 * 实例化之前 的操作是通过 InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation进行触发的。
	 * 这里我们可以返回我们的自定义拦截对象，如果返回了对象，我们的bean的创建就脱离了spring帮我们创建的流程。
	 * 在一些代理对象，远程调用的时候需要替换对应的bean实例的时候，会使用此方法，但是比较少见此种方式进行替换
	 *
	 * 在实例化中 的时候会通过我们的SmartInstantiationAwareBeanPostProcessor 进行相关构造器的选用，然后进行相关对象的实例化
	 */

	public static void main(String[] args) {
		// 实例化前只要使用处理器， InstantiationAwareBeanPostProcessor ,注意我们这里使用的是 容器而不是上下文，我们需要自己注册处理器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 将我们自定义的处理器注册到容器中，再上下文中，spring会默认一些处理器给我们，用户扩展容器的功能，所以，上下文的功能比底层容器的功能要丰富一些
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/bean-instantiation-09.xml";
		reader.loadBeanDefinitions(location);

		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		User user = beanFactory.getBean("user", User.class);
		System.out.println("user: "+user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println("superUser: "+superUser);

		// 构造器注入按照类型注入，resolveDependency UserHolder(User user) 到底是注入user还是指定了primary的superUser
		// 在构造器依赖注入的时候，会通过一系列的解析判断，创建出最后的构造器依赖描述符，然后再进入我们的DefaultListAbleBeanFactory中的resolveDependency方法中,通过类型进行处理依赖
		// 如果使用了构造器注入，真实的逻辑是在实例化的时候进行处理解析的，但是如果是依照名称或者类型注入则会是在那个阶段进行处理的呢？
		UserHolder userHolder = beanFactory.getBean("userHolderByConstructor", UserHolder.class);
		System.out.println("userHolder: "+userHolder);


	}


}
