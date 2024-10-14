package com.wf.model.aop.basejdk;

public class StudentServiceImpl implements StudentService{

	@Override
	public String getStudentInfo(){
		System.out.println("进入getStudentInfo逻辑");
		return "getStudentInfo is  18 age";
	}
}
