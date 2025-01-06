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

// 04 注入我们的资源相关信息 利用的是value处理
public class InjectResourceDemo {

	@Value("classpath:/META-INF/a11/default.properties")
	private Resource defaultPropertiesResource;

	@Value("classpath*:/META-INF/a11/*.properties")
	private Resource[] propertiesResources;

	@Value("${user.dir}")
	private String currentProjectRootPath;

	@PostConstruct
	public void init() { // 通过init进行处理，否在的话，我们的变量都要变成static的，因为我们的main方法是static的。
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


	/**
	 * @value 可以直接进行资源加载，这个为什么会能根据一个字符串解析出来对应的Resource呢？这个和java中的PropertyEditor有关联，在类型转换的时候进行处理出来的
	 *
	 */




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
