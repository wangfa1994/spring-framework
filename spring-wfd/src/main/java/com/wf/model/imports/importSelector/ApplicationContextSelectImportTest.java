package com.wf.model.imports.importSelector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import(MyImportSelector.class)
public class ApplicationContextSelectImportTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextSelectImportTest.class);
		// 通过Import进行导入的，beanName都是全路径类名
		SelectImportsBean1 bean = annotationConfigApplicationContext.getBean("com.wf.model.imports.importSelector.SelectImportsBean1",SelectImportsBean1.class);
		System.out.println(bean);

		SelectImportsBean2 bean2 = annotationConfigApplicationContext.getBean("com.wf.model.imports.importSelector.SelectImportsBean2",SelectImportsBean2.class);
		System.out.println(bean2);

		/**
		 * ConfigurationClassPostProcessor 的工厂后置处理器委派为 ConfigurationClassParser进行解析处理，
		 * 如果import 导入的 是 ImportSelector类的话，则会先进行此对象的实例化，这个对象是不会被spring所管理的，它仅仅在这里进行了创建，
		 * 然后回调对应的方法解析出我们所配置的一系列类的全路径名称，解析出来的全路径类名会被封装成新的配置类，
		 * 然后会被当做配置类再进行处理，处理完成之后，最后会将这些配置类变成我们的BeanDefinition，此时的beanName，就是我们配置的全路径类名。
		 *
		 *
		 */

	}
}
