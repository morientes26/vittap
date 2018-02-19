package com.vitta_pilates.model.dao.attendance;

import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.enumeration.ClassSeatSlotStatus;

import javax.persistence.*;

@Entity
public class  ClassSeatSlot {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private Attendant attendant;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ClassSeatSlotStatus status = ClassSeatSlotStatus.EMPTY;

  public ClassSeatSlot(){}

  public ClassSeatSlot(Attendant attendant){
    this.attendant = attendant;
    this.status = ClassSeatSlotStatus.OCCUPIED;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Attendant getAttendant() {
    return attendant;
  }

  public void setAttendant(Attendant attendant) {
    this.attendant = attendant;
  }

  public ClassSeatSlotStatus getStatus() {
    return status;
  }

  public void setStatus(ClassSeatSlotStatus status) {
    this.status = status;
  }
}
