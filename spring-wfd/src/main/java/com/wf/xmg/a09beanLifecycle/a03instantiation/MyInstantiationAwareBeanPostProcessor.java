package com.wf.xmg.a09beanLifecycle.a03instantiation;

import com.wf.xmg.a09beanLifecycle.SuperUser;
import com.wf.xmg.a09beanLifecycle.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;


public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {


	/**
	* Bean 实例化之前 进行拦截对应的方法，然后确定是否返回自定义的对象，如果返回了自定义的对象，则不会再再进行相关bean生命周期的调用
	**/
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("进入自定义实例化处理器的前置方法postProcessBeforeInstantiation:"+beanName);

		if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
			// 把配置完成 superUser Bean 覆盖
			return new SuperUser();
		}
		return null; // 保持 Spring IoC 容器的实例化操作
	}


	/**
	 * Bean实例化之后，赋值之前  进行操作的方法，
	 *
	 *  在bean通过构造函数或工厂方法实例化之后 ，但在Spring属性填充(来自显式属性或自动装配)发生之前执行操作。
	 *  这是在Spring自动装配开始之前对给定bean实例执行自定义字段注入的理想回调
	 *
	 *  返回了false 表示不会再进行后续的属性填充，这里可以进行自定义 的属性处理，而且返回false后，后续的InstantiationAwareBeanPostProcessor也不会再进行执行了呢
	 *
	 *  一般情况下都会返回true，表示还会后续的逻辑处理，进行相关属性值的赋值
	 *
	 **/
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
			User user = (User) bean;
			user.setAge(16);
			user.setName("mercyblitz");
			// "user" 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
			return false;
		}
		return true;
	}

	// user 是跳过 Bean 属性赋值（填入）
	// superUser 也是完全跳过 Bean 实例化（Bean 属性赋值（填入））
	// userHolder

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		// 对 "userHolder" Bean 进行拦截
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			// 假设 <property name="number" value="1" /> 配置的话，那么在 PropertyValues 就包含一个 PropertyValue(number=1)

			final MutablePropertyValues propertyValues;

			if (pvs instanceof MutablePropertyValues) {
				propertyValues = (MutablePropertyValues) pvs;
			} else {
				propertyValues = new MutablePropertyValues();
			}

			// 等价于 <property name="number" value="1" />
			propertyValues.addPropertyValue("number", "1");
			// 原始配置 <property name="description" value="The user holder" />

			// 如果存在 "description" 属性配置的话
			if (propertyValues.contains("description")) {
				// PropertyValue value 是不可变的
//                    PropertyValue propertyValue = propertyValues.getPropertyValue("description");
				propertyValues.removePropertyValue("description");
				propertyValues.addPropertyValue("description", "The user holder V2");
			}

			return propertyValues;
		}
		return null;
	}

}
