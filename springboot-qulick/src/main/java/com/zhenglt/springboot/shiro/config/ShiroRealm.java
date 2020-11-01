package com.zhenglt.springboot.shiro.config;

import com.zhenglt.springboot.pojo.PermissionBean;
import com.zhenglt.springboot.pojo.RoleBean;
import com.zhenglt.springboot.pojo.UserBean;
import com.zhenglt.springboot.shiro.service.ShiroService;
import com.zhenglt.springboot.shiro.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ShiroRealm
 * @Description
 * @Author zhenglt
 * @Date 2020/10/31 15:09
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * 角色/权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserBean user = (UserBean) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getUserid();
        System.out.println("用户" + userId + "认证-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //角色设置
        List<RoleBean> roles = shiroService.getRoleByUserId(userId);
        Set<String> roleSet = new HashSet<String>();
        roles.forEach(e->roleSet.add(e.getName()));
        authorizationInfo.setRoles(roleSet);
        //权限设置
        List<PermissionBean> perms = shiroService.getPermByUserId(userId);
        Set<String> perSet = new HashSet<String>();
        perms.forEach(e->perSet.add(e.getName()));
        authorizationInfo.setStringPermissions(perSet);

        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(StringUtils.isEmpty(token.getPrincipal())){
            return null;
        }
        String userId = (String) token.getPrincipal();
        String pwd = new String((char[]) token.getCredentials());
        System.out.println("用户" + userId + "认证-----ShiroRealm.doGetAuthenticationInfo");
        UserBean userBean = shiroService.getByUserId(userId);
        if(userBean==null){
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if(!pwd.equals(userBean.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if(!"1".equals(userBean.getFlag())){
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        //验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userBean,pwd,getName());

        return simpleAuthenticationInfo;
    }
}
