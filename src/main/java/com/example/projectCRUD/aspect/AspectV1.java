package com.example.projectCRUD.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

  @Around("com.example.projectCRUD.aspect.Pointcuts.allOrder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
    log.info("==================================");
    log.info("[log] {}",joinPoint.getSignature());
    Object result =  joinPoint.proceed();
    return result;
  }

  @Around("com.example.projectCRUD.aspect.Pointcuts.allOrderandService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
    try{
      Object result = joinPoint.proceed();
      log.info("[트랜잭션 커밋] {}",joinPoint.getSignature());
      return result;
    }catch(Exception e){
      log.info("[트랜잭션 롤백] {}",joinPoint.getSignature());
      throw e;
    }
    finally{
      log.info("[리소스 릴리즈] {}",joinPoint.getSignature());
    }
  }
}
