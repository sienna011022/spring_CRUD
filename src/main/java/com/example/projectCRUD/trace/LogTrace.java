package com.example.projectCRUD.trace;


import org.springframework.stereotype.Service;

@Service
public interface LogTrace {

  TraceStatus begin(String message);
  void end(TraceStatus status);
  void exception(TraceStatus status, Exception e);

}
