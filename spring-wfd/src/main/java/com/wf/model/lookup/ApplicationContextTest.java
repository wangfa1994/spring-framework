package com.wf.model.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextTest {

	/**
	 *在Spring的诸多应用场景中bean都是单例形式，
	 * 当一个单利bean需要和一个非单利bean组合使用或者一个非单利bean和另一个非单利bean组合使用时，我们通常都是将依赖以属性的方式放到bean中来引用，
	 * 然后以@Autowired来标记需要注入的属性。但是这种方式在bean的生命周期不同时将会出现很明显的问题，
	 * 假设单利bean A需要一个非单利bean B（原型），我们在A中注入bean B，每次调用bean A中的方法时都会用到bean B，
	 * 我们知道Spring Ioc容器只在容器初始化时执行一次，也就是bean A中的依赖bean B只有一次注入的机会，
	 * 但是实际上bean B我们需要的是每次调用方法时都获取一个新的对象（原型）所以问题明显就是：
	 * 我们需要bean B是一个原型bean，而事实上bean B的依赖只注入了一次变成了事实上的单利bean
	**/

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(LookUpConfig.class);

		// 标记了LookUp,为什么会产生代理对象呢？
		Person person = (Person) annotationConfigApplicationContext.getBean("person");

		Cat autowireCat1 = person.getCat();
		System.out.println("autowireCat1: "+autowireCat1);
		Cat autowireCat2 = person.getCat();
		System.out.println("autowireCat2: "+autowireCat2);
		System.out.println("autowireCat1 == autowireCat2: "+(autowireCat1 == autowireCat2));


		Cat lookUpCat1 = person.lookUpCat();
		System.out.println("lookupCat1: "+lookUpCat1);
		Cat lookUpCat2 = person.lookUpCat();
		System.out.println("lookupCat2: "+lookUpCat2);

		System.out.println("lookupCat1 == lookupCat2: "+(lookUpCat1 == lookUpCat2));

		System.out.println("autowireCat1 == lookupCat1: "+(autowireCat1 == lookUpCat1));







	}

}
