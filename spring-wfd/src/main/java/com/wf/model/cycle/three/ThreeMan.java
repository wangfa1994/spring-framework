package com.wf.model.cycle.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreeMan {

	@Autowired
	private FirstMan firstMan;


	public ThreeMan() {
		System.out.println("threeMan 构造器");
	}
}
