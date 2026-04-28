package com.wf.xmg.a18annotation.a01componentScan;

import org.springframework.stereotype.Repository;

@Repository
public class UserDemo {

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
