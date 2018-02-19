package com.vitta_pilates.core.event.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EventForm implements Serializable{

  private String id;
  private String start;
  private int duration;
  private String title;
  private String name;
  private String description;
  private String type = Type.SIMPLE.getName();
  private String occurence;
  private String recurrentType;
  private AttendanceForm attendanceTeacherForm = new AttendanceForm();
  private List<AttendanceForm> attendanceForm = new ArrayList<>();

  public EventForm(){

  }

  public enum Type {
    SIMPLE("S"), CLASS("C");

    private String name;

    Type(String name){
      this.name = name;
    }

    public String getName(){
      return this.name;
    }
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

  public List<AttendanceForm> getAttendanceForm() {
    return attendanceForm;
  }

  public void setAttendanceForm(List<AttendanceForm> attendanceForm) {
    this.attendanceForm = attendanceForm;
  }

  public AttendanceForm getAttendanceTeacherForm() {
    return attendanceTeacherForm;
  }

  public void setAttendanceTeacherForm(AttendanceForm attendanceTeacherForm) {
    this.attendanceTeacherForm = attendanceTeacherForm;
  }
}
