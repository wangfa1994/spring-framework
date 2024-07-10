package com.wf.model.annxml;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/8 15:39
 */
public class Person {

    private String name;


    private Cat cat;


	public Person() {
		System.out.println("通过配置文件进来的person 构造器...");
	}

	public Cat getCat() {
        return cat;
    }


    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
