package com.wf.xmgAop03.a12;

/** 类图描述
 * 62 AopProxyFactory的配置管理器  AdvisedSupport
 *
 * 核心api
 * @see org.springframework.aop.framework.AdvisedSupport
 *
 * 语义 代理配置
 *
 * 基类
 * @see org.springframework.aop.framework.ProxyConfig
 *
 * 实现接口
 * @see org.springframework.aop.framework.Advised
 *
 * 使用场景
 * @see org.springframework.aop.framework.AopProxy 实现
 *
 *
 */
public class A01AdvisedSupportDemo {

	/**
	 *
	 * AdvisedSupport 进行了继承了 ProxyConfig 实现 Advised
	 *
	 * 是一个配置类，继承了 ProxyConfig,进行了一些基本配置的设置，
	 * 实现了advised ， 主要是进行一些advisor的操作，新增，移除，数量之类的。
	 *
	 */
}
