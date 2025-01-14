package com.wf.xmgAop.a01.jdk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings({"unchecked","rawtypes"})
public class JdkProxyTest {

	public static void main(String[] args) {
		// jdk 静态代理存在 继承或者组合进行实现
		//staticProxy();
		System.out.println("======================================");
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

	private static void dynamicProxy1() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		DefaultEchoService target = new DefaultEchoService();

		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class},new EchoServiceHadler(target));
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
		// jdk 动态代理产生的类会继承Proxy,并且实现我们的接口  public final class $Proxy0 extends Proxy implements EchoService { }
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




	// 将代理类打印到控制台
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
