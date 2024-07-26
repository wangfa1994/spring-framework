package com.wf.xmg.a06injection;

public class UserHolder {

	private User user;



	public UserHolder() {
		System.out.println("进入UserHolder类的无参数构造方法");
	}


	public UserHolder(User user) {
		System.out.println("进入UserHolder类的user构造方法");
		this.user = user;
	}

	public void setUser(User user) {
		System.out.println("进入UserHolder类的setUser方法");
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}

	public User getUser() {
		return user;
	}
}
