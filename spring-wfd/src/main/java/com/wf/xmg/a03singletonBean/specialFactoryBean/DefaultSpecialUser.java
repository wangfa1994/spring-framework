package com.wf.xmg.a03singletonBean.specialFactoryBean;

public class DefaultSpecialUser implements ISpecialUser{
	@Override
	public SpecialUser getSpecialUser() {
		System.out.println("进入默认实现创建user");
		SpecialUser specialUser = new SpecialUser();
		specialUser.setAge(18);
		specialUser.setName("xmg");
		return specialUser;
	}
}
