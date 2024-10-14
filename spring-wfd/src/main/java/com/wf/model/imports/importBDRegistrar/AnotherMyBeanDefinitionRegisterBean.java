package com.wf.model.imports.importBDRegistrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class AnotherMyBeanDefinitionRegisterBean implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// AnnotationMetadata 为 导入类的元数据信息 比如这个的导入类为 AnotherBean，可以根据这个导入类的元信息去做我们的业务逻辑
		System.out.println("AnotherMyBeanDefinitionRegisterBean");
	}
}
