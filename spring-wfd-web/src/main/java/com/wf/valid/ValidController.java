package com.wf.valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class ValidController {


	@PostMapping(value = "/validUser",consumes = "application/json")
	public String validUser(@Valid @RequestBody ValidUser user){

		System.out.println("user: "+user);

		return "hello";
	}


	@GetMapping(value = "/validUserGet")
	public String validUserGet(@Min(value = 5, message = "每页数量必须大于0") @RequestParam Integer pageSize){

		System.out.println("pageSize: "+pageSize);
		return "validUserGet";
	}
}
