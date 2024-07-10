package com.wf.model.aop.jdk;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/10/25 19:23
 */
@Service
public class JavaServiceImpl implements JavaService{
    @Override
    public String helloJava(String hello) {
        System.out.println("hello:"+hello);
        return hello+"====="+hello.length();
    }
}
