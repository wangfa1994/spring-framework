package com.wf.model.jdbc.tarnsactiond.xml;

public class XmlTransactionalTest {
	/* spring的文档中这个是做什么的，为什么在介绍代理的时候突然提到了这个，我理解这个是和事务相关的吧：https://docs.spring.io/spring-framework/reference/core/aop-api/concise-proxy.html
	*
	*
	* 这个页面介绍的是 Spring早期版本中一种优化XML配置的方式，目的是让声明式事务管理（或其它AOP代理）的XML配置变得更简洁、避免重复
	* 在一个应用中，你有十个Service类都需要配置事务代理。如果每个Service都单独写一个完整的 <bean> 配置，代码会非常冗长和重复。
	* 这个页面提供的方案，就是创建一个事务代理的“模板”，然后让其他具体的Service Bean继承这个模板，这样公共的配置（比如事务管理器、默认事务规则）就只需要写一次
	*
	* 1.定义一个抽象模板：创建一个 abstract="true" 的父Bean（如文档中的 txProxyTemplate）。它不会被实际创建，只是作为模板。
	* 2.配置公共属性：在模板里配置好 TransactionProxyFactoryBean、事务管理器、默认的事务传播行为等。
	* 3.子Bean继承：具体的Service Bean（如 myService）通过 parent="txProxyTemplate" 来继承模板。
	* 4.定制化：子Bean只需要定义自己特有的部分（主要是注入自己的 target 目标对象），还可以覆盖模板中的特定属性（如为某些方法定制不同的事务属性）
	*
	* 这种基于 TransactionProxyFactoryBean 的XML配置方式，是Spring较为早期的用法。
	* 现代Spring应用（特别是Spring Boot）已经几乎完全转向使用 @Transactional 注解来声明事务，框架会自动为你创建代理，无需手动编写如此冗长的XML配置。这种方式更简洁、更直观，并且与代码紧耦合
	*
	* */
}
