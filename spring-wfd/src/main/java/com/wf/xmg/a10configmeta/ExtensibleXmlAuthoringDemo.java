package com.wf.xmg.a10configmeta;

import com.wf.xmg.a10configmeta.dto.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring XML 元素扩展示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class ExtensibleXmlAuthoringDemo {

	public static void main(String[] args) {

		// 创建 IoC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// 记载 XML 资源
		reader.loadBeanDefinitions("META-INF/a10/user-context.xml");
		// 获取 User Bean 对象
		User user = beanFactory.getBean(User.class);
		System.out.println(user);
	}

	/*
	*
	* https://docs.spring.io/spring/reference/core/appendix/xml-custom.html
	* 1. spring.handlers 文件描述 指定我的命名空间使用那个类进行引导解析
	*
	* 2.spring.schemas 是一个映射表，告诉Spring："当XML中引用某个XSD文件时，实际应该去哪里找这个文件"
	*
	* 3.xml文件中 XML schema 规定对应的xml文件中的标题头中
	*  xmlns: 指定对应的命名空间
	*  xsi:schemaLocation :指定我的命名空间和xsd规范文件的名称 ，通过这些指定的名称去spring.schemas文件查找映射的xsd文件，去spring.handlers文件查找解析类
	*
	* 4.xsd文件中
	* xsd:schema 指定命名空间
	*
	*
	*
	* XML Schema（XSD - XML Schema Definition）
	* 是用于定义和验证 XML 文档结构的语言。它是一种 XML-based 的规范，比 DTD（Document Type Definition）更强大
	* 主要作用：定义结构：规定 XML 文档中允许的元素和属性
	* 数据类型：支持丰富的数据类型（字符串、数字、日期、枚举等）
	* 约束验证：定义元素出现的顺序、次数、可选/必选等
	* 命名空间支持：更好的模块化和重用
	*
	* Spring 的 XML 扩展机制并不是扩展 XSD 标准本身，而是扩展了 Spring IoC 容器对自定义 XML 元素的支持。
	*  命名空间处理器（NamespaceHandler
	*  Bean定义解析器（BeanDefinitionParser）
	*
	* 1. Spring启动，解析XML配置文件
	* 2. 遇到自定义命名空间（如：myns:user）
	* 3. 查找 META-INF/spring.handlers，找到NamespaceHandler
	* 4. 调用 NamespaceHandler.init() 注册解析器
	* 5. 找到对应的 BeanDefinitionParser 解析元素
	* 6. 创建 BeanDefinition 并注册到容器
	* 7. 实例化Bean，注入依赖
	* */
}
