package com.wf.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello, Spring MVC Debug!";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "测试请求";
	}

	@GetMapping("/debug")
	@ResponseBody
	public String debug() {
		return "断点调试入口";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}


}
