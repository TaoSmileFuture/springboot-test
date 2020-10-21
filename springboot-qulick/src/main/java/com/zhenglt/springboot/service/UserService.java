package com.zhenglt.springboot.service;

import com.alibaba.fastjson.JSON;
import com.zhenglt.springboot.mapper.UserMapper;
import com.zhenglt.springboot.pojo.UserBean;
import com.zhenglt.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author zhenglt
 * @Date 20/10/20 13:46
 **/
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional(readOnly = true)
    public List<UserBean> getUserList(){

        return userMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserBean> getUserListByJpa(){

        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public String getUserListByRedis(){
        String data = "";
        Boolean hasKey = redisTemplate.hasKey("userList.findAll");
        if(!hasKey){
            System.out.println("dataSource.......");
            List<UserBean> userList = getUserList();
            data = JSON.toJSONString(userList);
            redisTemplate.boundValueOps("userList.findAll").set(data);
        }else{
            System.out.println("redis.......");
            data = redisTemplate.boundValueOps("userList.findAll").get();
        }
        return data;
    }
}
