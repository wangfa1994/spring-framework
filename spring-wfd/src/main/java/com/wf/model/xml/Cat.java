package com.wf.model.xml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/19 9:44
 */
public class Cat implements ApplicationContextAware, InitializingBean, SmartInitializingSingleton {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cat(String category) {

		System.out.println("cat的有参构造器");
		this.category = category;
    }


    public Cat() {
		System.out.println("cat的无参构造器");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Cat的 ApplicationContextAware 接口的  setApplicationContext.........");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        category = "这个是我自己写死的";
        System.out.println("Cat的 InitializingBean 接口的 afterPropertiesSet.........");

    }

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("Cat的 SmartInitializingSingleton 接口的afterSingletonsInstantiated 所有的对象都初始化完成之后再来进行执行的方法.........");
	}


	/*@Override
	public String toString() {
		return "Cat{" +
				"category='" + category + '\'' +
				"}=====>"+this.hashCode();
	}*/
}
