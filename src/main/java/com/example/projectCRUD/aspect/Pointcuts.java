package com.example.projectCRUD.aspect;


import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
  @Pointcut("execution(* com.example.projectCRUD..*(..))")
  public void allOrder(){}

  @Pointcut("execution(* *..*Service.*(..))")
  public void allService(){}

  //포인트컷을 합쳐서 새로운 포인트컷
  @Pointcut("allOrder() && allService()")
  public void allOrderandService(){}

}

//깃 모지 테스트

