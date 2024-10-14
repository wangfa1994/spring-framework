package com.wf.model.imports;

/**
 * @Desc : @Import 的处理逻辑
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextImportTest {

    public static void main(String[] args) {




	}

/**
 * @Import
 * 通过Import可以导入三种类型的类，一种是实现了 ImportSelector，一种是实现了 ImportBeanDefinitionRegistrar，最后就是会当成普通的配置类进行解析再处理
 * 导入ImportSelector的可以进行多个类的导入，
 * 导入ImportBeanDefinitionRegistrar的表示进行自定义导入beanDefinition，引入的元信息是导入类的元信息
 *
 * 导入的bean的beanName在容器中是全类路径的名称

 *
 */



}
