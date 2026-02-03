package com.wf.model.aop.xml;

public class HelloService {

	public HelloService(){
		System.out.println("....");
	}

	public String sayHello(String name){
		String result = "你好："+name;
		System.out.println(result);
		int length = name.length();
		return result + "---" + length;
	}

	public String echo(String name){
		String result = "echo："+name;
		System.out.println(result);
		int length = name.length();
		return result + "---" + length;
	}

}
