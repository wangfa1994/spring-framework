package com.wf.model.jdbc.tarnsactiond;

/**
 * CREATE TABLE IF NOT EXISTS order_info (
 * `id` bigint(20) NOT NULL,
 * `order_no` varchar(50) DEFAULT '',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; */

public class Order {
	/**
	 * 数据id
	 126
	 */
	private Long id;
	/**
	 * 订单编号
	 */
	private String orderNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
