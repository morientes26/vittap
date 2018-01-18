package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Long> {

  @Query("Select a from Attendant a " +
          "join a.personalData p " +
          "where upper(p.name) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<Attendant> findAllByName(@Param("keyword") String keyword);


//  @Query("Select a from Attendant a " +
//          "join a.personalData p " +
//          "join a.classInstance ci "+
//          "join a.clazz c "+
//          "where upper(p.name) LIKE upper(CONCAT('%',:keyword,'%'))")
//  List<Attendant> findAllPupilByName(@Param("keyword") String keyword);

  //todo: needs to implement sql query
  @Query("Select a from Attendant a " +
          "left join a.personalData p " +
          "where p.active = true")
  List<Attendant> findNonEnrolled();

  //todo: needs to implement sql query
  @Query("Select a from Attendant a " +
          "left join a.personalData p " +
          "where p.active = false")
  List<Attendant> findOpenDepts();
}
