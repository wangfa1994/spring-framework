package com.wf.xmgAop.a06;

public class PointCutXmlConfig {


	// 这里只定义我们的通知方法
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("xml  @Before any public method.");
	}
}
