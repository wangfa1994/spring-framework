package com.wf.model.spel;

import com.wf.model.spel.aopDemo.Student;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

// 主要用于在运行时查询和操作对象图
public class SpelDemo {

	/**
	 * 1. 文字表达式：包括字面量表达式、关系运算符、逻辑与算数运算表达式等
	 *
	 * https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html
	 */
	public static void main(String[] args) {
		// 主要操作的两个类
		// ExpressionParser 接口负责解析表达式字符串。 ExpressionParser 分析器， 直接可以进行我们的表达式的分析，运算等处理
		ExpressionParser expressionParser = new SpelExpressionParser(); // 分析器
		Expression expression = expressionParser.parseExpression("'hello'"); //字面表达式
		String value = (String)expression.getValue();
		System.out.println(value);



		Expression expression1 = expressionParser.parseExpression("'Hello World'.concat('!')"); //运算
		System.out.println(expression1.getValue());



		/**
		 * 在评估表达式时使用接口EvaluationContext来解析属性，方法，字段，并且帮助去进行类型转换。开箱即用的的实现 StandardEvaluationContext
		 * 使用反射来操作对象，缓存 java.lang.reflect 的方法、字段和构造函数实例以提高性能
		 *
		 * spel 和类型转换器有关？
		 */
		StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
		Student student  = new Student();
		student.setId(1);student.setName("zhangsan");
		evaluationContext.setRootObject(student);  // 设置Object 直接取值

		String name = expressionParser.parseExpression("name").getValue(evaluationContext, String.class);// 替换值
		System.out.println("直接获取值："+name);

		expressionParser.parseExpression("name").setValue(evaluationContext, "lisi"); // 替换值
		System.out.println("替换值之后获取"+student.getName());




		Student studentT  = new Student();
		studentT.setId(1);studentT.setName("zhangsanFromTeacher");
		Teacher teacher = new Teacher(); teacher.setStudent(studentT);
		StandardEvaluationContext evaluationContextT = new StandardEvaluationContext();

		evaluationContextT.setRootObject(teacher);
		String studentName = expressionParser.parseExpression("student.name").getValue(evaluationContextT, String.class);// 获取嵌套值
		System.out.println("嵌套中获取值"+studentName);



		EvaluationContext evaluationContextV = new StandardEvaluationContext();
		evaluationContextV.setVariable("names","lisi"); // 设置variables 需要进行#进行解析
		String studentNames = expressionParser.parseExpression("#names").getValue(evaluationContextV, String.class);//从variables中获取值
		System.out.println("variable中获取值"+studentNames);





	}


}
