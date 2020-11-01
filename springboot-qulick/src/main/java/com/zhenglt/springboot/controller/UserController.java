package com.zhenglt.springboot.controller;

import com.zhenglt.springboot.pojo.UserBean;
import com.zhenglt.springboot.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
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

    /**
     * 验证角色有没有注解内指定的权限
     * @param model
     * @return
     */
    @RequiresPermissions("user:user")
    @RequestMapping("list")
    public String userList(Model model) {
        model.addAttribute("value", "获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("add")
    public String userAdd(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }

    /**
     * 验证用户有没有注解内指定的角色
     * @param model
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping("admin")
    public String userRole(Model model) {
        model.addAttribute("value", "admin role");
        return "user";
    }

}
