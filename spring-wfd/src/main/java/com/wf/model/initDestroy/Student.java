package com.wf.model.initDestroy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Student {
	private String name="zhangSan";


	public Student() {
		System.out.println("student的构造器...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				'}';
	}

	@PostConstruct
	public void init() {
        System.out.println("student的init啦");
    }

	@PreDestroy
    public void destroy() {
        System.out.println("student的destroy啦");
    }



}
