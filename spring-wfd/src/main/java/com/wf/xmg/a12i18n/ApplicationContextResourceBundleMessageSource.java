package com.wf.xmg.a12i18n;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class ApplicationContextResourceBundleMessageSource {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextResourceBundleMessageSource.class);
		// 沒有提供特殊语言的格式，会采用默认的 baseName_local.properties
		String message = context.getMessage("message", null, "Default", Locale.CHINA);
		System.out.println(message);
	}

	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();

		source.setBasenames("META-INF/a12/format","META-INF/a12/exceptions","META-INF/a12/windows");

		return source;
	}
}
