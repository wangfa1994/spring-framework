package com.wf.model.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/19 9:44
 */
@Component
@Scope("prototype")
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
		System.out.println("cat 构造器");
    }



}
