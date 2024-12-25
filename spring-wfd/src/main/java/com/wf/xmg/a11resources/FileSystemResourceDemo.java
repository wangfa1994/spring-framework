package com.wf.xmg.a11resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;


//01  FileSystemResource  变成带编码的 EncodedResource ，被代理，还是被装饰呢？
// FileSystemResource 和 EncodedResource 属于不同的体系，EncodedResource 只是属于InputStreamSource体系，而FileSystemResource 则继承了AbstractResource 是Resource的真正实现
public class FileSystemResourceDemo {

	public static void main(String[] args) throws IOException {

		String currentJavaFilePath = System.getProperty("user.dir") + "/spring-wfd/src/main/java/com/wf/xmg/a11resources/FileSystemResourceDemo.java";
		File currentJavaFile = new File(currentJavaFilePath);
		// FileSystemResource => WritableResource => Resource
		FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath); // FileSystemResource 里面有一个toPath方法的调用
		EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
		// 字符输入流
		// 字符输入流
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}
	}
}
