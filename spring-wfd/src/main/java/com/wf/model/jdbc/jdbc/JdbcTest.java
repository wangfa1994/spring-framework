package com.wf.model.jdbc.jdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.Enumeration;
import java.util.List;

public class JdbcTest {

	public static void main(String[] args) throws Exception {

		oldJdbc();


	}

	private static void springJdbc() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JdbcConfiguration.class);


		JdbcTemplate jdbcTemplate = (JdbcTemplate)annotationConfigApplicationContext.getBean("jdbcTemplate");

		List<Stu> stu = jdbcTemplate.query("select * from stu", new BeanPropertyRowMapper<>(Stu.class));

		System.out.println(stu);

	}

	private static void oldJdbc() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springtran?useSSL=false", "root", "root");

		boolean autoCommit = connection.getAutoCommit();
		System.out.println(autoCommit);
		connection.setAutoCommit(false);

		PreparedStatement preparedStatement = connection.prepareStatement("insert into stu (id,stuname,address) values (?,?,?)");
		preparedStatement.setInt(1, 4);
		preparedStatement.setString(2, "name");
		preparedStatement.setString(3, "address");

		int i = preparedStatement.executeUpdate();
		System.out.println("i:"+i);
		//connection.commit();

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		Driver driver = drivers.nextElement();

		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println(metaData);

	}
}
