package com.wf.xmgAop.a01.jdk;

public class ProxyExtendEchoService extends DefaultEchoService {


	@Override
	public String echo(String message) {


		long startTime = System.currentTimeMillis();
		String result = super.echo(message); // 继承的方式进行实现
		long costTime = System.currentTimeMillis() - startTime;
		System.out.println("echo 方法执行的实现：" + costTime + " ms.");
		return result;
	}
}
