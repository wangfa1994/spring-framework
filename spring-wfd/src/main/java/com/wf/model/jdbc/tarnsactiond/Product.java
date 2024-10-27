package com.wf.model.jdbc.tarnsactiond;

import java.math.BigDecimal;
/*
* CREATE TABLE IF NOT EXISTS product_info (
	  `id` bigint(20) NOT NULL,
	  `product_name` varchar(50) DEFAULT NULL,
	  `product_price` decimal(10,2) DEFAULT NULL,
	  `stock_count` int(11) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

	INSERT INTO `product_info`(`id`, `product_name`, `product_price`, `stock_count`) VALUES (1, '笔记本电脑', 10000.00, 100)
*/
public class Product {
	/**
	 * 数据id
	 */
	private Long id;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品价格
	 */
	private BigDecimal productPrice;
	/**
	 * 库存数量
	 */
	private Integer stockCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
}
