package com.vitta_pilates.model.enumeration;

import org.zkoss.util.resource.Labels;


public enum  ClassSeatSlotStatus {

  OCCUPIED("classseat.occupied"), SUSPENDED("classseat.suspended"), EMPTY("classseat.empty");

  private String name;

  ClassSeatSlotStatus(String name){
    this.name = name;
  }

  public String getName() {
    return Labels.getLabel(name);
  }
}
