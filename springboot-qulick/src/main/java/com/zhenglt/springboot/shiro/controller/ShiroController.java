package com.zhenglt.springboot.shiro.controller;

import com.zhenglt.springboot.pojo.ResponseBo;
import com.zhenglt.springboot.pojo.UserBean;
import com.zhenglt.springboot.shiro.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ShiroController
 * @Description
 * @Author zhenglt
 * @Date 2020/10/31 15:07
 **/
@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password) {
//        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/shiro/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        UserBean user = (UserBean) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/shiro/login";
    }
}
