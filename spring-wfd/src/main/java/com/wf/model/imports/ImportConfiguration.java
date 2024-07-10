package com.wf.model.imports;

import org.springframework.context.annotation.*;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/29 16:07
 */
@ComponentScan(basePackages = {"com.wf.model.imports"})
@Import({MyImportSelector.class}) // 通过Import可以导入三种类型的类，一种是实现了 ImportSelector，一种是实现了 ImportBeanDefinitionRegistrar，最后就是会当成普通的配置类进行解析再处理
//@Import({ImportBean.class}) //导入普通类的时候，作为配置类，在容器中位类的全限定名称
public class ImportConfiguration {

	//导入ImportSelector的可以进行多个类的导入，
	// 当做普通配置类的时候不会进行导入
}
