package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.ClassInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface ClassInstanceRepository extends JpaRepository<ClassInstance, Long> {

    @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.attendedPupils a " +
          "WHERE a.id in :attendent " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
  List<ClassInstance> findByPupilAndDate(
          @Param("attendent") Long attendant,
          @Param("fromD") Date fromD,
          @Param("toD") Date toD);

}
