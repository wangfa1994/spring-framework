package com.wf.postprocess.bean.instantiationAwareBeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	public MyInstantiationAwareBeanPostProcessor(){
		System.out.println("MyInstantiationAwareBeanPostProcessor的无参构造器...");
	}


	// 继承父类BeanPostProcessor的两个方法  Initialization 初始化
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor接口的前置初始化postProcessBeforeInitialization..."+beanName);

		return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor接口的后置初始化方法postProcessAfterInitialization..."+beanName);
		return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}




	// 自己扩展的三个方法  Instantiation 实例化
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor接口的前置实例化方法postProcessBeforeInstantiation..."+beanName);

		if(beanClass == Cat.class && beanName.equals("cat")){
			System.out.println("InstantiationAwareBeanPostProcessor 自己返回cat");
			Cat cat = new Cat();
			cat.setName("jiaFei");
			cat.setAge(15);
			return cat;
		}

		return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor接口的后置实例化方法postProcessAfterInstantiation..."+beanName);

		if(bean instanceof Cat && beanName.equals("cat") ){
			System.out.println("InstantiationAwareBeanPostProcessor 自己再加工cat");
			Cat bean1 = (Cat) bean;
			bean1.setAge(16);
		}

		return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor接口的属性赋值方法postProcessProperties..."+beanName);
		return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
	}












	/*//初始化之前进行后置处理，Spring留给我们给这个组件创建对象的回调。
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("MyInstantiationAwareBeanPostProcessor...postProcessBeforeInstantiation=>"+beanClass+"--"+beanName); //if(class.isAssFrom(Cat.class)){return new Dog()}
		return null; //如果我们自己创建了对象返回。Spring则不会帮我们创建对象，用我们自己创建的对象？ 我们创建的这个对象，Spring会保存单实例？还是每次getBean都调到我们这里创建一个新的？
	}
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("MyInstantiationAwareBeanPostProcessor...postProcessAfterInstantiation=>"+bean+"--"+beanName); //提前改变一些Spring不管的bean里面的属性
		return true; //返回false则bean的赋值全部结束
	}  //解析自定义注解进行属性值注入；pvs 封装了所有的属性信息。
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException { //@GuiguValue();redis
		System.out.println("MyInstantiationAwareBeanPostProcessor...postProcessProperties=>"+bean+"--"+beanName);
		return null;
	}
//	public PropertyValues postProcessPropertyValues(
//			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
//		System.out.println("MyInstantiationAwareBeanPostProcessor...postProcessProperties");
//		return pvs;
//	}*/
}

