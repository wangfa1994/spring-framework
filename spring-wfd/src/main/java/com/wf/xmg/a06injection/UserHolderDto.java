package com.wf.xmg.a06injection;

public class UserHolderDto {

	private User user;

	public User getUser1() {
		return user;
	}

	/*public void setUser1(User user) {
		System.out.println("进入可写的user方法中");
		this.user = user;
	}*/

	public void setUser(User user) {
		System.out.println("进入可写的user方法中");
		this.user = user;
	}

}
