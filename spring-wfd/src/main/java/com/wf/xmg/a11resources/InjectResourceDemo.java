package com.wf.xmg.a11resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

// 注入我们的资源相关信息
public class InjectResourceDemo {
	@Value("classpath:/META-INF/a11/default.properties")
	private Resource defaultPropertiesResource;

	@Value("classpath*:/META-INF/a11/*.properties")
	private Resource[] propertiesResources;

	@Value("${user.dir}")
	private String currentProjectRootPath;

	@PostConstruct
	public void init() {
		System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
		System.out.println("================");
		Stream.of(propertiesResources).map(ResourceUtils::getContent).forEach(System.out::println);
		System.out.println("================");
		System.out.println(currentProjectRootPath);
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册当前类作为 Configuration Class
		context.register(InjectResourceDemo.class);
		// 启动 Spring 应用上下文
		context.refresh();
		// 关闭 Spring 应用上下文
		context.close();

	}



	// 为什么可以使用interface去创建方法呢？而且还没有使用default关键字，进行默认
	interface ResourceUtils {

		static String getContent(Resource resource) {
			try {
				return getContent(resource, "UTF-8");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		static String getContent(Resource resource, String encoding) throws IOException {
			EncodedResource encodedResource = new EncodedResource(resource, encoding);
			// 字符输入流
			try (Reader reader = encodedResource.getReader()) {
				return IOUtils.toString(reader);
			}
		}
	}
}
