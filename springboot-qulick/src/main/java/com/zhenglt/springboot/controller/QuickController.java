package com.zhenglt.springboot.controller;

import com.zhenglt.springboot.configBean.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName QulickController
 * @Description 使用ConfigurationProperties时，属性名要和配置文件字段保持一致，并且需要提供set方法
 * @Author zhenglt
 * @Date 20/10/19 15:13
 **/
@Controller
@ConfigurationProperties(prefix = "student.one")
public class QuickController {

//    @Value("${projectName}")
//    private String projectName;

//    @Value("${student.one.name}")
//    private String studentName;

//    @Value("${student.one.age}")
//    private Integer studentAge;

//    private String name;
//    private Integer age;
//    private String toString;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public void setToString(String toString) {
//        this.toString = toString;
//    }

    @Autowired
    private UserConfig userConfig;

    @RequestMapping("/quick")
    @ResponseBody
    public String quick(){

//        return "studentName:"+name+",studentAge:"+age+",toString:"+toString;
        return "studentName:"+userConfig.getName()+",studentAge:"+userConfig.getAge()+",toString:"+userConfig.getToString();
    }
}
