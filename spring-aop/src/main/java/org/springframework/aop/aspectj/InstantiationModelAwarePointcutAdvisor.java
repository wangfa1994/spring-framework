/*
 * Copyright 2002-2006 the original author or authors.
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

package org.springframework.aop.aspectj;

import org.springframework.aop.PointcutAdvisor;

/** 接口将由Spring AOP advisor实现，它包装了可能具有延迟初始化策略的AspectJ方面
 * Interface to be implemented by Spring AOP Advisors wrapping AspectJ
 * aspects that may have a lazy initialization strategy. For example,
 * a perThis instantiation model would mean lazy initialization of the advice.
 * 这个实例化模型意味着延迟初始化通知。
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 2.0
 */
public interface InstantiationModelAwarePointcutAdvisor extends PointcutAdvisor {

	/**
	 * Return whether this advisor is lazily initializing its underlying advice.
	 */
	boolean isLazy();

	/**
	 * Return whether this advisor has already instantiated its advice.
	 */
	boolean isAdviceInstantiated();

}
