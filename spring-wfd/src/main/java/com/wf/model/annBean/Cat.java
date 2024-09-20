package com.wf.model.annBean;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/19 9:44
 */

public class Cat {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cat(String category) {
        this.category = category;
    }


    public Cat() {
		System.out.println("cat的无参构造器......");
        System.out.println("无参构造器-----cat");
    }

}
