package com.zhenglt.springboot.mapper;

import com.zhenglt.springboot.pojo.UserBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description
 * @Author zhenglt
 * @Date 20/10/20 11:22
 *  mapper不添加改注解时，引导类必须添加包扫描注解(MapperScan)
 **/
//@Mapper
public interface UserMapper {

    List<UserBean> findAll();
}
