package com.wf.postprocess.bean.instantiationAwareBeanPostProcessor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class NormalCat {
	private String name;
	private Integer age;

	public NormalCat() {
		System.out.println("NormalCat 构造器。。。");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "NormalCat{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
