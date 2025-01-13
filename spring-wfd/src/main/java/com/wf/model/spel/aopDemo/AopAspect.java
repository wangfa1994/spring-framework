package com.wf.model.spel.aopDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class AopAspect {

	@Pointcut(value = "@annotation(com.wf.model.spel.aopDemo.StudentCheck)")
	public void studentCheck() {
	}


	@Around(value = "studentCheck()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 声明spel 表达式解析器
		ExpressionParser expressionParser = new SpelExpressionParser();


		// 得到我们的参数列表
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		Object[] args = point.getArgs();

		// 解析我们的参数名称
		ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
		String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

		// 声明解析器上下文，进行上下文内容的设置
		EvaluationContext evaluationContext = new StandardEvaluationContext();
		for (int i = 0; i < parameterNames.length; i++) {
			evaluationContext.setVariable(parameterNames[i], args[i]);
		}

		// 获取我们的解析表达式 这个表达式属于模板表达式
		StudentCheck annotation = method.getAnnotation(StudentCheck.class);
		String studentAnn = annotation.studentId();

		// 解析参数中的值
		Integer sutdentId = expressionParser.parseExpression(studentAnn).getValue(evaluationContext, Integer.class);


		// 业务逻辑处理
		if(sutdentId>=2){
			System.out.println("userid大于等于2，可以执行");
			return point.proceed();
		}else {
			System.out.println("userId小于2非法，不进行方法执行");
			return "false";
		}
	}


	// 多说一句 有没有存在获取不到参数名的情况？存在的，在编译的时候需要使用 -parameters 才能将参数信息保留，否则得到的就是一些args0,args1这些  idea中设置settings中java Compile可以指定
	// 在spring中， 在没有使用 -parameters 编译选项的情况下，Spring 的 ParameterNameDiscoverer 仍然能够获取到参数名，
	// 这主要得益于 Spring 内部实现的多种策略和机制。ParameterNameDiscoverer 是一个接口，用于从方法或构造函数中发现参数名称。
	// Spring 提供了多个实现类来处理不同情况下的参数名发现
	public static void main(String[] args) throws NoSuchMethodException {
		Class<AopAspect> vipCheckClass = AopAspect.class;
		Method echo = vipCheckClass.getMethod("echo", String.class);
		Parameter[] parameters = echo.getParameters();
		String name = parameters[0].getName();
		System.out.println("参数名称"+name);

		ParameterNameDiscoverer parameterNameDiscoverer = new StandardReflectionParameterNameDiscoverer();
		String[] parameterNames = parameterNameDiscoverer.getParameterNames(echo);
		System.out.println("spring"+parameterNames[0]);

	}

	public String echo(String nameP){
		System.out.println("hello");
		return "";
	}
	/**
	 * 主要实现类
	 * DefaultParameterNameDiscoverer 这是 ParameterNameDiscoverer 的默认实现，它组合了多个其他实现类来提高参数名发现的成功率。
	 * LocalVariableTableParameterNameDiscoverer 该实现依赖于字节码中的局部变量表（Local Variable Table），这是编译时生成的调试信息的一部分。如果编译时使用了 -g:vars 或 -g 选项，局部变量表会包含参数名信息。因此，在这种情况下，即使没有使用 -parameters 选项，LocalVariableTableParameterNameDiscoverer 也可以成功获取参数名。
	 * StandardReflectionParameterNameDiscoverer 该实现利用了 Java 8 引入的 Method.getParameterNames() 方法，但这个方法只有在编译时启用了 -parameters 选项时才有效。如果没有启用 -parameters，则此实现将无法获取参数名。
	 * ConstructorPropertiesParameterNameDiscoverer 如果类的构造函数使用了 @ConstructorProperties 注解，Spring 可以通过该注解提供的元数据来获取参数名。
	 * DebugInformationParameterNameDiscoverer 该实现尝试从调试信息中提取参数名，类似于 LocalVariableTableParameterNameDiscoverer。
	 *
	 *
	 * 实际工作流程
	 * 当 ParameterNameDiscoverer 尝试获取参数名时，它会依次调用这些实现类，直到找到有效的参数名。具体流程如下：
	 * 优先尝试 StandardReflectionParameterNameDiscoverer： 如果编译时启用了 -parameters 选项，它会直接返回参数名。
	 * 尝试 LocalVariableTableParameterNameDiscoverer： 如果编译时包含了局部变量表（即使用了 -g:vars 或 -g），它会从字节码中读取参数名。
	 * 尝试其他特定实现： 如 ConstructorPropertiesParameterNameDiscoverer 和 DebugInformationParameterNameDiscoverer，它们会在特定条件下提供参数名
	 *
	 */

}
