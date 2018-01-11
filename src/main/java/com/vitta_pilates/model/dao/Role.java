package com.vitta_pilates.model.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Role {

  @Id
  @GeneratedValue
  private Long id;

  @NotEmpty(message = "Name can not be null")
  private String name;

  private String description;

  public Role(){
  }

  public Role(String name, String description){
    this.name = name;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}