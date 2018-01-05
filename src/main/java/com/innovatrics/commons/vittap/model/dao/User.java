package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

  @Id
  @GeneratedValue
  private long id;

  private String password;

  private String login;

  private String name;

  private LevelOfAccess levelOfAccess;


  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
          name = "user_notification",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "notification_id")}
  )
  Set<Notification> notifications = new HashSet<>();

  public User(){
  }

  public User(String login, String name, String password, LevelOfAccess levelOfAccess){
    this.login = login;
    this.name = name;
    this.password = password;
    this.levelOfAccess = levelOfAccess;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}