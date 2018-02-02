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

  /**
   * Select all ClassInstance of pupils by date range
   * @param attendant
   * @param fromD
   * @param toD
   * @return
   */
  @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.attendedPupils a " +
          "WHERE a.id in :attendent " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
  List<ClassInstance> findByPupilAndDate(
          @Param("attendent") Long attendant,
          @Param("fromD") Date fromD,
          @Param("toD") Date toD);

  /**
   * Select all ClassInstance of teacher by date range
   * @param attendant
   * @param fromD
   * @param toD
   * @return
   */
  @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.trueAttendingTeacher a " +
          "WHERE a.id in :attendent " +
          "AND ci.trueTime BETWEEN :fromD AND :toD")
  List<ClassInstance> findByTeacherAndDate(
          @Param("attendent") Long attendant,
          @Param("fromD") Date fromD,
          @Param("toD") Date toD);

  /**
   * Select all ClassInstance by ClassCategory and Level
   */
  @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.clazz c " +
          "JOIN c.event e " +
          "JOIN e.type t " +
          "JOIN e.requiredLevel l " +
          "WHERE upper(l.name) LIKE upper(CONCAT('%',:level,'%')) " +
          "AND upper(t.name) LIKE upper(CONCAT('%',:classCategory,'%'))")
  List<ClassInstance> findByClassCategoryAndLevel(
          @Param("classCategory") String classCategory,
          @Param("level") String level);

  /**
   * Select all ClassInstance by ClassCategory
   */
  @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.clazz c " +
          "JOIN c.event e " +
          "JOIN e.type t " +
          "WHERE "+
          "upper(t.name) LIKE upper(CONCAT('%',:classCategory,'%'))")
  List<ClassInstance> findByClassCategory(
          @Param("classCategory") String classCategory);

  /**
   * Select all ClassInstance by Teacher
   */
  @Query("SELECT ci from ClassInstance ci " +
          "JOIN ci.trueAttendingTeacher a " +
          "JOIN a.personalData p " +
          "WHERE "+
          "upper(p.name) LIKE upper(CONCAT('%',:teacher,'%'))")
  List<ClassInstance> findByTeacher(
          @Param("teacher") String teacher);

}
