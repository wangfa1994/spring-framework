package com.wf.model.cycle.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondMan {

	@Autowired
	private FirstMan firstMan;

	@Autowired
	private ThreeMan threeMan;

	public SecondMan() {
		System.out.println("secondMan 构造器");
	}
}
