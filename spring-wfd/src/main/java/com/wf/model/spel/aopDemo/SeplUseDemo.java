package com.wf.model.spel.aopDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

// 基于AOP 拦截 获取方法中指定参数，然后进行业务逻辑处理
// 比如可以进行权限校验，这个方法是否可以被执行
@ComponentScan("com.wf.model.spel.aopDemo")
@EnableAspectJAutoProxy
public class SeplUseDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SeplUseDemo.class);
		SeplUseDemo seplUseDemo = context.getBean("seplUseDemo", SeplUseDemo.class);
		Student student1 = new Student(); student1.setId(1);
		Student student2 = new Student(); student2.setId(2);


		String s1 = seplUseDemo.checkStudent(student1);
		System.out.println("check userId1"+s1);
		String s2 = seplUseDemo.checkStudent(student2);
		System.out.println("check userId2"+s2);
	}



	@StudentCheck(studentId="#student.id")
	public String checkStudent(Student student){

		System.out.println("进入checkStudent方法执行");

		return "success";
	}

}
