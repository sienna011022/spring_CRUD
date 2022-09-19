package com.example.projectCRUD.config;

import com.example.projectCRUD.trace.LogTrace;
import com.example.projectCRUD.trace.TraceStatus;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;


@AllArgsConstructor
public class LogTraceAspect {

  private final LogTrace logTrace;

  @Around("execution(* com.example.projectCRUD..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint)throws Throwable{

    TraceStatus status = null;

    try {
      String message = joinPoint.getSignature().toShortString();
      status = logTrace.begin(message);

      //로직 호출

      Object result = joinPoint.proceed();

      logTrace.end(status);

      return result;
    }catch(Exception e){
      logTrace.exception(status,e);
      throw e;
    }
  }

}
