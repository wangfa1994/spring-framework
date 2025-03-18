package com.wf.xmgAop03.a03;

/**
 *  47.  pointcut 的 AspectJ 实现
 *
 * 实现类：org.springframework.aop.aspectj.AspectJExpressionPointcut (2.0版本引入的，说明2.0之前没有引入，使用的就是一些便捷实现)
 *
 * 指令支持 supported_primitives
 *
 * 表达式 org.aspectj.weaver.tools.PointcutExpression
 *
 *
 * @see org.springframework.aop.aspectj.AspectJExpressionPointcut
 *
 * @see org.aspectj.weaver.tools.PointcutExpression
 *
 *
 */
// spring 与aspectj的pointcut适配
public class AspectJDemo {

	// AspectJExpressionPointcut 类，桥接了AspectJ 语法对spring的支持
	// spring并没有新增pointCut语法，而是交给AspectJ进行了处理，这里就是使用了适配器模式
	// AspectJExpressionPointcut中的 PointcutExpression类
	// matches 方法，直接委派给 aspectj

}
