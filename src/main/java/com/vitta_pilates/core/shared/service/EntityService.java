package com.vitta_pilates.core.shared.service;



import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;

//note: I use abstract class instead of interface because generic interface
//note: has problem with autowired dependency in spring
public abstract class EntityService <T>{

  @Transactional
  public T save(T entity){
    throw new NotImplementedException();
  }

  public boolean delete(T entity){
    throw new NotImplementedException();
  }

  public T findOne(long id){
    throw new NotImplementedException();
  }

  public List<T> getAll(){
    throw new NotImplementedException();
  }

  public List<T> findByKeywords(String keyword){
    throw new NotImplementedException();
  }
}
