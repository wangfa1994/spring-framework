package com.wf.model.annBeanFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/8 15:39
 */

public class Person {

    private String name;



	@Autowired
    private Cat cat;

	public Person() {
		System.out.println("person的无参构造器......");
	}

	public Person(String name, Cat cat) {
		System.out.println("person的有参构造......");
		this.name = name;
		this.cat = cat;
	}

	public Cat getCat() {
        return cat;
    }


    public void setCat(Cat cat) {
		System.out.println("person 标记了autowired的set属性方法");
		this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", cat=" + cat +
				'}';
	}
}
