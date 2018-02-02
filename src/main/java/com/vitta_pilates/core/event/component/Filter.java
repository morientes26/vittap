package com.vitta_pilates.core.event.component;

import java.io.Serializable;


public class Filter implements Serializable {

  private String type;
  private String value;
  private String show;

  public Filter(){

  }

  public Filter(String type, String value, String show){
    this.type = type;
    this.value = value;
    this.show = show;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getShow() {
    return show;
  }

  public void setShow(String show) {
    this.show = show;
  }
}
