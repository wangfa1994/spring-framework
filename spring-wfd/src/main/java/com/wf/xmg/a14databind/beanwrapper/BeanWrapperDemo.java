package com.wf.xmg.a14databind.beanwrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

public class BeanWrapperDemo {

	public static void main(String[] args) {
		BeanWrapper company = new BeanWrapperImpl(new Company());

		company.setPropertyValue("name", "Some Company Inc.");

		// 在XML 中，会将我们的对象保证成BeanWrapper，然后通过XML中配置的PropertyValues进行设置值。
		PropertyValue value = new PropertyValue("name", "Some Company Inc.");
		company.setPropertyValue(value);

		BeanWrapper jim = new BeanWrapperImpl(new Employee());
		jim.setPropertyValue("name", "Jim Stravinsky");
		company.setPropertyValue("managingDirector", jim.getWrappedInstance());

		Float salary = (Float) company.getPropertyValue("managingDirector.salary");
		System.out.println("===="+salary);
		Company wrappedInstance = (Company)company.getWrappedInstance();
		System.out.println("====="+wrappedInstance);
	}
}
