package com.vitta_pilates.core.event.component;

import java.io.Serializable;
import java.util.List;

public class AttendanceForm implements Serializable {

    public Flag fixed;
    public Flag temporary;
    public String name;
    public List<Action> action;
    public List<Action> action2;
    public Long user;
    public Long user2;
    public Long id; //attendance_id
    public boolean select = false;
    public boolean attend = false;

    public AttendanceForm(){}
    public AttendanceForm(Flag fixed,
                          Flag temporary,
                          String name,
                          List<Action> action,
                          List<Action> action2,
                          Long user,
                          Long user2,
                          Long id,
                          boolean select,
                          boolean attend){
      this.fixed = fixed;
      this.temporary = temporary;
      this.name = name;
      this.action = action;
      this.action2 = action2;
      this.user = user;
      this.user2 = user2;
      this.id = id;
      this.select = select;
      this.attend = attend;
    }

    public static class AttendanceFormBuilder {

      private Flag fixed;
      private Flag temporary;
      private String name;
      private List<Action> action;
      private List<Action> action2;
      private Long user;
      private Long user2;
      private Long id;
      public boolean select;
      public boolean attend;

      public AttendanceFormBuilder(){

      }

      public AttendanceFormBuilder(Long user, Long id){
        this.user = user;
        this.id = id;
      }

      public AttendanceFormBuilder setId(Long id){
        this.id = id;
        return this;
      }

      public AttendanceFormBuilder setFixed(Flag fixed){
        this.fixed = fixed;
        return this;
      }

      public AttendanceFormBuilder setTemporary(Flag temporary){
        this.temporary = temporary;
        return this;
      }

      public AttendanceFormBuilder setName(String name){
        this.name = name;
        return this;
      }

      public AttendanceFormBuilder setAction(List<Action> action){
        this.action = action;
        return this;
      }

      public AttendanceFormBuilder setAction2(List<Action> action2){
        this.action2 = action2;
        return this;
      }

      public AttendanceFormBuilder setUser(Long user){
        this.user = user;
        return this;
      }

      public AttendanceFormBuilder setUser2(Long user2){
        this.user2 = user2;
        return this;
      }

      public AttendanceFormBuilder setSelect(boolean select){
        this.select = select;
        return this;
      }

      public AttendanceFormBuilder setAttend(boolean attend){
        this.attend = attend;
        return this;
      }

      public AttendanceForm createAttendanceForm(){
        return new AttendanceForm(
                this.fixed,
                this.temporary,
                this.name,
                this.action,
                this.action2,
                this.user,
                this.user2,
                this.id,
                this.select,
                this.attend
        );
      }
    }

    public enum Flag {
      ENROLL("button_enrolled.png"),
      ENROLL_SUSPENDED("button_enrolled.png"),
      DISABLED("button_disabled.png"),
      SUSPENDED("button_suspended.png"),
      EMPTY("button_empty.png");

      public String url;

      Flag(String url){
        this.url = url;
      }
    }

    public enum Action {

      ENROLL, ENROLL_SUSPENDED, UNENROLL, SUSPEND, UNSUSPEND

    }

}
