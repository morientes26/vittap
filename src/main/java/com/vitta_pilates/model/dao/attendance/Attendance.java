package com.vitta_pilates.model.dao.attendance;

import com.vitta_pilates.model.dao.ClassInstance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Attendance {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private ClassSeat teacher;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "attendance_class_seat",
          joinColumns = {@JoinColumn(name = "attendance_id")},
          inverseJoinColumns = {@JoinColumn(name = "class_seat_id")}
  )
  List<ClassSeat> pupils  = new ArrayList<>();

  @OneToOne
  private ClassInstance classInstance;


  public Attendance(){}

  public Long getId() {
    return id;
  }

  public ClassSeat getTeacher() {
    return teacher;
  }

  public void setTeacher(ClassSeat teacher) {
    this.teacher = teacher;
  }

  public List<ClassSeat> getPupils() {
    return pupils;
  }

  public void setPupils(List<ClassSeat> pupils) {
    this.pupils = pupils;
  }

  public ClassInstance getClassInstance() {
    return classInstance;
  }

  public void setClassInstance(ClassInstance classInstance) {
    this.classInstance = classInstance;
  }
}
