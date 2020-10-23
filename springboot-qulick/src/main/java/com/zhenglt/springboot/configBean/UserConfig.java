package com.zhenglt.springboot.configBean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserConfig
 * @Description
 * @Author zhenglt
 * @Date 20/10/23 10:12
 **/
@Component
@ConfigurationProperties(prefix = "student.one")
@PropertySource("classpath:System.properties")
public class UserConfig {

    private String name;
    private Integer age;
    private String toString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }
}
