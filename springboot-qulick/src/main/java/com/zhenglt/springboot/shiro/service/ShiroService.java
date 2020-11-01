package com.zhenglt.springboot.shiro.service;

import com.zhenglt.springboot.mapper.PermissionMapper;
import com.zhenglt.springboot.mapper.RoleMapper;
import com.zhenglt.springboot.mapper.UserMapper;
import com.zhenglt.springboot.pojo.PermissionBean;
import com.zhenglt.springboot.pojo.RoleBean;
import com.zhenglt.springboot.pojo.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public UserBean getByUserId(String userId){
        return userMapper.findByUserId(userId);
    }

    public List<RoleBean> getRoleByUserId(String userId){
        return roleMapper.findByUserId(userId);
    }

    public List<PermissionBean> getPermByUserId(String userId){
        return permissionMapper.findByUserId(userId);
    }
}
