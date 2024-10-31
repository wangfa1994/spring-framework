package com.wf.xmgAop.a00;

import java.io.*;

public class CustomClassLoadingDemo  extends ClassLoader {

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = loadClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}


	private byte[] loadClassData(String className) {
		String path ="E:\\projectwf\\spring\\spring-framework\\spring-wfd\\src\\main\\java\\com\\wf\\xmgAop\\a00\\StudentClassDemo.class";
		// String path = "classPath" + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		try (InputStream is = new FileInputStream(path);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		// 指定 .class 文件的路径

		// 创建自定义类加载器实例
		CustomClassLoadingDemo customClassLoader = new CustomClassLoadingDemo();

		try {
			// 加载指定的类
			Class<?> clazz = customClassLoader.loadClass("com.wf.xmgAop.a00.StudentClassDemo");

			// 打印加载的类名
			System.out.println("Class loaded: " + clazz.getName());

			// 可以进一步实例化对象或调用方法
			Object instance = clazz.getDeclaredConstructor().newInstance();
			System.out.println("Instance created: " + instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
