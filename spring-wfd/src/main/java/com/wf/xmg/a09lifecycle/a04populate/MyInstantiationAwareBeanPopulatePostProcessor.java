package com.wf.xmg.a09lifecycle.a04populate;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;


public class MyInstantiationAwareBeanPopulatePostProcessor implements InstantiationAwareBeanPostProcessor {

	/**
	 * 5.1 新增的用于属性赋值的方法，
	 * 在这个方法里，可以改变我们的属性值
	 *
	 *
	 * PropertyValues接口 收集我们的属性值对象
	 *
	 */
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
				propertyValues.addPropertyValue("description", "description from postProcessProperties");
			}

			return propertyValues;
		}
		return null;
	}

	// 5.1之后已经废弃的改用上面的方法
	/*@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		return InstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
	}*/
}
