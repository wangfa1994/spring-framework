package com.wf.model.jdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTest {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JdbcConfiguration.class);


		JdbcTemplate jdbcTemplate = (JdbcTemplate)annotationConfigApplicationContext.getBean("jdbcTemplate");

		List<Stu> stu = jdbcTemplate.query("select * from stu", new BeanPropertyRowMapper<>(Stu.class));

		System.out.println(stu);





	}
}