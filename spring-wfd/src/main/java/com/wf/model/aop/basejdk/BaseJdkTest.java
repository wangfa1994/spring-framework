package com.wf.model.aop.basejdk;

import java.lang.reflect.Proxy;

public class BaseJdkTest {

	public static void main(String[] args) {


		//JDK 动态代理
		StudentServiceImpl studentService1 = new StudentServiceImpl();

		System.out.println("原生调用"+studentService1.getStudentInfo());

		System.out.println("开始进行了代理");

		StudentService studentService = (StudentService)Proxy.newProxyInstance(BaseJdkTest.class.getClassLoader(), new Class<?>[]{StudentService.class}, new LoggerAspect(studentService1));


		System.out.println("被代理之后调用"+studentService.getStudentInfo());


	}
}
