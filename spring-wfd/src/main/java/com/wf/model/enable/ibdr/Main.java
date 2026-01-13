package com.wf.model.enable.ibdr;

import com.wf.model.enable.ibdr.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.wf.model.enable.ibdr")
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		Server server = context.getBean(Server.class);
		server.start();server.stop();

	}
}
