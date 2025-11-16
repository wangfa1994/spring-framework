package com.wf.model.customerAop.onlyAop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/*
*  只实现了AOP的执行引擎
*
* */

public class Main {
	public void hello() {
		System.out.println("Hello World!");
	}

	public static void main(String[] args) throws Throwable {
		Main target = new Main();
		Method method = Main.class.getMethod("hello");

		List<MethodInterceptor> chain = Arrays.asList(new SimpleLoggingInterceptor());

		MethodInvocation invocation = new SimpleMethodInvocation(target, method, new Object[0], chain);
		invocation.proceed();
	}

	/* JoinPoint的理解
	* 首先理解执行上下文， 执行上下文是程序在某个特定执行点的完整状态信息，包括[目标对象，调用方法，方法参数，调用者信息，当前线程状态，调用栈信息等]
	* 比如这样一个方法的调用：userService.updateUser(user, "admin");
	* 在这个调用发生时，执行上下文包含：
	* 目标对象：userService实例
	* 被调用方法：updateUser方法
	* 方法参数：[user对象, "admin"字符串]
	* 调用者信息：谁在调用这个方法
	* 当前线程状态：线程ID、安全上下文等
	* 调用栈信息：方法是从哪里被调用的
	*总结执行上下文，就是获得当前执行点的一些元信息
	*
	* 然后在我们编码的时候想要进行一些通用逻辑处理，比如记录日志，就发生一些问题：我们需要在不同的方法中进行硬编码，会分散到不同的业务逻辑中：
	*     public void updateUser(User user, String operator) {
			// 如果想记录日志，需要手动获取信息
			System.out.println("方法: " + "updateUser");  // 硬编码方法名
			System.out.println("参数: " + user + ", " + operator);  // 手动列举参数
			System.out.println("调用者: " + this.getClass());  // 获取目标对象
			// 实际业务方法
			}
	  问题：信息获取分散、重复代码、硬编码

	* 然后就开始进行对这个问题进行优化，进行抽象封装，于是就有了JoinPoint接口的抽象
	* public interface JoinPoint {
	* 	Object proceed() throws Throwable; // 抽象了"继续执行"这个动作
	* 	Object getThis(); // 抽象了"获取目标对象"
	* 	AccessibleObject getStaticPart(); // 抽象了"获取静态信息"（如Method对象）
	* }
	* 第一层的抽象JoinPoint：具备了最基础的执行点概念，任何执行点都具有继续执行，获取目标对象和获得静态信息的能力
	* 后来发展过程中，又产生了带参数的调用执行点 Invocation
	* 			public interface Invocation extends Joinpoint {
					Object[] getArguments();  // 增加了获取参数的能力
				}
	* 最后形成了方法调用执行点 MethodInvocation
	*		 	public interface MethodInvocation extends Invocation {
					Method getMethod();  // 增加了获取具体方法的能力
				}
	有了这些个抽象，我们在进行记录我们的相关信息的时候，就不会进行写死了，而是通过相关方法的调用可以直接使用，这样的话，我们就优化出来了我们的JoinPoint
	* ,然后我们能得到整体的信息了，那么我们的通用逻辑是不是也可以进行提取了呢，形成我们的拦截器链,
	*  		interface MethodInterceptor extends Interceptor {
				Object invoke(@Nonnull MethodInvocation invocation) throws Throwable;
	 		 }
	这样的话，我们就可以将我们的通用逻辑形成一个链，然后通过参数MethodInvocation进行通用的处理了
			public class LogInterceptor implements MethodInterceptor {
				public Object invoke(MethodInvocation invocation) throws Throwable {
					// 统一的方式获取上下文信息
					Method method = invocation.getMethod();
					Object target = invocation.getThis();
					Object[] args = invocation.getArguments();

					System.out.println("调用方法: " + target.getClass() + "." + method.getName());
					System.out.println("参数: " + Arrays.toString(args));
					Object result = invocation.proceed(); //进行方法的继续执行
					System.out.println("返回值: " + result);
					return result;
				 }
			}
	*
	*
	*
	*
	*
	*
	*
	*
	*
	*
 	*
	*
	*
	*
	*
	* JoinPoint连接点 是所有拦截点的抽象基础 。 是一个"执行上下文的抽象",而不是具体的执行点 。 它定义的是"在某个执行点上，我能获取什么信息，能做什么操作
	* 代表程序执行过程中的一个点，比如方法调用、字段访问等
	* 提供访问当前连接点上下文信息的方法【如何理解上下文信息，提供的这些方法是哪些】
	* 三个方法：
	* 	1.proceed()  继续执行到下一个拦截器或目标
	*   2.getThis() 获取当前执行的目标对象
	*   3.getStaticPart() 获取静态部分（比如Method对象）
	*
	*
	*
	*
	*
	* */

}
