package com.wf.model.lazy;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Method;


@ComponentScan(basePackages = {"com.wf.model.lazy"})
public class LazySourceTest {

	/*@Autowired
	private Cat cat;*/
	@Autowired
	@Lazy // lazy的情况下会产生代理对象, Lazy的代理对象是在解决依赖的时候进行判断产生的 ，LookUp的代理对象是在getBean判断beanDefinition中的属性产生的
	private Cat catLazy;



	public static void main(String[] args) {

		 //lazy();

		//proxy();
		customerProxyFactory();

	}

	private static void lazy() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(LazySourceTest.class);

		applicationContext.refresh();

		LazySourceTest lazySourceTest = applicationContext.getBean(LazySourceTest.class);
		//System.out.println("cat: "+lazySourceTest.cat);
		System.out.println("catLazy: "+lazySourceTest.getCatLazy());
		System.out.println("catLazy: "+lazySourceTest.getCatLazy().getName());
		 //cat 和 catLazy 是同一个对象 ,打印的hash值相同,但是判断却不是同一个对象
		//System.out.println("cat 和 catLazy 是同一个对象"+(lazySourceTest.cat == lazySourceTest.catLazy));
	}

	/**
	 * 在进行处理依赖注入的时候，在DefaultListableBeanFactory的resolveDependency方法中，会进行是否标注了@Lazy注解
	 * 如果标注了，则会通过AutowireCandidateResolver自动候选接口解析器的实现类ContextAnnotationAutowireCandidateResolver#getLazyResolutionProxyIfNecessary来进行处理依赖
	 *
	 * 通过ProxyFactory产生对应的代理对象
	 *
	 * Skipped breakpoint at org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver$1:95 because it happened inside debugger evaluation Troubleshooting guide
	 *
	 * ProxyCreatorSupport
	 *  	ProxyFactory
	 * AopProxyFactory
	 * 		DefaultAopProxyFactory
	 *
	 * 	CglibAopProxy   ObjenesisCglibAopProxy
	 *
	 * 	Enhancer
	 *
	 * 	SpringProxy
	 *
	 * 	Advised
	 * 	DynamicAdvisedInterceptor
	 * 	DynamicUnadvisedInterceptor
	 *
	 */


	// 当发现了对应的lazy注解之后，会进行代理对象的产生，这里是产生代理对象的逻辑
	// org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver.buildLazyResolutionProxy
	private static void proxy(){
		TargetSource targetSource = new TargetSource() {
			@Override
			public Class<?> getTargetClass() {
				return Cat.class;
			}

			@Override
			public boolean isStatic() {
				return false;
			}

			@Override
			public Object getTarget() throws Exception {
				Cat cat = new Cat();
				cat.setAge(18);
				cat.setName("cat");
				return cat;
			}

			@Override
			public void releaseTarget(Object target) throws Exception {

			}
		};

		ProxyFactory pf = new ProxyFactory(); // 使用代理工厂，创建被@Lazy标记的代理对象
		pf.setTargetSource(targetSource); // 设置目标对象 ,会把我们的对象封装成TargetSource
		// 得到代理对象
		Object proxy = pf.getProxy(LazySourceTest.class.getClassLoader());

		Cat cat = (Cat)proxy;
		String name = cat.getName();
		System.out.println(cat); //打印的时候，会转到getTarget方法中处理

		// AopProxyFactory接口 是产生 AopProxy的，    在产生的过程中委派给了ProxyFactory类 更高级的抽象，封装了代理对象的创建(门面模式)
		// 代理对象是使用cglib产生的，他的增强逻辑是在哪里呢？ 在对应的

	}
	public static void customerProxyFactory(){
		// 模仿高级抽象
		Cat cat = new Cat();
		cat.setAge(18);
		cat.setName("cat");

		DefaultAopProxyFactory defaultAopProxyFactory = new DefaultAopProxyFactory();
		AdvisedSupport advisedSupport = new AdvisedSupport();
		advisedSupport.setTarget(cat);
		advisedSupport.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("===============方法执行前的逻辑");
			}
		});


		AopProxy aopProxy = defaultAopProxyFactory.createAopProxy(advisedSupport);

		Cat proxy = (Cat)aopProxy.getProxy();
		proxy.getName();
		System.out.println(proxy);


	}








	public Cat getCatLazy() {
		return catLazy;
	}

	public void setCatLazy(Cat catLazy) {
		this.catLazy = catLazy;
	}

	@Override
	public String toString() {
		return "LazySourceTest";
	}
}
