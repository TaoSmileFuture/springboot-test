package com.zhenglt.springboot.controller;

import com.zhenglt.springboot.pojo.UserBean;
import com.zhenglt.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author zhenglt
 * @Date 2020/10/31 10:21
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    public String getList(Model model){

        List<UserBean> userList = userService.getUserList();
        model.addAttribute("userList",userList);
        return "users";
    }
}
