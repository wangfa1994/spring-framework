package com.wf.xmg.a09lifecycle.a03instantiation;

import com.wf.xmg.a09lifecycle.User;

public class UserHolder {

	private  User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		System.out.println("进入了对应的UserHolder的setUser方法: "+user);
		this.user = user;
	}


	public UserHolder() {
		System.out.println("进入了对应的UserHolder的无参构造器方法");
	}

	public UserHolder(User user) {
		System.out.println("进入了对应的UserHolder的有参构造器方法");
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
