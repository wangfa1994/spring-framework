/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop;

/** 核心Spring切入点抽象。
 * Core Spring pointcut abstraction.
 * pointCut 只有过滤功能，没有动作功能，只进行过滤匹配我们的类和方法
 * <p>A pointcut is composed of a {@link ClassFilter} and a {@link MethodMatcher}.
 * Both these basic terms and a Pointcut itself can be combined to build up combinations
 * (e.g. through {@link org.springframework.aop.support.ComposablePointcut}).
 *
 * @author Rod Johnson
 * @see ClassFilter
 * @see MethodMatcher
 * @see org.springframework.aop.support.Pointcuts
 * @see org.springframework.aop.support.ClassFilters
 * @see org.springframework.aop.support.MethodMatchers
 */
public interface Pointcut {

	/** 关联类 返回此切入点的ClassFilter。  类级别的过滤
	 * Return the ClassFilter for this pointcut. 这个类是否属于这个过滤器
	 * @return the ClassFilter (never {@code null}) 判断类是不是匹配的
	 */
	ClassFilter getClassFilter();

	/** 关联二  返回这个切入点的MethodMatcher。   方法级别的匹配 判断方法是不是匹配的
	 * Return the MethodMatcher for this pointcut.
	 * @return the MethodMatcher (never {@code null}) 这个方法是否匹配目标的方法
	 */
	MethodMatcher getMethodMatcher();


	/** 总是匹配的规范化切入点实例 方法总会符合对应的切点
	 * Canonical Pointcut instance that always matches.
	 */
	Pointcut TRUE = TruePointcut.INSTANCE;

}
