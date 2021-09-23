package com.ibm.microservice.blocking;

public class GreeterService {

  public String getMessage() throws InterruptedException {
     Thread.sleep(5000);
     return "Hello,This Delayed Message";
  }
}
