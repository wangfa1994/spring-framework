package com.wf.xmg.a11resources;

public class Main {

	// URI(Uniform Resource Identifier):统一资源标识符，用于唯一标识一个资源。它可以进一步分为两种类型：URL 和 URN
	// URL (Uniform Resource Locator): 统一资源定位符，是 URI 的一种形式，不仅标识了资源的位置，还提供了访问该资源的方法，通常包括协议（如 http、https）、主机名、端口号（可选）、路径、查询参数
	// classLoad 将class文件变成我们的class对象，包括加载 链接 初始化三个阶段

	public static void main(String[] args) {
		/**
		 * java 标准资源定位
		 * 面向资源的文件系统 ， artifact(jar,war,ear文件) 远程资源http ftp
		 * File类  ClassLoad类 URL、URI类
		 * URLConnection
		 * URL协议扩展：URLStreamHandler 和 URLStreamHandlerFactory ()
		 *
		 * sun.net.www.protocol.file.Handler
		 * sun.net.www.protocol.ftp.Handler
		 * sun.net.www.protocol.http.Handler 等
		 *
		 *
		 * spring的资源接口
		 * 输入流：org.springframework.core.io.InputStreamSource ：只定义了一个获取InputStream流方法的接口规范，这个是Source，并不是InputStreamResource
		 * 只读资源：org.springframework.core.io.Resource ： 继承了InputStreamSource,添加了针对资源的一些判断
		 * 可写资源：org.springframework.core.io.WritableResource
		 * 编码资源：org.springframework.core.io.support.EncodedResource 带编码的资源文件
		 * 上下文资源：org.springframework.core.io.ContextResource
		 *
		 * spring内建的Resource实现
		 * Bean定义：BeanDefinitionResource
		 * 数组： org.springframework.core.io.ByteArrayResource
		 * 类路径：classpath:/ 形式 org.springframework.core.io.ClassPathResource
		 * 文件系统： file:/ 形式 org.springframework.core.io.FileSystemResource
		 * URL :url支持的协议类型  org.springframework.core.io.UrlResource
		 * ServletContext: org.springframework.web.context.support.ServletContextResource
		 *
		 *
		 *
		 * spring的资源接口扩展
		 * WritableResource(可写资源接口)
		 * 		--FileSystemResource FileUrlResource PathResource
		 * EncodeResource(编码资源接口)
		 *
		 * Spring 的 资源加载器
		 * Resource的加载器
		 * 	ResourceLoader
		 * 		--DefaultResourceLoader --FileSystemResourceLoader ClassRelativeResourceLoader AbstractApplicationContext
		 *统配路径资源加载器
		 * 	ResourceLoader
		 * 		--ResourcePatternResolver --PathMatchingResourcePatternResolver
		 * 	路径匹配器
		 * 		PathMatcher --AntPathMatcher
		 * 通配路径资源扩展
		 * 	PathMatcher  --  PathMatchingResourcePatternResolver#setPathMatcher
		 *
		 *
		 * 	依赖注入Spring 资源
		 * 	@Value
		 * 	依赖注入Spring 资源加载器
		 * 	1. 实现ResourceLoaderAware接口
		 * 	2. @Autowire注入ResourceLoader
		 * 	3. 注入ApplicationContext作为ResourceLoader
		 *
		 *
		 *
		 * 	spring配置资源有哪些类型
		 * 	1. xml资源类型 ：普通的BeanDefinition xml资源配置   Spring的schema资源配置
		 * 	2. properties资源 ： 普通的properties资源，Spring handler实现类映射文件 ，Spring schema资源映射文件
		 * 	3.yaml资源： 配置资源
		 *
		 *
		 */

	}
}
