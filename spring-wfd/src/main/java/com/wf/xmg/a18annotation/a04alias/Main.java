package com.wf.xmg.a18annotation.a04alias;


// 208 属性别名

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@MyComponentScan(scanBasePackages = "com.wf.xmg.a18annotation.a04alias")
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(Main.class);

		User bean = context.getBean(User.class);
		System.out.println("====="+bean);
	}

	/**
	 * 属性别名是指从一个注解属性指向另一个注解属性的别名。同一别名集合中的属性可以互换使用，并被视为等效。属性别名可以按以下方式分类
	 * 1. 显式别名：如果一个注解中的两个属性通过 相互声明为别名@AliasFor，则它们是显式别名。
	 * 2. 隐式别名：如果一个注解中的两个或多个属性通过元注解中的显式覆盖声明为同一属性@AliasFor，则它们是隐式别名。
	 * 3. 传递隐式别名：给定一个注解中的两个或多个属性，这些属性通过声明为元注解中属性的显式覆盖@AliasFor，如果这些属性按照传递性定律有效地覆盖了元注解中的同一属性 ，则它们是传递隐式别名
	 *
	 *  ComponentScan 中的显式别名
	 *
	 *  SpringBootApplication 中的隐式别名 和  传递隐式别名
	 *
	 *
	 *
	 */
	 }
