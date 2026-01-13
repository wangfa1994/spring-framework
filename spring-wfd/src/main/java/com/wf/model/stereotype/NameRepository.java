package com.wf.model.stereotype;


import java.util.Arrays;
import java.util.List;

@StringRepository(value ="nameRepository")
public class NameRepository {


	public List<String> findAll(){
		return Arrays.asList("lisi","zhangsan");
	}
}
