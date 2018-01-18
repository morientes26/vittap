package com.vitta_pilates.core.shared.service;



import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

//note: I use abstract class instead of interface because generic interface
//note: has problem with autowired dependency in spring
public abstract class EntityService <T>{

  @Transactional
  T save(T entity){
    throw new NotImplementedException();
  }

  @Transactional
  void delete(T entity){
    throw new NotImplementedException();
  }

  T findOne(long id){
    throw new NotImplementedException();
  }

  List<T> getAll(){
    throw new NotImplementedException();
  }

  List<T> findByKeywords(String keyword){
    throw new NotImplementedException();
  }
}
