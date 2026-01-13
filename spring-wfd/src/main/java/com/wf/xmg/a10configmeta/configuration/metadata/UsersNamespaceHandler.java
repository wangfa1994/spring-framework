package com.wf.xmg.a10configmeta.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "users.xsd" {@link NamespaceHandler} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// 将 "user" 元素注册对应的 BeanDefinitionParser 实现
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}