package com.wf.model.enable.impoortselect;

import com.wf.model.enable.impoortselect.server.FTPServer;
import com.wf.model.enable.impoortselect.server.HttpServer;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ServerConfigSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// 得到标准Enable加载的类的注解信息
		/*MergedAnnotation<EnableServer> enableServerMergedAnnotation = importingClassMetadata.getAnnotations().get(EnableServer.class);
		ServerType value = enableServerMergedAnnotation.getEnum("type", ServerType.class);*/

		Map<String, Object> annotationAttributes =
				importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
		ServerType type = (ServerType) annotationAttributes.get("type");

		String[] strings = new String[0];
		switch (type){
			case HTTP:
				strings = new String[]{HttpServer.class.getName()};
				break;
			case FTP:
				strings = new String[]{FTPServer.class.getName()};
		}

		return strings;
	}
}
