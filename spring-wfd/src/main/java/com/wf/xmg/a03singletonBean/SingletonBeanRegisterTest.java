package com.wf.xmg.a03singletonBean;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

// 01 我们的bean通过各种方式进行实例化之后，怎么注册到beanFactory容器中
public class SingletonBeanRegisterTest {

	/**
	 *
	 * SingletonBeanRegistry 体系(还有一个是beanDefinitionRegistry体系)
	 *  我们可以直接将我们的bean实例注册到对应的容器中，而不经过BeanDefinition，
	 *  DefaultSingletonBeanRegistry实现类 查看结构图，能发现最后的DefaultListableBeanFactory也是bean注册器
	 *
	 *  ConfigurableBeanFactory接口继承了SingletonBeanRegistry，表明他也具备注册实例bean的功能，而且他的子类也会具有相同功能
	 *
	 *
	 */
	public static void main(String[] args) {
		SingletonBeanRegistry beanFactory = new DefaultListableBeanFactory();
		// 通过代码直接注册实例
		StudentRegister student = new StudentRegister();
		student.setAge(12);student.setName("zhangsan");
		beanFactory.registerSingleton("student",student);

		// 注入之后进行获取 注意此时是通过getSingleton获取的
		Object student1 = beanFactory.getSingleton("student");
		System.out.println(student+":"+(student==student1));
		//beanFactory.registerBeanDefinition();

	}
}
