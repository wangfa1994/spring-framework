package com.wf.model.autowire.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/8/12 15:29
 */
@Component
public class Cat {

	public Cat() {
		System.out.println("cat的构造器......");
	}


	public static void main(String[] args) {
		System.out.println("这是中国");
		System.out.println("构造器这几个字能打印吗");
	}
}
