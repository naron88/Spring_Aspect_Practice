package com.practice.aspectpractice.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")
    public void beforeMethodExecution() {
        System.out.println("메서드 실행 전 수행");
    }

    @After("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")
    public void afterMethodExecution() {
        System.out.println("메서드 실행 후 수행");
    }
}
