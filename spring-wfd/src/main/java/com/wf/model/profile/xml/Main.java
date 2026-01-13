package com.wf.model.profile.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {




	public static void main(String[] args) {
		System.setProperty("env","prod");
		//System.setProperty("env","dev");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("model/profile/context.xml");

		String name = (String)context.getBean("name");

		System.out.println(name);

		context.getEnvironment().addActiveProfile("dev");

		context.refresh();

		 name = (String)context.getBean("name");

		System.out.println(name);

	}

	/**
	 *  通过桥接XML上下文配置文件的方式实现，spring上下文需要天环占位符env，这个值可以来自 外部化配置，java系统属性或者操作系统环境变量
	 *
	 *  java系统属性或者操作系统环境变量 作为spring外部化配置，贯穿整个spring Frame 和 springboot时代
	 *  上面是通过 java系统属性配置
	 *  也可以通过ConfigurableEnvironment API 编码配置
	 *
	 */
}
