package com.wf.xmgAop.a00;

import java.net.SocketTimeoutException;
import java.util.Objects;

public class ClassLoadingDemo {

	public static void main(String[] args) throws Exception {

		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		// classLoad  APPClassLoad ClassLoaders$AppClassLoader   urlClassLoad

		while (true){
			ClassLoader parentClassLoader = contextClassLoader.getParent();
			if(Objects.nonNull(parentClassLoader)){
				System.out.println(parentClassLoader);
				contextClassLoader = parentClassLoader;
			}else{
				break;
			}
		}

		 contextClassLoader = Thread.currentThread().getContextClassLoader();
		Class<?> aClass = contextClassLoader.loadClass("com.wf.xmgAop.a00.StudentClassDemo"); // 传进去全路径名称，他是怎么知道去哪里找资源呢？
		Object o = aClass.getDeclaredConstructor().newInstance();
		System.out.println(o);


		// 基本类 CLass类 Method类
		//字节码提升 ClassVisitor

		// oop 封装  继承  多态

		// java 属于静态语言，类结构一旦被定义就很难被改变，想要修改或者扩展就特别困难
		// 虽然可以通过classLoad生成新的字节码，但是这种方式太过复杂与困难


		// MDC

		// defineClass 方法 字节码 传递的是字节码数组

	}



}
