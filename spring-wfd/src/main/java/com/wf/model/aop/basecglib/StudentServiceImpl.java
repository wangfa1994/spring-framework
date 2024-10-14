package com.wf.model.aop.basecglib;

import com.wf.model.aop.basejdk.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public String getStudentInfo(){
		System.out.println("进入getStudentInfo逻辑");
		return "getStudentInfo is  18 age";
	}
}
