package com.wf.xmg.a09beanLifecycle.a07life;

import com.wf.xmg.a09beanLifecycle.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, InitializingBean, DisposableBean {

	private String desc;

	@Override
	public void setBeanName(String name) {
		System.out.println("7.进入 容器的 Aware相关接口 setBeanName 方法 V6---->V7");
	}
	@PostConstruct
	public void postConstruct() {
		System.out.println("8.进入 postConstruct 方法 V7---->V8");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("10.进入 afterPropertiesSet 方法 V9---->V10");
	}
	public void initMethod() {
		System.out.println("11.进入 initMethod 方法 V10---->V11");
	}


	@PreDestroy
	public void preDestroy() {
		System.out.println("14.进入 preDestroy 方法 V13---->V14");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("16.进入 destroy 方法 V15---->V16");
	}

	public void destroyMethod() {
		System.out.println("17.进入 destroyMethod 方法 V16---->V17");
	}









	public void setDesc(String desc) {
		this.desc = desc;
	}
}
