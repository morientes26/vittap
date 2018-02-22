package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserAccount {

  @Id
  @GeneratedValue
  private Long id;

  private String password;

  private String login;

  private String name;

  @OneToOne
  private Role role;


  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
          name = "user_account_notification",
          joinColumns = {@JoinColumn(name = "user_account_id")},
          inverseJoinColumns = {@JoinColumn(name = "notification_id")}
  )
  Set<Notification> notifications = new HashSet<>();

  public UserAccount(){
  }

  public UserAccount(String login, String name, String password, Role role){
    this.login = login;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
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