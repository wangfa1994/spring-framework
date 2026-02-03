package com.wf.xmgAop.a01.jdk00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings({"unchecked","rawtypes"})
public class JdkProxyTest {

	public static void main(String[] args) {
		System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true"); //jdk动态代理保存字节码

		//staticProxy(); // jdk 静态代理存在 继承或者组合进行实现
		dynamicProxy(); // 内置判断型
		//dynamicProxy1(); // 通用型
	}

	private static void staticProxy() {
		// 组合的方式
		EchoService echoService = new ProxyEchoService(new DefaultEchoService());
		echoService.echo("Hello,World");
		// 继承的方式
		ProxyExtendEchoService proxyExtendEchoService = new ProxyExtendEchoService();
		proxyExtendEchoService.echo("Hello,World");
	}


	/*
	* 产生的代理类被final修饰，并且继承了Proxy类，实现了我们的接口，
	* 	继承 Proxy：提供了一个标准化的、必需的机制来持有和调用 InvocationHandler。这是代理功能的“发动机”。
	* 	使用 final：保护这个机制不被破坏。它确保了“发动机”是唯一的动力来源，没有人能绕过它给车子装上另一个引擎或直接推着走.
	* 代理类中的方法都被转到InvocationHandler的invoke方法中，并且第一个参数是代理类对象自己。所以如果在invocationHandler中使用第一个对象的话，则会进行无限循环调用导致栈溢出。
	*
	*
	* Proxy.newProxyInstance方法
	* 1.将我们的classLoad,接口列表和InvocationHandler传递进行用于产生代理对象
	* 2.通过Proxy.getProxyConstructor获得到对应的构造器方法
	* 	2.1 通过new ProxyBuilder(ld, clv.key()).build() 进行得到对应的构造器
	* 		2.1.1 通过 ProxyBuilder.build方法进行处理 ProxyBuilder为Proxy的静态内部类
	* 			2.1.1.1调用ProxyBuilder.defineProxyClass方法进行构造我们的代理类的名称和调用ProxyGenerator.generateProxyClass进行产生代理类的字节码
	* 				2.1.1.1.1 ProxyGenerator.generateProxyClass中进行generateClassFile方法调用产生我们的代理类的字节码，这里进行字节码的拼接
	* 					2.1.1.1.1.1通过ProxyGenerator.addProxyMethod进行方法的字节码拼接，这里都会进行产生代理方法
	* 					2.1.1.1.1.2通过ProxyGenerator.generateConstructor进行构造器的字节码产生，这里直接写死拼接了InvocationHandler.所以代理对象一定要构造InvocationHandler,这里还进行了一些大小的判断，字段和方法个数不能超过65535
	* 								[需要注意的是这个InvocationHandler其实是属于Proxy中的，因为这个代理类是继承了Proxy的,通过自身的构造传递给Proxy,如果没有继承关系，每个代理类都需要自己声明这个字段]
	* 				2.1.1.1.2 进行判断是否保存字节码，如果保存的话，进行保存 generateProxyClass
	* 3.通过Proxy.newProxyInstance进行产生代理对象。这个代理对象的构造方法是怎么得到的，为什么会有一个固定的InvocationHandler参数
	*
	* 主要牵扯到 Proxy   Proxy静态内部类ProxyBuilder    ProxyGenerator 和 ProxyGenerator私有类ProxyMethod 四个类合作产生字节码并产生对象
	*
	*
	*
	* 代理类可以再被代理，但是没有必要，
	* 1.代理类（如 $Proxy0）是一个普通的 final 类，它实现了你指定的接口，JDK 的 Proxy.newProxyInstance 方法核心要求是：传入一个 ClassLoader 和一组接口（至少一个）
	* 由于代理类实现了接口，你完全可以把它当作一个普通的接口实现类，扔给 Proxy.newProxyInstance 方法去创建第二层代理
	*
	* 虽然语法上允许，但在实践中会遇到几个关键问题
	* 1.final问题：JDK 动态代理生成的类是 final 的。JDK 代理机制是基于接口的，它创建的是实现类，而不是子类。因此，final 关键字并不妨碍你再次实现相同的接口来创建代理。它只是禁止你通过继承来扩展这个类。所以，对于嵌套 JDK 代理，final 并不是一个障碍
	* 2.调用栈问题：InvocationHandler 的调用栈问题 ,成了一个代理链。你必须小心地设计你的 InvocationHandler 逻辑，确保它们能正确地传递调用，而不是形成死循环。
	* 3.性能问题：每一层代理都会增加一次反射方法调用的开销（method.invoke）和 InvocationHandler 中的逻辑处理。嵌套代理会使性能开销成倍增加
	* 使用责任链模式，再一个代理类中进行责任链模式的使用调用。
	* */
	private static void dynamicProxy1() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		DefaultEchoService target = new DefaultEchoService();

		// 为什么Proxy.newProxyInstance执行之后，会生成一个全新的字节码
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class},new EchoServiceHadler(target));
		System.out.println("代理对象属于什么呢？"+proxy.getClass());
		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");
		echoService.echo2("Hello,World");
	}

	static class EchoServiceHadler implements InvocationHandler{
		private EchoService service;
		public EchoServiceHadler(EchoService service) {
			this.service = service;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("come into proxy "+method.getName());
			return method.invoke(service,args);
		}
	}




	private static void dynamicProxy() {
		// jdk 动态代理产生的类会继承Proxy,并且实现我们的接口,可以多实现接口  public final class $Proxy0 extends Proxy implements EchoService { }
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// classLoader 新生成的字节码的类使用的是那个类加载器，要属于当前classLoad的

		// $Proxy0 代理的类会继承这个Proxy代理类，然后还是实现了我们的接口，继承了Proxy，导致我们的的h进行了传递执行 ，这种是内置的形式
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				addClassToDisk(proxy.getClass().getName(), proxy.getClass());
				System.out.println("come into proxy "+method.getName());
				// 代理类的代理可以存在多个类，是一个类数组的形式Class[]的形式。不同的可以有不同的逻辑
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
					if(method.getName().equals("echo")){
						return echoService.echo((String) args[0]);
					}else{
						return echoService.echo2((String) args[0]);
					}

				}

				return null;
			}
		});

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");
		echoService.echo2("Hello,World");

		// $Proxy1 观察返回的对象是累增的
		Object proxy2 = Proxy.newProxyInstance(classLoader,
				new Class[]{Comparable.class},
				(proxy1, method, args1) -> {
					return null;
				});

		System.out.println(proxy2);
	}




	// 将代理类打印到控制台  不建议使用这个，使用自带的 jdk.proxy.ProxyGenerator.saveGeneratedFiles 参数进行配置即可
	private static void addClassToDisk(String className, Class<?> cl) {

		FileOutputStream out = null;
		try {
			//用于生产代理对象的字节码
			Class proxyGen = Class.forName("java.lang.reflect.ProxyGenerator");
			Method m =proxyGen.getDeclaredMethod("generateProxyClass",String.class,Class[].class);
			m.setAccessible(true);
			byte[] classFile = (byte[]) m.invoke(null,className, cl.getInterfaces());

			//byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
			// jdk 动态代理会在项目的根目录下生成对应的class文件，$Proxy
			 out = new FileOutputStream("$Proxy0.class");
			//将代理对象的class字节码写到硬盘上
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
