package com.vitta_pilates.model.dao.attendance;

import com.vitta_pilates.model.dao.ClassInstance;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Attendance {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private ClassInstance classInstance;

  @ManyToMany(fetch = FetchType.EAGER)
  @Cascade(value = CascadeType.DELETE)
  @JoinTable(
          name = "attendance_class_seat",
          joinColumns = {@JoinColumn(name = "attendance_id")},
          inverseJoinColumns = {@JoinColumn(name = "class_seat_id")}
  )
  private List<ClassSeat> classSeats = new ArrayList<>();


  public Attendance(){}

  public List<ClassSeat> getPupils(){
    List<ClassSeat> result = new ArrayList<>();
    for (ClassSeat seat : classSeats){
      if (!seat.isTeacher()){
        result.add(seat);
      }
    }
    return result;
  }

  public ClassSeat getTeacher(){
    ClassSeat result = null;
    for (ClassSeat seat : classSeats){
      if (seat.isTeacher()){
        return seat;
      }
    }
    return result;
  }


  public Long getId() {
    return id;
  }

  public List<ClassSeat> getClassSeats() {
    return classSeats;
  }

  public void setClassSeats(List<ClassSeat> classSeats) {
    this.classSeats = classSeats;
  }

  public ClassInstance getClassInstance() {
    return classInstance;
  }

  public void setClassInstance(ClassInstance classInstance) {
    this.classInstance = classInstance;
  }
}
