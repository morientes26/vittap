package com.innovatrics.commons.vittap.auth.service;

import com.innovatrics.commons.vittap.model.dao.LevelOfAccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserCredential implements Serializable{
  private static final long serialVersionUID = 1L;

  String account;
  String name;

  List<String> roles = new ArrayList<>();

  public UserCredential(String account, String name, List<String> roles) {
    this.account = account;
    this.name = name;
    this.roles = roles;
  }

  public UserCredential() {
    this.account = LevelOfAccess.ANONYMOUS.name();
    this.name = LevelOfAccess.ANONYMOUS.name();
    roles.add(LevelOfAccess.ANONYMOUS.name());
  }

  public boolean isAnonymous() {
    return hasRole(LevelOfAccess.ANONYMOUS.name()) || LevelOfAccess.ANONYMOUS.name().equals(account);
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean hasRole(String role){
    return roles.contains(role);
  }

  public void addRole(String role){
    roles.add(role);
  }

  public List<String> getRoles() {
    return roles;
  }
}