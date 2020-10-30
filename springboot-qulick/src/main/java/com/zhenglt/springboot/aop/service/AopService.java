package com.zhenglt.springboot.aop.service;

import com.zhenglt.springboot.aop.annotation.Log;
import org.springframework.stereotype.Service;

@Service
public class AopService {

    @Log("test aop")
    public void functionOne(){
//        String str = null;
//        System.out.println(str.length());
        System.out.println("=============functionOne===============");
    }

    public void saveLog(){
        System.out.println("=============saveLog===============");
    }
}
