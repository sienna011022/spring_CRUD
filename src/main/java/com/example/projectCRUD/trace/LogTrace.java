package com.example.projectCRUD.trace;

public interface LogTrace {

  TraceStatus begin(String message);
  void end(TraceStatus status);
  void exception(TraceStatus status, Exception e);

}
