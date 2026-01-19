package com.wf.model.xmlann;

import com.wf.model.xmlann.ann.DogAnn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextTest {

	public static void main(String[] args) {


		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanXmlAnn.xml");
		Cat cat = applicationContext.getBean("cat", Cat.class);
		System.out.println(cat);
		Person person = applicationContext.getBean("person", Person.class);
		System.out.println(person.getCat());

		/*AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(XmlAnnConfig.class);
		com.wf.model.ann.Cat person = (Cat) annotationConfigApplicationContext.getBean("cat");

		System.out.println(person);*/

	}

}
