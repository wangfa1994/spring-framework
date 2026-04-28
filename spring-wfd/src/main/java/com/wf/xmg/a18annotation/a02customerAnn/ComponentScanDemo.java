/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wf.xmg.a18annotation.a02customerAnn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Arrays;

// 派生 206
@ComponentScan(value = "com.wf.xmg.a18annotation.a02customerAnn") // 指定 Class-Path(s)
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ComponentScanDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
		System.out.println(context.getBean(UserDemo.class));
        context.close();



		// 注解元信息的处理
		AnnotationMetadata introspect = AnnotationMetadata.introspect(UserDemo.class);
		System.out.println(introspect.getAnnotationTypes());




	}

	/**
	 *  通过 ConfigurationClassPostProcessor 中 ConfigurationClassParse 进行解析
	 *  能够被识别是因为 Component 注解
	 *
	 *  Component 的派生性，可以无限派生， 把 Component 当作元注解来进行处理的
	 *
	 *
	 */
}
