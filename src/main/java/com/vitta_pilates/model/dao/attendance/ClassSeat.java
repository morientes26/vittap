package com.vitta_pilates.model.dao.attendance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClassSeat {

  @Id
  @GeneratedValue
  private Long id;

  //    int position;
  @OneToOne
  private ClassSeatSlot fixed;

  @OneToOne
  private ClassSeatSlot temporary;

  private boolean attendanceStaus = false; // true = attended, false = not_attended

  @ManyToMany(mappedBy = "pupils")
  private List<Attendance> pupils  = new ArrayList<>();

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

  public List<Attendance> getPupils() {
    return pupils;
  }

  public void setPupils(List<Attendance> pupils) {
    this.pupils = pupils;
  }
}
