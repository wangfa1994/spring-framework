package com.wf.postprocess.bean.instantiationAwareBeanPostProcessor;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InstantiationAwareBeanPostProcessorTest {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(InstantiationAwareConfiguration.class);

		// 通过instantiationAwareBeanPostProcessor返回去的cat,此时返回之后，之后执行instantiationAwareBeanPostProcessor的BeforeInstantiation和BeanPostProcessor的AfterBeanInit
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat);

		Cat cat1 = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat1);

		System.out.println(cat == cat1);
		// normalCat 正常流程的猫
		NormalCat normalCat = (NormalCat) annotationConfigApplicationContext.getBean("normalCat");
		System.out.println(normalCat);

	}
}
