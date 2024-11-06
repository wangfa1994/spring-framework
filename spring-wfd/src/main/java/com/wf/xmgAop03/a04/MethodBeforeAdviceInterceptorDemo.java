package com.wf.xmgAop03.a04;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// 49课  springAop joinPoint beforeAdvice的标准实现
public class MethodBeforeAdviceInterceptorDemo {

	/** spring 在没有引入aspectj之前的实现
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

		 // 这个是我们自己手动进行实现的代理  MethodBeforeAdvice




		// 主要使用 AspectJProxyFactory 进行我们的设置与处理
		Map<String, Object> cache = new HashMap<>();
		// 创建 Proxy 工厂(AspectJ)  得到AOP代理对象
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache); // cache 目标对象，被代理的对象
		// 增加 Aspect 配置类 可以从这里解析出来对应的advice。
		//proxyFactory.addAspect(AspectConfiguration.class);
		proxyFactory.setExposeProxy(true);
		proxyFactory.addAdvice(new MethodBeforeAdvice() { // 我们手动添加的advice 会被封装成  DefaultPointcutAdvisor  MethodBeforeAdviceInterceptor 进行执行
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					Object proxy = AopContext.currentProxy();
					System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" +
							"代理对象：%s\n", args[0], args[1], proxy);
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
	 *
	 */

}
