package com.wf.xmg.a14databind;

import com.wf.xmg.a14databind.beanwrapper.Company;

import java.beans.*;
import java.util.Properties;
import java.util.stream.Stream;

public class JavaBeanInfo {


	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

		/*	Stream.of(propertyDescriptors).forEach(item->{
			//item.getReadMethod();
			System.out.println(item);

		});*/

		MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
		Stream.of(methodDescriptors).forEach(item->{
			System.out.println(item);

		});

	}


	public static class User{
		private Long id;

		private String name;

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
	}
}
