package com.wf.enhance;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DebugController {

	@GetMapping("/params")
	public String testParams(@RequestParam String name,
							 @RequestParam(defaultValue = "20") Integer age) {
		return String.format("参数测试: name=%s, age=%d", name, age);
	}

	@PostMapping("/json")
	public User testJson(@RequestBody User user) {
		user.setName(user.getName() + "_processed");
		return user;
	}

	@GetMapping("/exception")
	public String testException() {
		throw new RuntimeException("测试异常处理");
	}

	static class User {
		private String name;
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}
}
