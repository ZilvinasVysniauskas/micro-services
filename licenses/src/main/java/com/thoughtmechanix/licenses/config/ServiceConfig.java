package com.thoughtmechanix.licenses.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig{


  private String exampleProperty ="x";

  public String getExampleProperty(){
    return exampleProperty;
  }
}
