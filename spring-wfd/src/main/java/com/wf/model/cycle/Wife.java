package com.wf.model.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/8/12 15:29
 */
@Component
public class Wife {


	@Autowired
	private Husband husband;

	public Wife() {
		System.out.println("Wife构造器");
	}

//	@Autowired
	public Wife(Husband husband) {
		this.husband = husband;
	}

	//@Autowired
	public void aetHusband(Husband husband) {
		System.out.println("调用了Wife的setter方法");
		this.husband = husband;
	}


	@Override
	public String toString() {
		return "Wife{" +
				"husband=" + husband.hashCode() +
				'}'+this.hashCode();
	}
}
