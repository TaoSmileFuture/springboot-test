package com.zhenglt.springboot.shiro.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.AuthorizingSecurityManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
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
        //数据源认证管理
        webSecurityManager.setRealm(shiroRealm());
        //记住管理
        webSecurityManager.setRememberMeManager(rememberMeManager());
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
//        //拦截以上之外的所有请求 必须登录
//        filterChainDefinitionMap.put("/**", "authc");

        //拦截以上之外的所有请求 非必须登录 可以使用rememberMe
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * cookie设置
     * @return
     */
    public Cookie cookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        //设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        simpleCookie.setName("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(cookie());
        // rememberMe cookie加密的密钥
        rememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
