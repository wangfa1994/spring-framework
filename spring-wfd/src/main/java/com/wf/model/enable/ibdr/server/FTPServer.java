package com.wf.model.enable.ibdr.server;

public class FTPServer implements Server {
	@Override
	public void start() {
		System.out.println("ftp 服务启动");
	}

	@Override
	public void stop() {
		System.out.println("ftp 服务关闭");
	}
}
