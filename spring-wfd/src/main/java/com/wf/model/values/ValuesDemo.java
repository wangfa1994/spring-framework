package com.wf.model.values;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

// @Value的使用
@PropertySource("model/spel.properties")
public class ValuesDemo {

	@Value("${student.category}") //$ 加载外部资源
	private String category;

	@Value("#{3+4}") // # 用于spel计算
	private int totalNumber;

	@Value("#{student.name}") // # 用于spel计算 可以从对应的结果中取出数值
	private String studentName;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ValuesDemo.class);
		ValuesDemo valuesDemo = context.getBean("valuesDemo", ValuesDemo.class);
		System.out.println(valuesDemo);

	}

	@Bean
	public Student student(){
		Student student = new Student();
		student.setAge("12");
		student.setName("zhangsan");
		return student;
	}

	@Override
	public String toString() {
		return "ValuesDemo{" +
				"category='" + category + '\'' +
				", totalNumber=" + totalNumber +
				", studentName=" + studentName +
				'}';
	}
}
