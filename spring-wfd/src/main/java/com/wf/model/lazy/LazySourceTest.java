package com.wf.model.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;


@ComponentScan(basePackages = {"com.wf.model.lazy"})
public class LazySourceTest {

	@Autowired
	private Cat cat;

	@Autowired
	@Lazy // lazy的情况下会产生代理对象, Lazy的代理对象是在解决依赖的时候进行判断产生的 ，LookUp的代理对象是在getBean判断beanDefinition中的属性产生的
	private Cat catLazy;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(LazySourceTest.class);

		applicationContext.refresh();

		LazySourceTest lazySourceTest = applicationContext.getBean(LazySourceTest.class);
		System.out.println("cat: "+lazySourceTest.cat);
		System.out.println("catLazy: "+lazySourceTest.catLazy);
		// cat 和 catLazy 是同一个对象 ,打印的hash值相同,但是判断却不是同一个对象
		System.out.println("cat 和 catLazy 是同一个对象"+(lazySourceTest.cat == lazySourceTest.catLazy));
	}

}
