package com.innovatrics.commons.vittap.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

  @Id
  @GeneratedValue
  private long id;

  private LevelOfAccess levelOfAccess;
  // login
  // password


  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
          name = "user_notification",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "notification_id")}
  )
  Set<Notification> notifications = new HashSet<>();


  public long getId() {
    return id;
  }

  public LevelOfAccess getLevelOfAccess() {
    return levelOfAccess;
  }

  public void setLevelOfAccess(LevelOfAccess levelOfAccess) {
    this.levelOfAccess = levelOfAccess;
  }

  public Set<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<Notification> notifications) {
    this.notifications = notifications;
  }
}