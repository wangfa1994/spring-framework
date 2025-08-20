package com.wf.xmgAop03.a14;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.classreading.MetadataReaderFactory;

/**
 *
 * 73 Aop Infrastructure Bean 接口
 *
 * @see org.springframework.aop.framework.AopInfrastructureBean
 *
 * spring aop 基础bean的标记接口
 *
 * 实现
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator
 * @see org.springframework.aop.scope.ScopedProxyFactoryBean
 *
 * 判断逻辑
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#isInfrastructureClass(Class)
 *
 * @see org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate(BeanDefinition, MetadataReaderFactory)
 *
 *
 */
public class A04AopInfrastructureBeanDemo {
}
