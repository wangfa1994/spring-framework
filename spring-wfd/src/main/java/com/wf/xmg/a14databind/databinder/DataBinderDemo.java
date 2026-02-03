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
package com.wf.xmg.a14databind.databinder;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class DataBinderDemo {

    public static void main(String[] args) {

		// 通用使用
		// commonMethod();

		// 存在类中不存在的属性
		// conditionPropertyMethod();

		// 嵌套属性
		// nestPropertyMethod();

		// 属性控制行为
		propertyControl();

       /* // 创建空白对象
        User user = new User();

		// 1. 创建 DataBinder
        DataBinder binder = new DataBinder(user, "userObject");

        // 2. 创建 PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "小马哥");

        // a. PropertyValues 存在 User 中不存在属性值
        // DataBinder 特性一 : 忽略未知的属性
        source.put("age", 18);

        // b. PropertyValues 存在一个嵌套属性，比如 company.name
        // DataBinder 特性二：支持嵌套属性
        // Company company = new Company();
        // company.setName("geekbang");
        // user.setCompany(compay)

//        source.put("company", new Company());
        source.put("company.name", "geekbang");

        PropertyValues propertyValues = new MutablePropertyValues(source);

        // 1. 调整 IgnoreUnknownFields true（默认） -> false（抛出异常，age 字段不存在于 User 类）
        // binder.setIgnoreUnknownFields(false);

        // 2. 调整自动增加嵌套路径 true（默认） —> false
        binder.setAutoGrowNestedPaths(false);

        // 3. 调整 ignoreInvalidFields false(默认） -> true（默认情况调整不变化，需要调增 AutoGrowNestedPaths 为 false）
        binder.setIgnoreInvalidFields(true);

        binder.setRequiredFields("id", "name", "city");

        binder.bind(propertyValues);

        // 3. 输出 User 内容
        System.out.println(user);

        // 4. 获取绑定结果（结果包含错误文案 code，不会抛出异常）
        BindingResult result = binder.getBindingResult();
        System.out.println(result);*/
    }

	private static void propertyControl() {
		// 创建空白对象
		User user = new User();

		// 1. 创建 DataBinder
		DataBinder binder = new DataBinder(user, "userObject");


		// 2. 构建属性值
		Map<String, Object> propertySource = new HashMap<>();
		propertySource.put("id", 1);

		//binder.setIgnoreUnknownFields(false); // 是否忽略未知的字段
		propertySource.put("source","Class"); // 添加不存在的属性 不忽略的话，类一定要存在对应的属性


		//binder.setIgnoreInvalidFields(false);


		// 3. 创建 PropertyValues
		MutablePropertyValues values = new MutablePropertyValues();
		values.addPropertyValues(propertySource);
		// 4. 数据绑定
		binder.bind(values);

		BindingResult bindingResult = binder.getBindingResult();
		Object target1 = bindingResult.getTarget();
		System.out.println("bindingResult:"+bindingResult);

		Object target = binder.getTarget();
		String objectName = binder.getObjectName();
		System.out.println("objectName:"+objectName+"=====target:"+target);
		System.out.println("直接輸出"+user);
	}

	private static void nestPropertyMethod() {

		Teacher teacher = new Teacher();
		DataBinder dataBinder = new DataBinder(teacher,"teacher");

		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

		mutablePropertyValues.addPropertyValue("name","teacherName");

		mutablePropertyValues.addPropertyValue("user.name","userName"); //嵌套属性设置,框架帮我们创建内部属性

		dataBinder.bind(mutablePropertyValues);
		Teacher teacher1 = (Teacher)dataBinder.getTarget();
		System.out.println("嵌套属性"+teacher1+"==="+teacher1.getUser().getName());
	}

	private static void conditionPropertyMethod() {
		// 创建空白对象
		User user = new User();

		// 1. 创建 DataBinder
		DataBinder binder = new DataBinder(user, "userObject");

		// 2. 构建属性值
		Map<String, Object> propertySource = new HashMap<>();
		propertySource.put("id", 1);
		propertySource.put("name", "小马哥");

		propertySource.put("source","Class"); // 添加不存在的属性 默认忽略

		// 3. 创建 PropertyValues
		MutablePropertyValues values = new MutablePropertyValues();
		values.addPropertyValues(propertySource);

		// 数据绑定
		binder.bind(values);

		System.out.println(binder.getTarget());
	}

	private static void commonMethod() {
		// 创建空白对象
		User user = new User();

		// 1. 创建 DataBinder
		DataBinder binder = new DataBinder(user, "userObject");

		// 2. 构建属性值
		Map<String, Object> propertySource = new HashMap<>();
		propertySource.put("id", 1);
		propertySource.put("name", "小马哥");

		// 3. 创建 PropertyValues
		MutablePropertyValues values = new MutablePropertyValues();
		values.addPropertyValues(propertySource);
		// 4. 数据绑定
		binder.bind(values);
		Object target = binder.getTarget();
		String objectName = binder.getObjectName();
		System.out.println("objectName:"+objectName+"=====target:"+target);
		System.out.println("直接輸出"+user);

	}

}
