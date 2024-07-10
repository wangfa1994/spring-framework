package com.wf.model.cycle.three;

import com.wf.model.cycle.Wife;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstMan {

	@Autowired
	private SecondMan secondMan;


	public FirstMan() {
		System.out.println("firstMan 构造器");
	}
}
