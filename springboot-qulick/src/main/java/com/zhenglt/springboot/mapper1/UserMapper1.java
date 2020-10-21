package com.zhenglt.springboot.mapper1;

import com.zhenglt.springboot.pojo.UserBean;

import java.util.List;

/**
 * @ClassName UserMapper1
 * @Description
 * @Author zhenglt
 * @Date 20/10/20 11:36
 **/
public interface UserMapper1 {

    List<UserBean> findAll();
}
