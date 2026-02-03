package com.wf.xmg.a13validator.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

public class ApplicationContextDemo {


	public static void main(String[] args) {
		UserLogin userLogin = new UserLogin();
		userLogin.setUserName("z");

		UserLoginValidator userLoginValidator = new UserLoginValidator();

		Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");
		userLoginValidator.validate(userLogin, errors);

		// 获取messageSource对象，整合到messageSource上,
		MessageSource messageSource = createMessageSource();

		if (errors.hasErrors()) {
			List<ObjectError> allErrors = errors.getAllErrors();
			System.out.println("开始打印异常");
			for (ObjectError item : allErrors) {
				System.out.println(item.toString());
				System.out.println(messageSource.getMessage(item.getCode(), item.getArguments(), Locale.getDefault()));
			}

		}
	}

	// MessageSource 整合我们的所有信息枚举 ，这个code需要和校验器中的code相匹配才能找到对应的结果
	static MessageSource createMessageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
		messageSource.addMessage("password.field.required", Locale.getDefault(), "密码不能为空");
		messageSource.addMessage("password.field.required.userLogin.password", Locale.getDefault(), "密码不能为空空空空");
		messageSource.addMessage("userName.required", Locale.getDefault(), "名称不能为空");
		return messageSource;
	}

}
