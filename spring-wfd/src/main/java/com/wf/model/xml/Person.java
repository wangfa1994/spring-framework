package com.wf.model.xml;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/8 15:39
 */
public class Person {

    private String name;


    private Cat cat;


    public Cat getCat() {
        return cat;
    }


    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Person() {
		System.out.println("person的无参构造器");

	}

	/*@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", cat=" + cat +
				"}========》"+cat.hashCode()+"=======》"+this.hashCode();
	}*/
}
