package com.neueda.payments.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepoLogginAspect {

    @Pointcut("execution(* com.neueda.payments.repositories.*.* (..))")
    public void logRepoMethods(){}

    @Around("logRepoMethods()")
    public Object aroundRepoMethods(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Starting repo method: " + pjp.getSignature());
        Object result = pjp.proceed();

        System.out.println("Exiting repo method: " + pjp.getSignature());
        return  result;
    }
}
