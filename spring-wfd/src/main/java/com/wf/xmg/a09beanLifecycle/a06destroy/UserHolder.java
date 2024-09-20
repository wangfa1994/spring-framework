package com.wf.xmg.a09beanLifecycle.a06destroy;

import com.wf.xmg.a09beanLifecycle.User;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class UserHolder implements DisposableBean{

	private User user;

	@Override
	public void destroy() throws Exception {
		System.out.println("进入UserHolder的destory()方法");

	}


	public void myDestroy() {
		System.out.println("进入UserHolder的自定义销毁方法");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("进入USerHolder的@PreDestroy方法");
	}






	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
