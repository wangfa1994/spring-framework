package com.wf.model.aop.cglib;

import org.springframework.context.annotation.*;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/29 16:07
 */
@ComponentScan(basePackages = {"com.wf.model.aop.cglib"})
@EnableAspectJAutoProxy
public class CglibConfiguration {

}
