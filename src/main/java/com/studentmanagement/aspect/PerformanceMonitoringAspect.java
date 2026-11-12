package com.studentmanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Pointcut("execution(* com.studentmanagement.service.StudentService.*(..)) || execution(* com.studentmanagement.controller.StudentController.*(..))")
    public void performancePointcut() {
    }

    @Around("performancePointcut()")
    public Object monitorExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("[AROUND ADVICE] Method {} executed in {} ms", methodName, executionTime);
        }
    }
}
