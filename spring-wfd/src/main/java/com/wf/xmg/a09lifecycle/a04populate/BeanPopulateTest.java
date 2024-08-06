package com.wf.xmg.a09lifecycle.a04populate;

import com.wf.xmg.a09lifecycle.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

// 实例化之后，开始进入填充属性阶段  填充属性前 填充属性中
public class BeanPopulateTest {

	/**
	 *  实例化之后，开始进入填充属性阶段  填充属性前 填充属性中
	 *
	 *  实例化之后，属性赋值之前，会先利用了InstantiationAwareBeanPostProcessor#postProcessProperties进行相关属性值的修改和处理，
	 *  然后返回我们的PropertyValues，再进行相关属性的填充。
	 *
	 *  在xml 中 如果依赖的对象是引用对象，则会被包装成RuntimeBeanReference。然后通过解决依赖进行getBean对应的依赖
	 *
	 *
	 * BeanDefinitionValueResolver
	 * BeanReference
	**/

	public static void main(String[] args) {

		// 实例化前只要使用处理器， InstantiationAwareBeanPostProcessor ,注意我们这里使用的是 容器而不是上下文，我们需要自己注册处理器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 将我们自定义的处理器注册到容器中，再上下文中，spring会默认一些处理器给我们，用户扩展容器的功能，所以，上下文的功能比底层容器的功能要丰富一些
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPopulatePostProcessor());

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/bean-populate-09.xml";
		reader.loadBeanDefinitions(location);

		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		User user = beanFactory.getBean("user", User.class);
		System.out.println("user: "+user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println("superUser: "+superUser);

		// 构造器注入按照类型注入，resolveDependency UserHolder(User user) 到底是注入user还是指定了primary的superUser
		// 在构造器依赖注入的时候，会通过一系列的解析判断，创建出最后的构造器依赖描述符，然后再进入我们的DefaultListAbleBeanFactory中的resolveDependency方法中,通过类型进行处理依赖
		// 如果使用了构造器注入，真实的逻辑是在实例化的时候进行处理解析的
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println("userHolder: "+userHolder);

		// 按照名称注入，找到的是相关的对应的名称的对象，注入的是user 用的都是setter注入 自动注入是在填充属性阶段进行处理的
		UserHolder userHolderByName = beanFactory.getBean("userHolderByName", UserHolder.class);
		System.out.println("userHolderByName: "+userHolderByName);
		// 按照名称注入，找到的是相关的对应的类型，注入的是SuperUser 用的都是setter注入 自动注入是在填充属性阶段进行处理的
		UserHolder userHolderByType = beanFactory.getBean("userHolderByType", UserHolder.class);
		System.out.println("userHolderByType: "+userHolderByType);
	}
}
