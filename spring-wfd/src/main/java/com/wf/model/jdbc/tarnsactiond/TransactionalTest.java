package com.wf.model.jdbc.tarnsactiond;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionalTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TranConfiguration.class);
		OrderService orderService = context.getBean(OrderService.class);
		// orderService.submitOrder();
		orderService.submitOrder3();
	}
}
