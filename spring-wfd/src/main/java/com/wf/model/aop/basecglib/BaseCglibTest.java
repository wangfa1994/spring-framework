package com.wf.model.aop.basecglib;

import org.springframework.cglib.proxy.Enhancer;

public class BaseCglibTest {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(StudentServiceImpl.class);
		enhancer.setCallback(new LoggerInterceptor());

		StudentServiceImpl studentService = (StudentServiceImpl)enhancer.create();
		System.out.println(studentService.getStudentInfo());
	}
}
