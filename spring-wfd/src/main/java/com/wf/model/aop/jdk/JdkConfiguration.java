package com.wf.model.aop.jdk;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/29 16:07
 */
@ComponentScan(basePackages = {"com.wf.model.aop.jdk"})
@EnableAspectJAutoProxy
public class JdkConfiguration {

}
