package com.wf.model.aop.jdk;

import com.wf.model.aop.cglib.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextJdkTest {

    public static void main(String[] args) {

		// cglib
		System.out.println("hello spring");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JdkConfiguration.class);

		JavaService javaService = annotationConfigApplicationContext.getBean(JavaService.class);
		
		String zhangsan1 = javaService.helloJava("zhangsan"); //JdkDynamicAopProxy
		System.out.println("接口："+zhangsan1);


	}

/**
 *  基本概念：
 *  切面(Aspect)： 封装了横切关注点，一个切面由通知(advice)和切入点(pointCut)构成，切面定义了你要在什么地方(切入点)做什么事情(通知)
 *  通知(Advice): 定义切面要执行的操作，真正逻辑处理的地方,代表在切点匹配到连接点上要执行的代码。包括前置通知/后置通知/异常通知/最终通知/环绕通知
 *  切入点(PointCut): 定义了切面中的通知应该在哪些连接点(JoinPoint)上执行。是对连接点的一种筛选机制，通过AspectJ切入点表达式来匹配和定位一系列的连接点
 *  连接点(JoinPoint): 连接点代表应用程序执行过程中某一个特定的点，特别指可以被AOP拦截的方法调用，所有的方法都可以说是一个连接点。连接点是潜在的拦截点，是程序执行流中一个可以识别的位置。
 *
 *  连接点是广泛的潜在拦截位置集合，而切点则精确地指定了我们关心并希望在其中插入横切关注点（如日志、安全检查）的具体连接点
 *
 *  所有的方法都是一个一个的连接点，通过切入点的切入点表达式筛选出匹配的连接点，然后通过书写通知类型和逻辑来决定在筛选出来的连接点上执行什么操作。这就形成了切面。
 *
 *  织入：将切面应用到目标对象，并创建出代理对象的过程。这个过程可以是编译器，类加载期间，或运行期间进行。spring采用的是运行期间织入。在程序运行过程中为目标对象动态创建代理对象。
 *  目标对象：切面要织入的时机对象，包括业务逻辑的类的实例。
 *
 * 基本流程：
 * 先解析配置类，从配置类中得到import的AspectJAutoProxyRegistrar类，属于ImportBeanDefinitionRegistrar
 * 在ConfigurationClassPostProcessor属于工厂后置器(BeanDefinitionRegistryPostProcessor)解析处理配置类的过程中，会进行registerBeanDefinitions方法的执行
 * ，在进行次方法的执行过程中，又会将AnnotationAwareAspectJAutoProxyCreator类进行注入到系统中，此类是属于BeanPostProcessor后置处理器
 * 此时准备工作完成。
 * 在创建单实例对象时，首先在会通过AnnotationAwareAspectJAutoProxyCreator后置处理器的postProcessBeforeInstantiation进行切面相关信息的初始化处理
 * 然后再创建对象。
 * 在创建对象的过程中的最后一步，执行BeanPostProcessor的真正接口所有的 postProcessAfterInitialization时，通过AnnotationAwareAspectJAutoProxyCreator的AbstractAutoProxyCreator类对代理对象在这里产生的
 * 产生代理对象的时候，会利用AspectJ进行判断我们的类实例是否满足前面条件，如果满足会处理出来满足的切面列表，然后在创建代理对象的时候，会将实例对象，切面列表进行封装成ProxyFactory传入到我们的代理类中。
 *
 * 然后再方法调用的时候会进入我们的JdkDynamicAopProxy，这里保存了ProxyFactory。从这里解析出来通知。
 *
 *
 *
 *
 * AnnotationAwareAspectJAutoProxyCreator 实现了SmartInstantiationAwareBeanPostProcessor 说白了，他就是一个SmartInstantiationAwareBeanPostProcessor的后置处理器
 * SmartInstantiationAwareBeanPostProcessor的处理时机
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */



}
