package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="\"user\"") // special case of table because 'user' word is reserved by hibernate
public class User {

  @Id
  @GeneratedValue
  private long id;

  private String password;

  private String login;

  private String name;

  @OneToOne
  private Role role;


  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
          name = "user_notification",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "notification_id")}
  )
  Set<Notification> notifications = new HashSet<>();

  public User(){
  }

  public User(String login, String name, String password, Role role){
    this.login = login;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public long getId() {
    return id;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}