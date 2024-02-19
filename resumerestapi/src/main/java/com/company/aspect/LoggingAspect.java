package com.company.aspect;

import java.util.Arrays;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

    // Works if any method of our project throws an exception
    @AfterThrowing(
            pointcut = "execution(* com.company.*.*.*(..))",
            throwing = "ex")
    public void logError(Exception ex) {
        ex.printStackTrace();
    }

    // Works before invoked method runs
    @Before("execution(* com.company.service.inter.UserServiceInter.getAll(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    // It will work regardless method throws an exception or runs smoothly
    @After("execution(* com.company.service.inter.UserServiceInter.getAll(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    // Works if method runs successfully
    @AfterReturning(
            pointcut = "execution(* com.company.service.inter.UserServiceInter.getAll(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");
    }

    // Works if method throws an exception
    @AfterThrowing(
            pointcut = "execution(* com.company.service.inter.UserServiceInter.getAll(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");
    }


    // Works during the lifecycle of callable method
    @Around("execution(* com.company.service.inter.UserServiceInter.getAll(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("Around before is running!");
        Object res = joinPoint.proceed();
        System.out.println("Around after is running!");
        System.out.println("******");
        return res;
    }
}