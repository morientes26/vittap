package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.ProgramInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ProgramInstanceRepository extends JpaRepository<ProgramInstance, Long> {

    @Query("SELECT ci from ProgramInstance ci " +
          "JOIN ci.attendedPupils a " +
          "WHERE a.id in :attendent " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
    List<ProgramInstance> findByPupilAndDate(
            @Param("attendent") Long attendant,
            @Param("fromD") Date fromD,
            @Param("toD") Date toD);

}
