package com.wf.model.cycle.lazyResolve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/8/12 15:29
 */
@Component
public class Husband {


	//@Autowired
	private Wife wife;



	public Husband() {
		System.out.println("Husband构造器");
	}

	@Autowired
	@Lazy
	public Husband(Wife wife) {
		this.wife = wife;
	}

	//@Autowired
	public void betWife(Wife wife) {
		System.out.println("调用了Husband的setter方法");
		this.wife = wife;
	}

	public void sayWifeHello(){

		wife.sayHello();

	}


	public Wife getWife() {
		return wife;
	}


	@Override
	public String toString() {
		return "Husband{" +
				"wife=" + wife.hashCode() +
				'}'+this.hashCode();
	}
}
