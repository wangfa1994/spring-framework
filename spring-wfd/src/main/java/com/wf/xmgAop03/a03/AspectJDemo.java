package com.wf.xmgAop03.a03;

// spring 与aspectj的pointcut适配
public class AspectJDemo {

	// AspectJExpressionPointcut 类，桥接了AspectJ 语法对spring的支持
	// spring并没有新增pointCut语法，而是交给AspectJ进行了处理，这里就是使用了适配器模式
	// AspectJExpressionPointcut中的 PointcutExpression类
	// matches 方法，直接委派给 aspectj

}
