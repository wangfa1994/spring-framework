package com.wf.model.jdbc.jdbc;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;


public class JdbcConfiguration {

	@Bean
	public DriverManagerDataSource driverManagerDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/springtran?useSSL=false");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}

	// 内置了jdbcTemplate,但是如果要使用jpa，还需要单独引入spring-data-jpa相关的jar包
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(driverManagerDataSource());
		return jdbcTemplate;
	}

	@Bean
	private PlatformTransactionManager platformTransactionManager(){
		DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
		platformTransactionManager.setDataSource(driverManagerDataSource());
		return platformTransactionManager;
	}
}
