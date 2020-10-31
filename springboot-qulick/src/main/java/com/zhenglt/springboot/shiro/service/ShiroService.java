package com.zhenglt.springboot.shiro.service;

import com.zhenglt.springboot.mapper.UserMapper;
import com.zhenglt.springboot.pojo.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ShiroService
 * @Description
 * @Author zhenglt
 * @Date 2020/10/31 15:08
 **/
@Service
public class ShiroService {

    @Autowired
    private UserMapper userMapper;

    public UserBean getByUserId(String userId){
        return userMapper.findByUserId(userId);
    }
}
