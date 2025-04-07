package com.wf.xmgAop03.a04.a02beforeAdvice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *重点 49 JoinpointBeforeAdvice 标准实现
 *
 *  接口
 *  	-标准接口  org.springframework.aop.BeforeAdvice
 *      -方法界别  org.springframework.aop.MethodBeforeAdvice
 *
 *  实现 org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor
 *
 *
 *  连接点joint  的 执行动作 Advice 三类
 *
 * @see org.springframework.aop.BeforeAdvice
 * @see org.springframework.aop.MethodBeforeAdvice
 */
// 49课  springAop joinPoint beforeAdvice的标准实现
public class A01MethodBeforeAdviceInterceptorDemo {

	/** spring 在没有引入aspectj之前的实现，给到我们用户使用的
	 *
	 *  我们可以使用的两个标准接口 BeforeAdvice  MethodBeforeAdvice
	 * 标准接口 BeforeAdvice
	 * 方法级别 MethodBeforeAdvice
	 *
	 * 适配器的实现 这个是springAop框架进行使用的
	 * 实现类  MethodBeforeAdviceInterceptor
	 *
	 *
	 * MethodBeforeAdviceInterceptor 类 实现了 MethodInterceptor  并且内置了 MethodBeforeAdvice
	 *
	 *
	 *  我们使用的 MethodBeforeAdvice 最终会被包装成 aop框架使用的  MethodBeforeAdviceInterceptor
	 *  然后通过  MethodBeforeAdviceInterceptor 的invoke方法 进行执行到我们的 MethodBeforeAdvice
	 *
	 *  一个包装成一个，一对一， MethodBeforeAdviceInterceptor 是框架进行使用的，
	 *
	 */


	public static void main(String[] args) {

		Map<String, Object> cache = new HashMap<>();
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache); // cache 目标对象，被代理的对象
		// 增加 Aspect 配置类 可以从这里解析出来对应的advice。
		// proxyFactory.setExposeProxy(true);
		proxyFactory.addAdvice(new MethodBeforeAdvice() { // 我们手动添加的advice 会被封装成  DefaultPointcutAdvisor  MethodBeforeAdviceInterceptor 进行执行
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					//Object proxy = AopContext.currentProxy();
					//System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" + "代理对象：%s\n", args[0], args[1], proxy);
					System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s " , args[0], args[1]);
				}
			}
		});
		// 通过代理对象存储数据
		Map<String, Object> proxy = proxyFactory.getProxy(); // 这个proxyFactory 创建AOP代理，从AOP代理中得到代理对象，代理工厂获取代理对象
		proxy.put("1", "A");
		System.out.println(proxy.get("1"));
		// AopProxyFactory AOP代理工厂获取AOP的代理对象
	}



	/** ProxyConfig
	 * 		--AdvisedSupport   AOP代理配置管理器的基类。
	 * 			--ProxyCreatorSupport
	 * 				--	AspectJProxyFactory
	 *
	 * MethodBeforeAdvice 会被spring内部转换包装为  MethodBeforeAdviceInterceptor
	 *
	 */

}
