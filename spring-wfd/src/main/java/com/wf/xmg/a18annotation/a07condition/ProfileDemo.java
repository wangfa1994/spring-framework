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
package com.wf.xmg.a18annotation.a07condition;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 */
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);

        // 获取 Environment 对象（可配置的）
        ConfigurableEnvironment environment = context.getEnvironment();
        // 默认 profiles = [ "odd" ] （兜底 profiles)
        environment.setDefaultProfiles("odd");
        // 增加活跃 profiles
        environment.addActiveProfile("even");

        // --spring.profiles.active = even
        // -Dspring.profiles.active=even

        // 启动 Spring 应用上下文
        context.refresh();

		String[] activeProfiles = environment.getActiveProfiles();
		System.out.println(Arrays.toString(activeProfiles));

        Integer number = context.getBean("number", Integer.class);

        System.out.println(number);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Bean(name = "number")
    @Profile("odd") // 奇数   使用ProfileCondition进行实现逻辑，和我们自己实现的异曲同工
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
    //@Profile("even") // 偶数
    @Conditional(EvenProfileCondition.class) // 这个可以是我们自定义的形式，和Profile具有相同的功能
    public Integer even() {
        return 2;
    }

}
