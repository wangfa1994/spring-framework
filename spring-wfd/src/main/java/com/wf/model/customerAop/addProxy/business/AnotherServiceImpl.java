package com.wf.model.customerAop.addProxy.business;

public class AnotherServiceImpl implements AnotherService {
	@Override
	public void sayAnother(String name) {
		System.out.println("Another, " + name + "!");
	}
}
