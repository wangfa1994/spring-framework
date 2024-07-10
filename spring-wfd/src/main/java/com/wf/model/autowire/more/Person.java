package com.wf.model.autowire.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/8/12 15:29
 */
@Component
public class Person {


	@Qualifier("whiteCat")
	@Autowired
	private Cat cats;

	public Person() {
		System.out.println("Person的构造器...");
	}

	public Cat getCat() {
		return cats;
	}
}
