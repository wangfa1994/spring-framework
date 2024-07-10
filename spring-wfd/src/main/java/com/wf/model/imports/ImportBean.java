package com.wf.model.imports;

import org.springframework.context.annotation.Bean;

/**
 * @Desc : importBean也会被容器管理，只是这个类名位全限定类名
 * @Author : Mr.WangF
 * @Date: 2022/8/12 17:55
 */
public class ImportBean {

	private String name ="zhangsan";

	public ImportBean() {
		System.out.println("ImportBean 构造器...");
	}

	//@Bean
	private ConfigurationsBean configurationsBean(){
		ConfigurationsBean configurationsBean = new ConfigurationsBean();
		configurationsBean.setCb("configurationsBean的cb");
		return configurationsBean;
	}
}
