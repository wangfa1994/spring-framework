package com.wf.model.createBean;

import org.springframework.stereotype.Component;

@Component
public class Cat {
	private String name;

	private int age;

	public Cat() {
		System.out.println("cat 构造器");
	}
}
