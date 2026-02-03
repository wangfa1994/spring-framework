package com.wf.xmg.a12i18n.java;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {

	public static void main(String[] args) {
		// 加载资源包  指定classpath下的全路径,并且我们的资源包名称格式是 baseName_en.properties格式，baseName要一致
		//ResourceBundle bundle = ResourceBundle.getBundle("META-INF/a12/messages", Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle("META-INF/a12/messages", Locale.CHINA);

		// 获取资源
		String greeting = bundle.getString("greeting");
		System.out.println("greeting:"+greeting);

		// 带参数的格式化消息
		MessageFormat formatter = new MessageFormat(bundle.getString("welcome"));
		String formatted = formatter.format(new Object[]{"John"});
		System.out.println(formatted);
	}
}
