package com.wf.xmgAop03.a13;

import com.wf.xmgAop02.a04.a04ProxyFactoryBean.CustomerMethodInterceptor;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 66 AdvisedSupport 事件监听器
 *
 * 核心api
 * @see org.springframework.aop.framework.AdvisedSupportListener
 *
 * 事件对象
 * @see org.springframework.aop.framework.AdvisedSupport
 *
 * 事件来源
 * @see org.springframework.aop.framework.ProxyCreatorSupport
 *
 * 激活事件触发
 *
 * @see org.springframework.aop.framework.ProxyCreatorSupport#createAopProxy()
 *
 *变更事件触发
 *  代理接口变化时，Advisor 变化是，配置复制时
 *
 */
public class A02AdvisedSupportListenerDemo {


	public static void main(String[] args) {
		DefaultEchoService defaultEchoService = new DefaultEchoService();
		ProxyFactory proxyFactory  = new ProxyFactory(defaultEchoService);
		proxyFactory.setTargetClass(DefaultEchoService.class);
		proxyFactory.addAdvice(new CustomerMethodInterceptor()); // 添加我们的拦截逻辑，

		proxyFactory.addListener(new AdvisedSupportListener() {
			@Override
			public void activated(AdvisedSupport advised) {
				System.out.println("AOP 配置对象已激活："+advised);
			}

			@Override
			public void adviceChanged(AdvisedSupport advised) {
				System.out.println("AOP 配置对象已改变："+advised);
			}
		});


		EchoService proxy = (EchoService)proxyFactory.getProxy(); // 激活事件触发 底层createAopProxy()，这里才会激活监听
		System.out.println("新增Advice。。。。");
		proxyFactory.addAdvice(new Advice() {
		});

	}
}
