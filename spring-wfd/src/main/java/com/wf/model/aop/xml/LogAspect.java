package com.wf.model.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Spring5以后顺序就一切正常
 * 正常：前置通知===目标方法===返回通知===后置通知
 * 异常: 前置通知===目标方法===异常通知===后置通知
 * try{
 *     前置通知
 *     目标方法的执行
 *     返回通知
 * }catch(){
 *     异常通知
 * }finally{
 *     后置通知
 * }
 *
 *
 */
public class LogAspect {

	public LogAspect(){
		System.out.println("LogAspect构造器...");
	}

	// 切点PointCut: 定义了切面中的通知应该在哪些连接点(JoinPoint)上执行
	// 连接点JointPoint:定义在应用程序执行过程中插入切面的候选点，通常是方法调用，spring中只支持方法级别的连接点
	public void pointCut(){

	}

	// 通知Advice： 定义了切面中实际操作的部分，代表切点匹配到的连接点上执行的代码
	//前置通知  增强方法/增强器
	public void logStart(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("logStart()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
	}
/*
	//返回通知
	@AfterReturning(value = "pointCut()",returning = "result")
	public void logReturn(JoinPoint joinPoint,Object result){
		String name = joinPoint.getSignature().getName();
		System.out.println("logReturn()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【result: "+result+"】");
	}




	//后置通知
	@After(value = "pointCut()")
	public void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("logEnd()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
	}


	//异常通知
	@AfterThrowing(value = "pointCut()",throwing = "e")
	public void logError(JoinPoint joinPoint,Exception e){
		String name = joinPoint.getSignature().getName();
		System.out.println("logError()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【exception: "+e+"】");
	}*/
}
