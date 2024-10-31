package com.wf.xmgAop.a01.interceptor;

public class EchoServiceImpl implements EchoService{

	@Override
	public String echo(String message) throws NullPointerException {
		return "[echo]"+message;
	}
}
