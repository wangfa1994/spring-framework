package com.wf.model.autowire.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/8/12 15:29
 */

public class Person {


	@Autowired
	private Cat cat;

	public Person() {
		System.out.println("Person的构造器...");
	}

	public Cat getCat() {
		return cat;
	}
}
