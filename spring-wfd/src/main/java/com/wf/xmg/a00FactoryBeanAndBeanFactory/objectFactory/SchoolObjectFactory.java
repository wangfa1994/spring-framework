package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class SchoolObjectFactory implements ObjectFactory<School> {
	@Override
	public School getObject() throws BeansException {
		System.out.println("SchoolObjectFactory 进入");

		// 自己的业务逻辑处理，然后返回不同的对象

		int a = (int)(Math.random()*10);
		if(a<5){
			XiaoXue school = new XiaoXue();
			school.setAddress("小学");
			return school;
		}else{
			GaoZhong school = new GaoZhong();
			school.setAddress("高中");
			return school;
		}
	}
}
