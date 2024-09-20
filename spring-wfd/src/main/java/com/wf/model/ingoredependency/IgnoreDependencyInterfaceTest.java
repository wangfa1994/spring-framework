package com.wf.model.ingoredependency;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IgnoreDependencyInterfaceTest {


	/** 忽略相关接口的原理
 	 * beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
	 *
	 * 最底层是在这里进行使用 org.springframework.beans.factory.support.AutowireUtils#isSetterDefinedInInterface(java.beans.PropertyDescriptor, java.util.Set)
	 * 但是上层在设置属性的autowireByType 和  autowireByName   以及 filterPropertyDescriptorsForDependencyCheck 使用，
	 *
	 * constructor 是利用构造器进行设置的
	 *
	 *
	 * xml配置并且是在自动装配的模式下，才会触发相关逻辑，遗留下来的产物，
	 *
	 * spring会帮我们忽略掉一些内置的bean的注入，因为这些我们可以通过实现相关的Aware接口进行注入，而不是依赖注入
	 * 这种方法而且是在特定的场景下才能使用，暂时没有发现
	 *
	 * 现在好像通过注解都可以进行注入了，
	 * @param args
	 */

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("model/ignore-dependency.xml");

		Teacher teacherByName = context.getBean("teacherByName", Teacher.class);
		Teacher teacherByType = context.getBean("teacherByType", Teacher.class);
		Teacher teacherByConstructor = context.getBean("teacherByConstructor", Teacher.class);

		System.out.println("teacherByName: "+teacherByName);
		System.out.println("teacherByType: "+teacherByType);
		System.out.println("teacherByConstructor: "+teacherByType);

		System.out.println("============================================================");

		TeacherSingle teacherSingleByName = context.getBean("teacherSingleByName", TeacherSingle.class);
		System.out.println("teacherSingleByName: "+teacherSingleByName);
		TeacherSingle teacherSingleByType = context.getBean("teacherSingleByType", TeacherSingle.class);
		System.out.println("teacherSingleByType: "+teacherSingleByType);
		TeacherSingle teacherSingleByConstructor = context.getBean("teacherSingleByConstructor", TeacherSingle.class);



		System.out.println("teacherSingleByConstructor: "+teacherSingleByConstructor);
	}
}
