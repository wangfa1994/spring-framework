package com.wf.model.fullandlite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/8 15:39
 */
public class Person {



    private Cat cat;

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public Person(Cat cat) {
		this.cat = cat;
	}
}
