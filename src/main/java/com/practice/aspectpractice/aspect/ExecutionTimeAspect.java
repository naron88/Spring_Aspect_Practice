package com.practice.aspectpractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 메서드 실행 시간을 측정하는 클래스
@Aspect
@Component
public class ExecutionTimeAspect {

    // 특정 어노테이션이 있는 메서드에서만 호출
    @Around("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")  // ✅ 패키지 경로 추가
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { // 모든 종류의 예외 처리
        long startTime = System.currentTimeMillis();

        // 원래 메서드 실행하고 값 반환
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature().getName() + " 메서드 실행 시간: " + (endTime - startTime) + "ms");

        return result;
    }
}
