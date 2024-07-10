package com.wf.model.ann;

import com.wf.model.imports.ConfigurationsBean;
import com.wf.postprocess.bean.MySmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.*;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/29 16:07
 */
@ComponentScan(basePackages = {"com.wf.model.ann"})
// @ComponentScan(basePackages = {"com.wf.model.ann","com.wf.postprocess.bean"})
@Description("这是好的")
public class AnnConfiguration {

	/*@Bean
	private ConfigurationsBean string(){
		ConfigurationsBean configurationsBean = new ConfigurationsBean();
		configurationsBean.setCb("configurationsBean");
		return configurationsBean;
	}*/



/*    @Bean
	private MySmartInstantiationAwareBeanPostProcessor string(){
		return new MySmartInstantiationAwareBeanPostProcessor();
	}*/
}
