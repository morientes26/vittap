package com.vitta_pilates.core.event.component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by  ??_?¬ morientes on 05/02/2018.
 */
public class EventForm implements Serializable{

  private String id;
  private String start;
  private int duration;
  private String title;
  private String name;
  private String description;
  private String type;
  private String occurence;
  private String recurrentType;

  public EventForm(){

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOccurence() {
    return occurence;
  }

  public void setOccurence(String occurence) {
    this.occurence = occurence;
  }

  public String getRecurrentType() {
    return recurrentType;
  }

  public void setRecurrentType(String recurrentType) {
    this.recurrentType = recurrentType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
