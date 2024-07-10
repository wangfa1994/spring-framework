package com.wf.model.aop.jdk;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/10/25 19:25
 */
@Component  //切面也是容器中的组件
@Aspect //说明这是切面
public class LogJavaAspect {

    public LogJavaAspect(){
        System.out.println("LogAspect...");
    }


	@Pointcut("execution(* com.wf.model.aop.jdk.JavaService.helloJava(..))")
	public void pointCutExpress() {
	}

	// Around.class, Before.class, After.class, AfterReturning.class, AfterThrowing.class

    //前置通知  增强方法/增强器
    @Before("pointCutExpress()") //这是切面，通知+切点表达式
    public void logStart(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("前置通知：logStart()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
    }

	//后置通知
	@After("pointCutExpress()")
	public void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("后置通知：logEnd()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
	}

    //返回通知
    @AfterReturning(value = "pointCutExpress()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println("返回通知：logReturn()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【result: "+result+"】");
    }

    //异常
    @AfterThrowing(value = "pointCutExpress()",throwing = "e")
    public void logError(JoinPoint joinPoint,Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println("异常通知：logError()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【exception: "+e+"】");
    }

	//异常
	//@Around("writeLog()")
	public Object logAround(ProceedingJoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("环绕通知：logAround()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
		return null;
	}
}
