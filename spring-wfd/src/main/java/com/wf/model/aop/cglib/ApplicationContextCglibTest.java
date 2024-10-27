package com.wf.model.aop.cglib;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextCglibTest {

    public static void main(String[] args) {

		// cglib
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(CglibConfiguration.class);
		HelloService helloService = annotationConfigApplicationContext.getBean(HelloService.class);

		String zhangsan = helloService.sayHello("zhangsan");
		System.out.println("cglib动态代理："+zhangsan);


	}

/**
 * 基本概念：
 * 连接点(JoinPoint): 连接点代表应用程序执行过程中某一个特定的点，特别指可以被AOP拦截的方法调用，所有的方法都可以说是一个连接点。连接点是潜在的拦截点，是程序执行流中一个可以识别的位置。
 * 切入点(PointCut): 定义了切面中的通知应该在哪些连接点(JoinPoint)上执行。是对连接点的一种筛选机制，通过AspectJ切入点表达式来匹配和定位一系列的连接点
 * 通知(Advice): 定义切面要执行的操作，真正逻辑处理的地方,代表在切点匹配到连接点上要执行的代码。包括前置通知/后置通知/异常通知/最终通知/环绕通知
 * 切面(Aspect)： 封装了横切关注点，一个切面由通知(advice)和切入点(pointCut)构成，切面定义了你要在什么地方(切入点)做什么事情(通知)
 *
 *
 * 连接点是广泛的潜在拦截位置集合，而切点则精确地指定了我们关心并希望在其中插入横切关注点（如日志、安全检查）的具体连接点
 *
 * 所有的方法都是一个一个的连接点，通过切入点的切入点表达式筛选出匹配的连接点，然后通过书写通知类型和逻辑来决定在筛选出来的连接点上执行什么操作。这就形成了切面。
 *
 *
 *织入：将切面应用到目标对象，并创建出代理对象的过程。这个过程可以是编译器，类加载期间，或运行期间进行。spring采用的是运行期间织入。在程序运行过程中为目标对象动态创建代理对象。
 *目标对象：切面要织入的时机对象，包括业务逻辑的类的实例。
 *
 *
 *
 *通过@EnableAspectJAutoProxy  在@Import的BeanFactory的后置处理中创建对应的AnnotationAwareAspectJAutoProxyCreator代理BeanDefinition
 * 底层的后置处理器
 * 通过RootBeanDefinition注册 org.springframework.context.annotation.internalConfigurationAnnotationProcessor---->ConfigurationClassPostProcessor
 *通过RootBeanDefinition注册 org.springframework.context.annotation.internalAutowiredAnnotationProcessor---->AutowiredAnnotationBeanPostProcessor
 *
 *按需通过RootBeanDefinition注册  org.springframework.context.annotation.internalCommonAnnotationProcessor---->CommonAnnotationBeanPostProcessor ---支持JSR250
 *
 *
 * AnnotatedGenericBeanDefinition 配置类的bean
 *
 */



}
