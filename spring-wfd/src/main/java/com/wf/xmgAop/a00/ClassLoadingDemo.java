package com.wf.xmgAop.a00;

public class ClassLoadingDemo {

	public static void main(String[] args) {

		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader);
		// classLoad  APPClassLoad ClassLoaders$AppClassLoader   urlClassLoad

		// 基本类 CLass类 Method类
		//字节码提升 ClassVisitor

		// oop 封装  继承  多态

		// java 属于静态语言，类结构一旦被定义就很难被改变，想要修改或者扩展就特别困难
		// 虽然可以通过classLoad生成新的字节码，但是这种方式太过复杂与困难


	}
}
