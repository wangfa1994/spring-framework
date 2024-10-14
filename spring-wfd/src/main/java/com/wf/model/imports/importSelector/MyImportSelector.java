package com.wf.model.imports.importSelector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2023/6/2 18:07
 */
public class MyImportSelector implements ImportSelector {


    public MyImportSelector() {
        System.out.println("MyImportSelector构造器...");
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //用于导入其他类，当类比较多时会比较方便 ，全路径名称
        return new String[]{SelectImportsBean1.class.getName(), "com.wf.model.imports.importSelector.SelectImportsBean2"};
    }


	// cat是无法注入进去的
	@Bean
	private String cat(){
		return "cat";
	}
}
