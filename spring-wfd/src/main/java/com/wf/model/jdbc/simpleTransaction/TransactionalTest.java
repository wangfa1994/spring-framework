package com.wf.model.jdbc.simpleTransaction;

import com.wf.model.jdbc.tarnsactiond.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionalTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TranConfiguration.class);
		ProductService productService = context.getBean(ProductService.class);

		productService.updateProductStockCountById2(1,1L);
	}
}
