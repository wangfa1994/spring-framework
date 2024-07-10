package com.wf.model.aop.cglib;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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
@Component  //切面也是容器中的组件
@Aspect //说明这是切面 封装了很切关注点，可以看做是通知(Advice) 和切点(PointCut)之间关系的组合,切面定义了你要在什么地方(PointCut)做什么事情(Advice)
public class LogAspect {

	public LogAspect(){
		System.out.println("LogAspect...");
	}

	// 切点PointCut: 定义了切面中的通知应该在哪些连接点(JoinPoint)上执行
	// 连接点JointPoint:定义在应用程序执行过程中插入切面的候选点，通常是方法调用
	@Pointcut("execution(* com.wf.model.aop.cglib.HelloService.sayHello(..))")
	public void pointCut(){

	}

	// 通知Advice： 定义了切面中实际操作的部分，代表切点匹配到的连接点上执行的代码
	//前置通知  增强方法/增强器
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("logStart()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
	}

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
	}
}
