package com.wf.xmg.a13validator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan("com.wf.xmg.a13validator")
public class SpringBeanValidatorDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBeanValidatorDemo.class);
		ServiceController bean1 = context.getBean(ServiceController.class);
		/*try {
			bean1.hello(new User());
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		/*try {
			bean1.say(new User());
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		try {
			bean1.helloParam(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 *
		 *
		 */


	}


	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(){
		return new LocalValidatorFactoryBean();
	}

	// 方法级别的校验
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(localValidatorFactoryBean());
		return methodValidationPostProcessor;
	}










	public void beanValid(AnnotationConfigApplicationContext context ){
		Validator bean = context.getBean(Validator.class);

		System.out.println(bean);

		System.out.println(bean.supports(User.class));

		User user = new User();
		Errors errors = new BeanPropertyBindingResult(user, "user");
		bean.validate(user,errors);

		System.out.println(errors);
		System.out.println(errors.getFieldError("name").getDefaultMessage());
	}


}
