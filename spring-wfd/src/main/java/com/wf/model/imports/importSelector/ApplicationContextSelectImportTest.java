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
	}
}
