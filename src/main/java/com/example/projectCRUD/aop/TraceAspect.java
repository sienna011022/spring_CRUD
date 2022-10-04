package com.example.projectCRUD.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

	@Before("@annotation(com.example.projectCRUD.aop.Trace)")
	public void doTrace(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		//파라미터 받아서 출력
		log.info("[trace] {} args = {}",joinPoint.getSignature(),args);
	}


}
