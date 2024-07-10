package com.wf.model.imports;

import com.wf.model.imports.importSelector.SelectImportsBean1;
import com.wf.model.imports.importSelector.SelectImportsBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2023/6/2 18:07
 */
public class MyImportSelector implements ImportSelector {


    public MyImportSelector() {
        System.out.println("MyImportSelector...");
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //用于导入其他类，当类比较多时会比较方便 ，全路径名称
        return new String[]{SelectImportsBean1.class.getName(), "com.wf.model.imports.importSelector.SelectImportsBean2"};
    }


	@Bean
	private String cat(){
		return "cat";
	}
}
