package com.wf.model.ann;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/19 9:44
 */
@Component
public class Cat implements ApplicationContextAware, InitializingBean {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cat(String category) {
        this.category = category;
    }


    public Cat() {
		System.out.println("cat的无参构造器......");
        System.out.println("无参构造器-----cat");
    }
    /**
      * @Desc:
      * @Author: Mr.WangF
      * @Date: 2023/4/7 16:21
     *
     *
     * 3.11 finishBeanFactoryInitialization(beanFactory);
     *    3.11.1 判断上下文中是否需要ConversionService，如果需要则进行getBean()实例化
     *    3.11.2 注册对应的StringValueResolver 给工厂属性embeddedValueResolvers设置StringValueResolver字符串解析器，主要用在
     *    3.11.3 通过 beanFactory.getBeanNamesForType获取LoadTimeWeaverAware.class的beanName.如果存在的话则会进行实例化，此时系统中不存在对应的beanNames;
     *    3.11.4 允许缓存所有bean定义元数据，并且进行属性设置 configurationFrozen=true    frozenBeanDefinitionNames = 复制系统中所有的beanNames
     *    3.11.5 beanFactory.preInstantiateSingletons() 开始进行实例化我们的bean
     *       3.11.5.1 从属性beanDefinitionNames中获取所有的beanNames,然后循环进行处理，此时只会存在系统默认的四个加个自己的
     *                  [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,
     *                  org.springframework.context.annotation.internalAutowiredAnnotationProcessor,
     *                  org.springframework.context.event.internalEventListenerProcessor,
     *                  org.springframework.context.event.internalEventListenerFactory]
     *       3.11.5.2 调用 getMergedLocalBeanDefinition(beanName) 进行合并我们的beanName，此时有些bean已经在前置进行过合并，可以直接从属性mergedBeanDefinitions中获取出来
     *       3.11.5.3 当bean不是抽象的，是单例的，而且不是懒加载的，则进行调用getBean()创建bean
     *       3.11.5.4 doGetBean()
     *         3.11.5.4.1 计算出bean的真正名称，先进行FactoryBean去除前缀，然后处理别名解析出规范的名称
     *         3.11.5.4.2 调用getSingleton()通过一二三级缓存确定是否已经存在对应的bean实例对象
     *            3.11.5.4.2.1 先从实例对象池中属性singletonObjects中获取对象实例，
     *                 3.11.5.4.2.1.1 在没有得到的情况下，并且从属性singletonsCurrentlyInCreation属性中发现当前对象正在创建过程中，则会进行earlySingletonObjects属性中获取当前对象是否存在
     *         3.11.5.4.3 如果此时的对象实例已经存在了，则会进行getObjectForBeanInstance()返回我们的实例对象，会从属性中currentlyCreatedBean
     *            3.11.5.4.3.1 如果不存在则进行对象的创建,首先 从属性prototypesCurrentlyInCreation中判断是否正在创建中
     *            3.11.5.4.3.2 获取当前的父工厂，试图从父工厂中获取对应的组件
     *            3.11.5.4.3.3 如果不能从父工厂进行获取的时候，则先进行当前bean标记为创建，会将对应的beanName放入到我们的alreadyCreated属性中，并且设置我们的mergedBeanDefinitions属性中的对象的属性stale重新合并标记为TRUE，并且从属性mergedBeanDefinitionHolders移除
     *            3.11.5.4.3.4 调用getMergedLocalBeanDefinition()进行合并beanDefinition,由于上一步将我们的stal属性标志位重新合并了，此时会进行再次合并
     *
     *
     *         3.11.5.4.4 获取到对应的对象实例之后，则会再进行adaptBeanInstance()转换，将我们的对象转换成我们所需要的对象
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
    */


     @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("cat的 ApplicationContextAware接口的  setApplicationContext.........");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        category = "这个是我自己写死的";
        System.out.println("cat的 InitializingBean接口的  afterPropertiesSet.........");

    }

}
