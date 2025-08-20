package com.wf.model.resolveDependency;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DefaultListableBeanFactory 进行 解决依赖
 *
 * 解决依赖的方法属于 AutowireCapableBeanFactory 规范中的
 *
 */


public class BeanFactoryResolveDependencyDemo {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {


		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		//先注册Teacher实例
		Teacher teacher = new Teacher(); teacher.setName("teacher");
		beanFactory.registerSingleton("teacher",teacher);

		// 注册没有进行属性赋值的学生类
		Student student = new Student();
		beanFactory.registerSingleton("student",student);

		// 得到学生类的字段，teacher，然后进行依赖注入
		Field field  = student.getClass().getDeclaredField("teacher");
		DependencyDescriptor desc = new DependencyDescriptor(field, true);
		Set<String> autowiredBeanNames = new LinkedHashSet<>(2);
		TypeConverter typeConverter = beanFactory.getTypeConverter();

		//得到工厂中的teacher对象
		Object value = beanFactory.resolveDependency(desc, "student", autowiredBeanNames, typeConverter);

		if (value != null) { // 当获取到依赖注入的值不为空的话，进行反射注入到对象中去
			Object bean = beanFactory.getBean("student");
			ReflectionUtils.makeAccessible(field);
			field.set(bean, value);
		}

		System.out.println(student);

	}
}
