package com.wf.xmgAop.a01.jdk00;

public class ProxyEchoService implements EchoService {

	// 组合的方式进行实现
	private final EchoService echoService;

	public ProxyEchoService(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public String echo(String message) {
		long startTime = System.currentTimeMillis();
		String result = echoService.echo(message);
		long costTime = System.currentTimeMillis() - startTime;
		System.out.println("echo 方法执行的实现：" + costTime + " ms.");
		return result;
	}

	@Override
	public String echo2(String message) throws NullPointerException {
		long startTime = System.currentTimeMillis();
		String result = echoService.echo2(message);
		long costTime = System.currentTimeMillis() - startTime;
		System.out.println("echo2 方法执行的实现：" + costTime + " ms.");
		return result;
	}
}
