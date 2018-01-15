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
          "where upper(a.personalData.name) LIKE upper(CONCAT('%',:keyword,'%')) " +
          "OR upper(a.user.name) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<Attendant> findByName(@Param("keyword") String keyword);

}
