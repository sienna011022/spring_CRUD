package com.example.projectCRUD.trace;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Bean;


@AllArgsConstructor

//저번에 6번에서 만들어놓은 advice 프록시 팩터리로.
public class LogTraceAdvice implements MethodInterceptor {

  private final LogTrace logTrace;


  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    TraceStatus status = null;
    try {
      Method method = invocation.getMethod();
      String message = method.getDeclaringClass().getSimpleName() + "." +
          method.getName() + "()";
      status = logTrace.begin(message);

      //로직 호출
      Object result = invocation.proceed();

      logTrace.end(status);
      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }
}
