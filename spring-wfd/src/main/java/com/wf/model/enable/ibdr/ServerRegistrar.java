package com.wf.model.enable.ibdr;

import com.wf.model.enable.ibdr.server.FTPServer;
import com.wf.model.enable.ibdr.server.HttpServer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ServerRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// 得到标准Enable加载的类的注解信息
		/*MergedAnnotation<EnableServer> enableServerMergedAnnotation = importingClassMetadata.getAnnotations().get(EnableServer.class);
		ServerType value = enableServerMergedAnnotation.getEnum("type", ServerType.class);*/

		Map<String, Object> annotationAttributes =
				importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
		ServerType type = (ServerType) annotationAttributes.get("type");

		String[] strings = new String[0];
		switch (type){
			case HTTP:
				GenericBeanDefinition httpBeanDefinition = new GenericBeanDefinition();
				httpBeanDefinition.setBeanClass(HttpServer.class);
				registry.registerBeanDefinition("httpServer",httpBeanDefinition);
				break;
			case FTP:
				GenericBeanDefinition ftpBeanDefinition = new GenericBeanDefinition();
				ftpBeanDefinition.setBeanClass(FTPServer.class);
				registry.registerBeanDefinition("ftpServer",ftpBeanDefinition);
		}

	}
}
