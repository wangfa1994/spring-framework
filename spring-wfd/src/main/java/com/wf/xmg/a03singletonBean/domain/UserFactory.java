package com.wf.xmg.a03singletonBean.domain;

public class UserFactory {

	public User createUser() {
		User user = new User(2L,"xmgMethod");
		return user;
	}
}
