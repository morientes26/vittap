package com.vitta_pilates.model.dao.attendance;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClassSeat {

  @Id
  @GeneratedValue
  private Long id;

  //    int position;
  @OneToOne
  @Cascade(value = CascadeType.ALL)
  private ClassSeatSlot fixed;

  @OneToOne
  @Cascade(value = CascadeType.ALL)
  private ClassSeatSlot temporary;

  private boolean attendanceStaus = false; // true = attended, false = not_attended

  private boolean isTeacher = false;

  @ManyToMany(mappedBy = "classSeats")
  private List<Attendance> classSeats  = new ArrayList<>();

  public ClassSeat(){}

  public Long getId() {
    return id;
  }

  public ClassSeatSlot getFixed() {
    return fixed;
  }

  public void setFixed(ClassSeatSlot fixed) {
    this.fixed = fixed;
  }

  public ClassSeatSlot getTemporary() {
    return temporary;
  }

  public void setTemporary(ClassSeatSlot temporary) {
    this.temporary = temporary;
  }

  public boolean isAttendanceStaus() {
    return attendanceStaus;
  }

  public void setAttendanceStaus(boolean attendanceStaus) {
    this.attendanceStaus = attendanceStaus;
  }

  public boolean isTeacher() {
    return isTeacher;
  }

  public void setTeacher(boolean teacher) {
    isTeacher = teacher;
  }

  public List<Attendance> getClassSeats() {
    return classSeats;
  }

  public void setClassSeats(List<Attendance> classSeats) {
    this.classSeats = classSeats;
  }
}
