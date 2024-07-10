package com.wf.model.imports;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextImportTest {

    public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ImportConfiguration.class);


		Object selectImportsBean2 = annotationConfigApplicationContext.getBean("selectImportsBean2");
		System.out.println(selectImportsBean2);


		ImportBean configurationsBean = (ImportBean)annotationConfigApplicationContext.getBean("com.wf.model.imports.ImportBean");
		//String cb = configurationsBean.getCb();
		System.out.println("=============="+configurationsBean);


	}

/**
 * @Import
 * 通过Import可以导入三种类型的类，一种是实现了 ImportSelector，一种是实现了 ImportBeanDefinitionRegistrar，最后就是会当成普通的配置类进行解析再处理
 * 导入ImportSelector的可以进行多个类的导入，
 * 导入ImportBeanDefinitionRegistrar的表示进行自定义导入beanDefinition
 *
 * ImportSelector
 *
 *
 * ConfigurationClassPostProcessor
 *
 * ImportBeanDefinitionRegistrar
 *
 *
 */



}
