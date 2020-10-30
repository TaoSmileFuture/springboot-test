package com.zhenglt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MySpringBootApplication
 * @Description spingboot启动入口 只会加载扫描该类同级及下级类文件
 * @Author zhenglt
 * @Date 20/10/19 15:05
 *  SpringBootApplication 声明该类是一个spingboot引导类
 *  MapperScan mabits的mapper接口上不添加@mapper注解时，使用包扫描模式
 *  EnableTransactionManagement 开启事务
 *  EnableAspectJAutoProxy 开启aop
 **/
@SpringBootApplication
//@MapperScan(value = {"com.zhenglt.springboot.mapper","com.zhenglt.springboot.mapper1"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class MySpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(MySpringBootApplication.class);
    }
}
