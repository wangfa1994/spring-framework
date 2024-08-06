package com.wf.xmg.a09lifecycle;

public class SuperUser extends User{

	private String mobile;


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "SuperUser{" +
				"mobile='" + mobile + '\'' +
				"} " + super.toString();
	}
}
