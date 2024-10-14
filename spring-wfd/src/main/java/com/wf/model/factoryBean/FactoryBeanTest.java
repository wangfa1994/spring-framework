package com.wf.model.factoryBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;

/**
* @Desc : FactoryBean 的逻辑分析
* @Author : Mr.WangF
**/
@ComponentScan(basePackages = "com.wf.model.factoryBean")
public class FactoryBeanTest {

	public static void main(String[] args) throws Exception {

		//beanFactoryCase();
		applicationContextCase();
	}


	private static void applicationContextCase() throws Exception {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanTest.class);

		AutoStudentFactoryDto bean = applicationContext.getBean(AutoStudentFactoryDto.class);

		System.out.println(bean);

		/**
		 * 被FactoryBean 管理的bean 是否可以被依赖注入？
		 *
		 * 当进行依赖处理的时候，DefaultListableBeanFactory的resolveDependency方法会进行依赖的处理，
		 * 然后会遍历所有存在的beanName,进行一个一个的处理，与我们要依赖的对象类进行匹配是否所需要的beanName,
		 * 此时的容器中虽然没有我们的FactorBean关联对象的相关beanName以及BeanDefinition信息，但是存在我们的
		 * FactoryBean相关信息，在DefaultListableBeanFactory中的doGetBeanNamesForType()和isTypeMatch()方法中
		 * 会进行FactoryBean的处理，得到FactoryBean关联的对象，然后就能匹配到我们依赖对象所关联的beanName，然后就会
		 * 利用我们的getBean进行获取对象，此时传进去的是FactoryBean的名称，然后得到我们的FactoryBean对象，最后再通过
		 * getObjectForBeanInstance 返回到我们的真正的要依赖的对象
		 *
		 *
		 */


	}


	/**
	 *  放到容器中的对象
	 */
	private static void beanFactoryCase() throws Exception {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(defaultListableBeanFactory);

		reader.register(StudentFactoryBean.class);

		// 标记&符号表示要找到的是StudentFactoryBean
		/*StudentFactoryBean studentFactoryBean = defaultListableBeanFactory.getBean("&studentFactoryBean", StudentFactoryBean.class);
		System.out.println(studentFactoryBean);

		// 没有标记&符号表示要找到的是工厂Bean创建出来的对象
		Student student1 = defaultListableBeanFactory.getBean("studentFactoryBean", Student.class);
		System.out.println("student1 ："+student1);

		Student student2 = defaultListableBeanFactory.getBean("studentFactoryBean", Student.class);
		System.out.println("student2 ："+student2);

		System.out.println("多次获取是否相同："+(student1 == student2));*/

		Student student3 = defaultListableBeanFactory.getBean(Student.class);
		System.out.println("student3 ："+student3);


		// 我们只向我们的容器中注册了一个StudentFactoryBean，此时容器中的BeanDefinition只存在一个studentFactoryBean，为什么可以依赖查找得到Student对象呢？
		/** 我们的容器中，只有一个StudentFactoryBean的BeanDefinition，但是我们要得到本体对象(studentFactoryBean)和创建对象(Student),就会利用FactoryBean接口
		 *  容器会管理我们的FactoryBean,它具有bean的声明周期，然后我们需要想办法从FactoryBean中获得到我们的实体对象，我们容器中有且只有StudentFactoryBean，容器中不会存在实体对象的实例。
		 * 容器中没有这个实体对象，那他可以被依赖注入吗？
		 *
		 *
		 * 在getBean的时候，我们首先会处理我们的BeanName,会将我们以&符号开头的beanName进行循环处理，多个&也都会处理掉，得到我们真正的beanName;
		 * 然后开始进行我们实例的处理，此时需要注意的是，此时创建的对象都是我们的工厂Bean,因为我们的工厂Bean才有对应的BeanDefinition,
		 * 创建出来之后，会通过我们的.AbstractBeanFactory#getObjectForBeanInstance方法来处理FactoryBean的逻辑，根据我们获得对象传递进去的是否带&符号，来确定我们是要工厂bean,还是工厂产生的对象。
		 * 如果我们想要的是工厂Bean产生的对象，在得到相关对象之后，spring还会把这个对象进行缓存，等下次用的时候，会直接得到。

		 *
		 * 现在有两种情况，一种是我们先获得创建我们的工厂Bean，再进行获得我们的工厂要创建的对象。
		 * 此时容器在获得我们的工厂Bean的时候，会将我们的工厂Bean的&符号移除得到真正的beanName，然后就是正常的创建bean实例的流程，并且将我们的工厂bean对象放到容器中。
		 * 然后我们再获得我们的工厂Bean中的实体对象的时候，在经过BeanName的处理此时得到的其实是工厂Bean的名称，由于我们的工厂Bean已经创建了并在容器中了，
		 * 所以此时会直接进入到我们的getObjectForBeanInstance方法中进行FactoryBean的逻辑中处理返回我们的实体对象。
		 *
		 * 第二种情况，就是我们直接得到我们的工厂要创建的对象，此时没有了我们的工厂Bean实例。
		 * 在创建的时候，此时也是会进行BeanName的处理，此时的BeanName就是我们的工厂Bean的名称，然后会直接创建我们的工厂Bean实例，此时我们想要的是我们的实体对象，但是怎么给我创建的是工厂Bean呢？
		 * 然后再创建完成之后，在结束的时候也会调用我们的getObjectForBeanInstance方法，此时就又进入了FactoryBean的逻辑中了，这里会进行处理我们真正想要的对象。至此结束
		 *
		 *
		 *
		 * 主要逻辑 在 AbstractBeanFactory#doGetBean() 配合 AbstractBeanFactory#getObjectForBeanInstance() 完成对象的处理
		 *
		 *
		 * 在 Student student3 = defaultListableBeanFactory.getBean(Student.class); 我们可以得到对应的Student对象， 容器中并没有Student实例对象，
		 * 他是怎么转换到我们的FactoryBean上，并且得到我们的Student上的呢？ 这里会有一个根据class获得到对应的beanName的逻辑，最终还是会得到我们的
		 *
		 *
		 *
		 */

	}
}
