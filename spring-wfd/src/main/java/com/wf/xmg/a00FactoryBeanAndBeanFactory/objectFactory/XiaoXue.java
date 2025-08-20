package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory;

public class XiaoXue implements School {
	private String name="xiaoxue";

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
