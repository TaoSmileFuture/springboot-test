package com.zhenglt.springboot.aop;

import com.zhenglt.springboot.aop.annotation.Log;
import com.zhenglt.springboot.aop.service.LogService;
import com.zhenglt.springboot.pojo.SysLog;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName AopAspect
 * @Description
 * @Author zhenglt
 * @Date 2020/10/30 15:41
 **/
@Aspect
@Component
public class AopAspect {

    @Autowired
    private LogService logService;

//	  (1)Aspect(切面):通常是一个类，里面可以定义切入点和通知
//    (2)JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用
//      @annotation(注解路径) 注解增强
//      execution(public * com.zhenglt.springboot.aop.service.*.function*(..)) 方法增强
//    (3)Advice(通知):AOP在特定的切入点上执行的增强处理，有before,after,afterReturning,afterThrowing,around
//    (4)Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
//    (5)AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类
//     AOP =Aspect+Pointcut

//    通知方法:
//
//    前置通知:在我们执行目标方法之前运行(@Before)
//    后置通知:在我们目标方法运行结束之后 ,不管有没有异常(@After)
//    返回通知:在我们的目标方法正常返回值后运行(@AfterReturning)
//    异常通知:在我们的目标方法出现异常后运行(@AfterThrowing)
//    环绕通知:动态代理, 需要手动执行joinPoint.procced()(其实就是执行我们的目标方法执行之前相当于前置通知, 执行之后就相当于我们后置通知(@Around)
//    ProceedingJoinPoint只有在环绕通知可以使用


    @Pointcut("execution(public * com.zhenglt.springboot.aop.service.*.function*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        System.out.println("before..................");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after..................");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning..................");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("afterThrowing..................");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around..................前");
        //手动执行
        joinPoint.proceed();
        SysLog sysLog = new SysLog();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        if(annotation!=null){
            sysLog.setOperation(annotation.value());
        }
        String methodName = method.getName();
        sysLog.setMethod(className+"."+methodName);
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        sysLog.setIp("127.0.0.1");
        sysLog.setTime(System.currentTimeMillis());
        sysLog.setCreateTime(new Date());
        sysLog.setUsername("admin");

        logService.saveLog(sysLog);

        System.out.println("around..................后");
    }

}
