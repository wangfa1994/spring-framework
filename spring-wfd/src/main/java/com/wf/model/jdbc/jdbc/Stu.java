package com.wf.model.jdbc.jdbc;

import java.io.Serializable;


public class Stu implements Serializable {

	private static final long serialVersionUID = 1625795451950451599L;
	private int id;

	private String stuname;

	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Stu{" +
				"id=" + id +
				", stuname='" + stuname + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
