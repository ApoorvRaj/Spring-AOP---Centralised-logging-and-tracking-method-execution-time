package com.spring.aop.logging.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {

	Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Around("execution(* com.spring.aop.logging.*.*.*(..) ) && !execution(* com.spring.aop.logging.repo.*.*(..))")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] arguments = pjp.getArgs();
		Long startTime = System.currentTimeMillis();
		LOGGER.info("method - " + methodName + " from class - "+className + " started with arguments "+ mapper.writeValueAsString(arguments));
		Object object = null;
		try {
		object = pjp.proceed();
		}finally {
			Long endTime = System.currentTimeMillis();
			Long diff = endTime - startTime;
			System.out.println("time taken by method - "+methodName + " of class - " + className +" : "+diff + "ms");
		}
		LOGGER.info("method - " + methodName + " from class - "+className + " ended with result - "+mapper.writeValueAsString(object));
		return object;
	}
	
}
