package com.zhenglt.springboot.shiro.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  权限认证失败，异常拦截
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AuthorizationException.class)
//    @ResponseBody
	public String handleAuthorizationException() {
        log.error("没有通过权限验证！");
//        return "没有通过权限验证！";
	    return "403";
	}
}
