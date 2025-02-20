package com.wf.xmgAop.a01.interceptor03;

public class EchoServiceImpl implements EchoService{

	@Override
	public String echo(String message) throws NullPointerException {
		return "[echo]"+message;
	}
}
