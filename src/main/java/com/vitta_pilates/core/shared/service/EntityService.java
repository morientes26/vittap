package com.vitta_pilates.core.shared.service;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

//note: I use abstract class instead of interface because generic interface
//note: has problem with autowired dependency in spring
public abstract class EntityService <T>{

  JpaRepository repository;

  public EntityService(JpaRepository repository){
    this.repository = repository;
  }

  @Transactional
  public T save(T entity){
    return (T)repository.save(entity);
  }

  public boolean delete(T entity){
    try {
      repository.delete(entity);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public T findOne(long id){

    return (T)repository.findOne(id);
  }

  public List<T> getAll(){
    return (List<T>)repository.findAll();
  }

}
