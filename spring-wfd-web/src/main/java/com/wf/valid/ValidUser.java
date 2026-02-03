package com.wf.valid;

import javax.validation.constraints.NotEmpty;

public class ValidUser {


	@NotEmpty
	private String age;

	private String name;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
