package com.wf.model.autowire.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class APerson {
	@Autowired
	private Cat cat;

	public APerson() {
		System.out.println("APerson的构造器...");
	}

	public Cat getCat() {
		return cat;
	}
}
