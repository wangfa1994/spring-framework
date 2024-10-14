package com.wf.model.lazy;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;


@ComponentScan(basePackages = {"com.wf.model.lazy"})
public class LazySourceTest {

	/*@Autowired
	private Cat cat;*/
	@Autowired
	@Lazy // lazy的情况下会产生代理对象, Lazy的代理对象是在解决依赖的时候进行判断产生的 ，LookUp的代理对象是在getBean判断beanDefinition中的属性产生的
	private Cat catLazy;



	public static void main(String[] args) {

		proxy();
		//lazy();
	}

	private static void lazy() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(LazySourceTest.class);

		applicationContext.refresh();

		LazySourceTest lazySourceTest = applicationContext.getBean(LazySourceTest.class);
		//System.out.println("cat: "+lazySourceTest.cat);
		System.out.println("catLazy: "+lazySourceTest.getCatLazy());
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

		pf.setTargetSource(targetSource);


		Object proxy = pf.getProxy(LazySourceTest.class.getClassLoader());

		Cat cat = (Cat)proxy;
		System.out.println(cat);

	}




	public Cat getCatLazy() {
		return catLazy;
	}

	public void setCatLazy(Cat catLazy) {
		this.catLazy = catLazy;
	}
}
