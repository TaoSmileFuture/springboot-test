package com.zhenglt.springboot.shiro.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @ClassName ShiroConfig
 * @Description
 * @Author zhenglt
 * @Date 2020/10/31 15:07
 **/
@Configuration
public class ShiroConfig {

    /**
     * 自定义认证规则注入ioc
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

    /**
     *  权限管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm());
        return webSecurityManager;
    }

    /**
     * 过滤工厂，配置过滤规则和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        shiroFilterFactoryBean.setSuccessUrl("/shiro/index");
        //认证失败跳转地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //过滤条件
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //静态资源过滤，不拦截 匿名使用
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        //注销，不拦截
        filterChainDefinitionMap.put("/logout", "logout");
        //登录页面，不拦截 匿名使用
        filterChainDefinitionMap.put("/shiro/login", "anon");
        //拦截以上之外的所有请求 必须登录
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
