package com.wf.model.autowire.more;

import com.wf.model.autowire.one.AutoWireOneConfigurations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc : @Desc : @Autowire注解配置的分析，多Cat实现
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationAutowireMoreTest {

    public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoWireMoreConfigurations.class);
		Person person = (Person) annotationConfigApplicationContext.getBean("person");
		System.out.println(person);
		Cat cat1 = person.getCat();
		System.out.println("person的cat"+cat1);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("blackCat");
		System.out.println("容器中的cat"+cat);






    }

/**
 *
 * 底层的后置处理器
 * 通过RootBeanDefinition注册 org.springframework.context.annotation.internalConfigurationAnnotationProcessor---->ConfigurationClassPostProcessor
 *通过RootBeanDefinition注册 org.springframework.context.annotation.internalAutowiredAnnotationProcessor---->AutowiredAnnotationBeanPostProcessor
 *
 *按需通过RootBeanDefinition注册  org.springframework.context.annotation.internalCommonAnnotationProcessor---->CommonAnnotationBeanPostProcessor ---支持JSR250
 *
 *
 * AnnotatedGenericBeanDefinition 配置类的bean
 *
 */



}
