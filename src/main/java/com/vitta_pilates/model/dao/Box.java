package com.vitta_pilates.model.dao;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Box {

  public List<Event> events = new ArrayList<>();
  public  boolean active;


  public Box(String idx){
    this.active = false;
   // events.add(new Event(idx));
  }

  public Box(List<Event> events){
    this.active = true;
    this.events = events;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
