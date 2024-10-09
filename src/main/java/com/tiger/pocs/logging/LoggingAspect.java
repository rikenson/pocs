package com.tiger.pocs.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Before("execution(* com.tiger.pocs.service..*(..)) || execution(* com.tiger.pocs.handler..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Appel de la méthode : {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(*  com.tiger.pocs.service..*(..)) || execution(* com.tiger.pocs.handler..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Méthode terminée : {} avec résultat : {}", joinPoint.getSignature(), result);
    }
}
