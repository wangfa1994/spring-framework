package com.wf.xmg.a11resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

//02  FileSystemResourceLoader 资源的类加载器
public class FileSystemResourceLoaderDemo {
	public static void main(String[] args) throws IOException {
		String currentJavaFilePath = System.getProperty("user.dir") + "/spring-wfd/src/main/java/com/wf/xmg/a11resources/FileSystemResourceLoaderDemo.java";
		// 新建一个 FileSystemResourceLoader 对象
		FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
		// FileSystemResource => WritableResource => Resource
		Resource resource = resourceLoader.getResource(currentJavaFilePath);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		// 字符输入流
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}
	}


}
