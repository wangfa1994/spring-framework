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
package com.wf.xmgAop02.a06.a03pointcutapi;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 *  继承抽象类实现我们自己的pointcut
 */
public class MyPointCutApiPointcut extends StaticMethodMatcherPointcut {

    private String methodName;

    private Class targetClass;

    public MyPointCutApiPointcut(String methodName, Class targetClass) {
        this.methodName = methodName;
        this.targetClass = targetClass;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) { // 过滤匹配出我们的哪些方法是可以进行拦截的

		return Objects.equals(methodName, method.getName()) && Objects.equals(this.targetClass,targetClass);
        /*return Objects.equals(methodName, method.getName())
                && this.targetClass.isAssignableFrom(targetClass);*/
    }


}
