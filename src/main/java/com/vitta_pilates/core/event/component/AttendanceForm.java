package com.vitta_pilates.core.event.component;

import java.io.Serializable;

public class AttendanceForm implements Serializable {

    public Flag fixed;
    public Flag temporary;
    public String name;
    public Action action;
    public Action action2;
    public Long user;
    public Long id; //attendance_id

    public AttendanceForm(){}
    public AttendanceForm(Flag fixed,
                          Flag temporary,
                          String name,
                          Action action,
                          Action action2,
                          Long user,
                          Long id){
      this.fixed = fixed;
      this.temporary = temporary;
      this.name = name;
      this.action = action;
      this.action2 = action2;
      this.user = user;
      this.id = id;
    }

    public static class AttendanceFormBuilder {

      private Flag fixed;
      private Flag temporary;
      private String name;
      private Action action;
      private Action action2;
      private Long user;
      private Long id;

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

      public AttendanceFormBuilder setAction(Action action){
        this.action = action;
        return this;
      }

      public AttendanceFormBuilder setAction2(Action action2){
        this.action2 = action2;
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
                this.id
        );
      }
    }

    public enum Flag {
      ENROLL("button_enrolled.png"),
      DISABLED("button_disabled.png"),
      SUSPENDED("button_suspended.png"),
      EMPTY("button_empty.png");

      public String url;

      Flag(String url){
        this.url = url;
      }
    }

    public enum Action {

      ENROLL, UNENROLL, SUSPEND, UNSUSPEND

    }

}
