package com.wf.xmg.a06injection;

public class Main {
	/**
	 * 依赖注入的场景有哪些？
	 * 1. 实时注入
	 * 2. 延迟注入 @Lazy  ObjectProvider  ObjectFactory
	 *
	 * 依赖注入的方式有哪些？
	 *  1.构造器注入
	 *  2.setter方法注入
	 *  3.字段注入
	 *  4.方法注入
	 *  5.api注入
	 *
	 * 依赖注入的类型有哪些？
	 * 1.包装类型
	 * 2.引用类型
	 * 3.集合类型
	 *
	 * 多实例注入哪一个如何选择？
	 * @Qualifier 指定要注入的名称
	 * @Primary 指定那个是主要的
	 *
	 *
	 * 依赖注入的处理只要是在 DefaultListableBeanFactory#resolveDependency方法中，这个方法的定义来自AutowireCapableBeanFactory.
	 *
	 *
	 */
}
