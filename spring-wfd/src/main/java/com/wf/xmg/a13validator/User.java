package com.wf.xmg.a13validator;

import com.wf.xmg.a13validator.spring.Group1;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class User {
	@NotNull(message = "name不能为空", groups = {Group1.class})
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User{" +
			   "name='" + name + '\'' +
			   '}';
	}
}
