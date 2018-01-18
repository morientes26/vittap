package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {


}
