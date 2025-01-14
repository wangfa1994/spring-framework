package com.wf.model.createBean;

import com.wf.model.cycle.CycleConfigurations;
import com.wf.model.cycle.Husband;
import com.wf.model.cycle.Wife;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextCreateBeanTest {

    public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(CreateBeanConfiguration.class);

		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat);




    }

/** 创建bean流程
 * 先判断是否被InstantiationAwareBeanPostProcessor 后置处理器进行了拦截，如果拦截,创建完对象后会执行BeanPostProcessors的postProcessAfterInitialization方法，然后返回(代理是在这里产生的，所以要执行下吧)。
 * 没有被拦截的情况下先确定构造器，
 * 进行创建对象，创建完对象之后进行beanDefinition合并，再进行属性填充，然后再进行初始化
 *
 *
 *
 *
 *
 *
 ** 在创建bean过程中 Instantiation 实例化 和  Initialization 初始化
 * BeanPostProcessor一共被分为下列大类
 * 	final List<InstantiationAwareBeanPostProcessor> instantiationAware = new ArrayList<>();
 * 	final List<SmartInstantiationAwareBeanPostProcessor> smartInstantiationAware = new ArrayList<>();
 * 	final List<DestructionAwareBeanPostProcessor> destructionAware = new ArrayList<>();
 * 	final List<MergedBeanDefinitionPostProcessor> mergedDefinition = new ArrayList<>();
 *
 * BeanPostProcessor
 * 		postProcessBeforeInitialization 在初始化方法的时候进行调用,
 * 		postProcessAfterInitialization 在初始化方法的时候进行调用 ，主要是属性赋值完之后，我们可以再次进行对属性自定义的修改和变更，如果我们先进行初始化的操作，则有可能在赋值的时候再次被默认掉。
 * InstantiationAwareBeanPostProcessor(ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor,CommonAnnotationBeanPostProcessor,AutoWiredAnnotationBeanPostProcessor)
 * 		postProcessBeforeInstantiation  在创建对象之前进行调用
 * 		postProcessAfterInstantiation  在属性赋值方法的时候进行调用
 * 		postProcessProperties		在属性赋值方法的时候进行调用
 * 		postProcessPropertyValues   在属性赋值方法的时候进行调用
 *
 * SmartInstantiationAwareBeanPostProcessor(AutoWiredAnnotationBeanPostProcessor)
 * 		predictBeanType       在创建对象之前进行调用，确定是否有指定构造器
 * 		determineCandidateConstructors
 * 		getEarlyBeanReference
 *
 * MergedBeanDefinitionPostProcessor(CommonAnnotationBeanPostProcessor,AutowiredAnnotationBeanPostProcessor,ApplicationListenerDetector)
 * 	postProcessMergedBeanDefinition 用作创建对象实例之后调用，进行beanDefinition的合并，主要处理相关的类与类之间的组合关系放入到beanDefinition中
 *
 *
 *
 *
 ** createBean流程
 *A- resolveBeforeInstantiation(先判断是否被自定义创建bean)
 *  1.在没有创建对象之前先会第一个进行执行的处理器instantiationAware(InstantiationAwareBeanPostProcessor)
 * 		主要执行InstantiationAwareBeanPostProcessor自身接口的postProcessBeforeInstantiation，判断是否被拦截生成对象，如果生成对象，则进行 BeanPostProcessor的 postProcessAfterInitialization调用处理
 *B- doCreateBean(然后创建bean)
 *  1. createBeanInstance方法,开始判断是否通过回调，或者工厂方法进行了自定义的对象创建。如果没有的话，就判断是否指定了构造器，
 * 		从smartInstantiationAware(SmartInstantiationAwareBeanPostProcessor)中执行对应的determineCandidateConstructors方法，用来获取是否存在指定的构造器
 * 		如果存在的话，则通过制定的构造器进行实例对象的创建autowireConstructor()，不存在的话，则通过默认的构造器进行创建对象，并包装成对应的BeanWrapperImpl
 *
 *  2.applyMergedBeanDefinitionPostProcessors方法，bean对象创建之后，开始利用后置处理器MergedBeanDefinitionPostProcessor的postProcessMergedBeanDefinition进行执行BeanDefinition的合并，
 *   最初的BeanDefinition主要是包括自身的信息范围，是否懒加载等，现在开始处理类与类组合上的相关属性(autowire,value等)。
 *
 *  3.populateBean方法阶段进行再次执行instantiationAware.postProcessAfterInstantiation再次判断是否进行真正的执行回调，如果返回了false，则不会再进行后续的instantiationBeanPostProcessor的执行，所以他的顺序还是比较重要的
 *		 循环体中 执行 instantiationAware 对应的 postProcessProperties(5.1新增) 和 postProcessPropertyValues(5.1之后弃用了) 一个类中的一起执行
 *
 *  4.initializeBean方法阶段 先判断当前类自身是否是Aware接口，执行Aware(BeanNameAware/BeanClassLoaderAware/BeanFactoryAware)回调逻辑 只判断这三个
 * 		 再执行BeanPostProcessor的真正接口所有的 postProcessBeforeInitialization方法
 * 		 然后判断自身是否是InitializingBean接口 ，执行afterProperties逻辑,
 * 		 然后再判断是否存在初始化方法，进行执行初始化方法initMethod方法执行。
 * 		 最后执行BeanPostProcessor的真正接口所有的 postProcessAfterInitialization 代理对象在这里产生的
 *
 *
 *
 *
 * MergedBeanDefinitionPostProcessor 为什么在创建完对象后，要进行BeanDefinition的处理？创建完对象之后bd不会再需要了吧？
 * 这是一个非常好的问题！乍一看，在对象创建完成后再处理`BeanDefinition`确实似乎不合逻辑，因为Bean实例已经存在了，为什么还要修改它的定义呢？但实际上，这样设计是有其深思熟虑的原因的。
 *
 * 首先，让我们明确`MergedBeanDefinitionPostProcessor`的调用时机：
 * 1. Bean实例化（Instantiation）：创建Bean的实例，但还未填充属性
 * 2. 调用`postProcessMergedBeanDefinition`：处理合并后的Bean定义
 * 3. 属性填充（Populate Properties）：设置Bean的属性值
 * 4. 初始化（Initialization）：调用初始化方法
 *
 * 关键点在于，`postProcessMergedBeanDefinition`方法是在Bean实例化之后，但在属性填充和初始化之前调用的。这个时机非常重要，原因如下：
 *
 * 1. 注解元数据的内省和缓存
 *    - 主要目的：优化性能
 *    - 解释：Spring中大量使用了注解，如`@Autowired`、`@Value`、`@PostConstruct`等。这些注解需要通过反射来处理，而反射操作是相对昂贵的。
 *    - 做法：在这个阶段，Spring会扫描Bean的类定义，找出所有需要处理的注解，并将这些信息缓存起来。
 *    - 好处：后续在属性填充和初始化阶段，Spring可以直接使用这些缓存的元数据，而不需要重复进行反射操作，从而显著提高性能。
 *    - 例子：`AutowiredAnnotationBeanPostProcessor`和`CommonAnnotationBeanPostProcessor`都利用这个阶段来处理和缓存注解信息。
 *
 * 2. 合并父类定义
 *    - 主要目的：处理Bean定义的继承
 *    - 解释：在Spring中，Bean定义是可以继承的。子Bean定义可以从父定义中继承作用域、构造函数参数值、属性值等。
 *    - 做法：在实例化子Bean后，需要将其定义与父定义合并，以确保所有必要的信息都被正确应用。
 *    - 重要性：虽然实例已创建，但属性尚未填充。合并后的定义确保在下一步的属性填充中使用正确的值。
 *
 * 3. 修改Bean定义以影响后续处理
 *    - 主要目的：根据运行时信息调整Bean的行为
 *    - 解释：有时，我们需要根据实例化后但初始化前的状态来决定如何进一步处理Bean。
 *    - 可能的操作：
 *      - 动态改变属性值
 *      - 添加或删除初始化方法
 *      - 修改Bean的作用域
 *    - 场景示例：根据实例化后的Bean的某些特征（如类型、注解等），决定是否需要为其创建代理，或者调整其生命周期方法。
 *
 * 4. 准备循环依赖解决
 *    - 主要目的：帮助解决循环依赖问题
 *    - 解释：Spring通过三级缓存来解决循环依赖。在这个过程中，需要尽早地识别Bean的一些特征。
 *    - 做法：通过分析Bean定义和实例，确定是否需要提前创建代理（如AOP代理）以解决循环依赖。
 *    - 重要性：这个阶段的信息帮助Spring决定是否需要在`SmartInstantiationAwareBeanPostProcessor.getEarlyBeanReference`中创建早期代理。
 *
 * 5. 支持自定义的后处理逻辑
 *    - 主要目的：提供扩展点
 *    - 解释：Spring的设计哲学之一是在各个生命周期阶段提供足够的扩展点。
 *    - 潜在用途：开发者可以实现自己的`MergedBeanDefinitionPostProcessor`来在这个独特的时间点执行自定义逻辑，例如：
 *      - 根据实例化后的Bean状态动态调整其定义
 *      - 收集元数据用于后续的自定义处理
 *      - 与其他Spring机制集成，需要在属性填充前进行准备工作
 *
 * 总结：
 * 虽然Bean已经实例化，但在属性填充和初始化之前，仍然有大量工作要做。`MergedBeanDefinitionPostProcessor`提供了一个独特的时间点，用于：
 * 1. 优化性能（通过缓存注解元数据）
 * 2. 确保正确性（通过合并Bean定义）
 * 3. 提供灵活性（允许根据运行时信息调整Bean行为）
 * 4. 支持核心功能（如解决循环依赖）
 * 5. 提供扩展点（用于自定义处理）
 *
 * 这体现了Spring框架的精心设计：即使在看似不需要的地方，也为不同的需求场景提供了适当的钩子，使框架既强大又灵活。
 *
 *
 *
 */




}