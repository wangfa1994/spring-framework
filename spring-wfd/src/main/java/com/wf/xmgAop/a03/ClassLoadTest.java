package com.wf.xmgAop.a03;

public class ClassLoadTest {

	public static void main(String[] args) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(classLoader);

		ClassLoader parentClassLoader = classLoader;
		while (true) {
			parentClassLoader = parentClassLoader.getParent();
			if (parentClassLoader == null) { // Bootstrap ClassLoader
				break;
			}
			System.out.println(parentClassLoader);
		}
	}
}
