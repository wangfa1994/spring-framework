package com.wf.xmg.a03singletonBean.domain;

import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

	@Override
	public User getObject() throws Exception {
		User user = new User(3L,"xmgFactoryBean");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
