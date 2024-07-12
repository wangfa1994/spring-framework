package com.wf.xmg.a03singletonBean.domain;

public class User {

	private Long id;

	private String name;


	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static User createUser() {
		User user = new User(1L,"xmgStatic");
		user.setId(1L);
		user.setName("小马哥");
		return user;
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
