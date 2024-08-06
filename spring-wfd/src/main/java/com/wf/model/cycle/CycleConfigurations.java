package com.wf.model.cycle;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.FilterType;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:43
 */
@ComponentScan(basePackages = {"com.wf.model.cycle"},
		excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,
				pattern = "com.wf.model.cycle.three.*"),
				@ComponentScan.Filter(type = FilterType.REGEX,
						pattern = "com.wf.model.cycle.lazyResolve.*")})
public class CycleConfigurations {

}
