package com.wf.model.jdbc.tarnsactiond;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;


	/**
	 * 场景一：外部方法无事务注解
	 * 内部方法一：saveOrder无事务注解
	 * 内部方法二：updateProductStockCountById添加@Required事务传播类型 @Transactional(propagation = Propagation.REQUIRED)
	 *
	 * 结果：方法一正常执行，方法二无法插入，报错不会对一产生影响
	 *
	 * 总结：外部方法无事务注解，内部方法添加REQUIRED事务传播类型时，内部方法抛出异常。
	 * 内部方法执行失败，不会影响外部方法的执行，外部方法执行成功
	 *
	 *
	 */
	public void submitOrder1(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById(1, 1L);
	}

	/**
	 * 为外部方法添加REQUIRED事务传播类型，内部方法无事务注解
	 * 方法一和方法二都无事务注解
	 *
	 * 结果：方法一和方法二都无法插入修改数据
	 *
	 *
	 * 总结：外部方法添加REQUIRED事务传播类型，内部方法无事务注解时，内部方法抛出异常，
	 * 会影响外部方法的执行，导致外部方法的事务回滚
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder2(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById(1, 1L);
	}


	/** 场景三：
	 * 为外部方法添加REQUIRED事务传播类型，
	 * 方法一 不添加事务注解
	 * 方法二添加事务注解  @Transactional(propagation = Propagation.REQUIRED)
	 *
	 * 结果：方法一和方法二都无法插入修改数据
	 *
	 *
	 * 总结：外部⽅法添加REQUIRED事务传播类型，内部方法添加REQUIRED事务传播类型时，
	 * 内部方法抛出异常，会影响外部⽅法的执行，事务会回滚
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder3(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById(1, 1L);
	}


	/** 场景四：
	 * 为外部方法添加REQUIRED事务传播类型，
	 * 方法一 不添加事务注解
	 * 方法二添加事务注解  @Transactional(propagation = Propagation.NOT_SUPPORTED)
	 *
	 * 结果：方法一和方法二都无法插入修改数据
	 *
	 *
	 * 总结：外部方法添加REQUIRED事务传播类型，内部方法添加NOT_SUPPORTED事务传播类
	 * 型时，内部方法抛异常，如果外部方法执⾏成功，事务会提交，如果外部方法执⾏失败，事务
	 * 会回滚。确定是否try里面的
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder4(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById(1, 1L);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder44(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		try {
			productService.updateProductStockCountById(1, 1L);
		}catch (Exception e){
			System.out.println("捕获到异常");
		}

	}


	/** 场景五：
	 * 为外部方法添加REQUIRED事务传播类型，
	 * 方法一 不添加事务注解
	 * 方法二添加事务注解  @Transactional(propagation = Propagation.REQUIRES_NEW)
	 *
	 * 结果：方法一和方法二都无法插入修改数据
	 *
	 *
	 * 总结：外部方法添加REQUIRED事务传播类型，内部方法添加REQUIRES_NEW事务传播类
	 * 型，内部方法抛出异常时，内部方法和外部方法都会执行失败，事务回滚
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder5(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById(1, 1L);
	}

	/** 场景六：
	 * 为外部方法添加REQUIRED事务传播类型，
	 * 方法一 不添加事务注解
	 * 方法二添加事务注解  @Transactional(propagation = Propagation.REQUIRES_NEW)
	 *
	 * 结果：方法一添加失败，方法二扣减成功
	 *
	 * 总结：外部⽅法添加REQUIRED事务传播类型，内部⽅法添加REQUIRES_NEW事务传播类
	 * 型，并且把异常代码移动到外部方法的末尾，内部方法抛异常时，外部方法执行失败，事务回
	 * 滚；内部方法执行成功时，事务提交

	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder6(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		productService.updateProductStockCountById2(1, 1L);
		int i = 1 / 0; // 提取到外层

	}

	/** 场景七：
	 * 为外部方法添加REQUIRED事务传播类型，
	 * 方法一 不添加事务注解
	 * 方法二添加事务注解  @Transactional(propagation = Propagation.REQUIRES_NEW)
	 *
	 * 但是是在同一个方法中
	 *
	 * 结果：方法一添加失败，方法二扣减失败
	 *
	 *
	 *
	 *总结：外部方法添加REQUIRED事务传播类型，内部方法添加REQUIRES_NEW事务传播类
	 * 型，并且把异常代码移动到外部方法的末尾，同时外部方法和内部方法在同⼀个类中，内部方
	 * 法抛出异常，外部方法和内部方法都会执行失败，事务回滚
	 *
	 * 这种情况属于同一个类中调用，会导致内部事务失效
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitOrder7(){
		//⽣成订单
		Order order = new Order();
		long number = Math.abs(new Random().nextInt(500));
		order.setId(number);
		order.setOrderNo("order_" + number);
		orderDao.saveOrder(order);
		//减库存
		this.updateProductStockCountById2(1, 1L);
		int i = 1 / 0; // 提取到外层

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateProductStockCountById2(Integer stockCount, Long id){
		productDao.updateProductStockCountById(stockCount, id);

	}

}
