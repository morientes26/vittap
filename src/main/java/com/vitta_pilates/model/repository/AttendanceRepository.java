package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  Attendance findOneByClassInstance(ClassInstance classInstance);

  /**
   * Find all Attendance by pupil and date
   */
  @Query("SELECT a from Attendance a " +
          "LEFT JOIN a.classInstance ci " +
          "LEFT JOIN a.classSeats s " +
          "LEFT JOIN s.fixed f " +
          "LEFT JOIN s.temporary t " +
          "LEFT JOIN f.attendant aa " +
          "LEFT JOIN t.attendant ab " +
          "WHERE (aa.id =:attendant " +
          "OR ab.id =:attendant) " +
          "AND s.isTeacher=0 " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
  List<Attendance> findByPupilAndDate(
          @Param("attendant") Long attendant,
          @Param("fromD") Date fromD,
          @Param("toD") Date toD);


  /**
   * Find all Attendance by teacher and date
   */
  @Query("SELECT a from Attendance a " +
          "LEFT JOIN a.classInstance ci " +
          "LEFT JOIN a.classSeats s " +
          "LEFT JOIN s.fixed f " +
          "LEFT JOIN s.temporary t " +
          "LEFT JOIN f.attendant aa " +
          "LEFT JOIN t.attendant ab " +
          "WHERE (aa.id =:attendant " +
          "OR ab.id =:attendant) " +
          "AND s.isTeacher=1 " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
  List<Attendance> findByTeacherAndDate(
          @Param("attendant") Long attendant,
          @Param("fromD") Date fromD,
          @Param("toD") Date toD);

}