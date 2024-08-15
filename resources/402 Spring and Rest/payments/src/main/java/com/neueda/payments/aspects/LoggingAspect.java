package com.neueda.payments.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.neueda.payments.service.*.* (..))")
    public void logServiceMethods() {}

    private String getArgsAsString(Object[] args) {
        return Arrays.toString(args);
//        try {
//            String[] stringArgs = Arrays.copyOf(args, args.length, String[].class);
//            return String.join(",", stringArgs);
//        }
//        catch (Exception e) {
//            return "[unknown]";
//        }
    }

    @Before("logServiceMethods()")
    public void beforeLogServiceMethods(JoinPoint joinPoint){
        logger.info("Starting {} method with params {}",
                joinPoint.getSignature().getName(), getArgsAsString(joinPoint.getArgs()));
    }

    @After("logServiceMethods()")
    public void afterLogServiceMethods(){
        logger.info("Exiting a method");
    }
}


