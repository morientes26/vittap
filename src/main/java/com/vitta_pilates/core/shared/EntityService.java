package com.vitta_pilates.core.shared;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface EntityService <E>{

  @Transactional
  E save(E entity);

  @Transactional
  void delete(E entity);

  E findOne(long id);

  List<E> getAll();

  List<E> findByKeywords(String keyword);
}
