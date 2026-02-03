package com.wf.xmg.a13validator;

import com.wf.xmg.a13validator.spring.Group1;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class ServiceController {
	public void say(@Valid User user){
		System.out.println("into say method:"+user);
	}
	public void hello(@Validated User user){
		System.out.println("into hello method:"+user);
	}


	public void helloParam(@Validated @NotNull String param){
		System.out.println("into helloParam method:"+param);
	}
}
