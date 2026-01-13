package com.wf.model.enable.ibdr.server;

public class HttpServer implements Server {
	@Override
	public void start() {
		System.out.println("http 服务启动");
	}

	@Override
	public void stop() {
		System.out.println("http 服务关闭");
	}
}
