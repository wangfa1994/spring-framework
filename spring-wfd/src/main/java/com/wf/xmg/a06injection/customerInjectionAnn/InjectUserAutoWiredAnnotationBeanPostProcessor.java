package com.wf.xmg.a06injection.customerInjectionAnn;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;


public class InjectUserAutoWiredAnnotationBeanPostProcessor extends AutowiredAnnotationBeanPostProcessor {

	public InjectUserAutoWiredAnnotationBeanPostProcessor() {
		setAutowiredAnnotationType(InjectUser.class);
	}
}
