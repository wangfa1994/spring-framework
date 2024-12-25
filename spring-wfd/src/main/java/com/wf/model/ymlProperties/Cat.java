package com.wf.model.ymlProperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/19 9:44
 */
@Component
public class Cat {

    private String category;

	@Value("${system_pause.switchFlag}")
	private String switchFlag;


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
    }


	public String getSwitchFlag() {
		return switchFlag;
	}

	public void setSwitchFlag(String switchFlag) {
		this.switchFlag = switchFlag;
	}

	@Override
	public String toString() {
		return "Cat{" +
				"category='" + category + '\'' +
				", switchFlag='" + switchFlag + '\'' +
				'}';
	}
}
