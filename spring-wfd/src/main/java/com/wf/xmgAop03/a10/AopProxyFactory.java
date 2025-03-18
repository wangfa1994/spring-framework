package com.wf.xmgAop03.a10;

/**
 * 59.
 * AopProxy 工厂接口与实现
 *
 * 通过AopProxyFactory 产生AopProxy
 *
 * aop代理工厂
 *
 *
 * 接口
 * @see org.springframework.aop.framework.AopProxyFactory
 *
 *
 * 默认实现
 * @see org.springframework.aop.framework.DefaultAopProxyFactory  有且仅有此一个实现 (而且还没有产生aspectJ动态代理对象呢？)
 *
 * 返回类型，即返回我们的Aop代理对象
 *
 * @see org.springframework.aop.framework.JdkDynamicAopProxy
 * @see org.springframework.aop.framework.CglibAopProxy
 * @see org.springframework.aop.framework.ObjenesisCglibAopProxy
 *
 *
 *
 * 进行了DefaultAopProxyFactory类分析
 *
 * 我们可以进行替换掉默认的代理实现 DefaultAopProxyFactory
 *
 * aop代理对象工厂
 */
public class AopProxyFactory {


}
