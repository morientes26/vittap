package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.dao.attendance.ClassSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassSeatRepository extends JpaRepository<ClassSeat, Long> {


  @Query("SELECT c from ClassSeat c " +
          "join c.classSeats a " +
          "join c.fixed f " +
          "where a =:attendance " +
          "and f.attendant =:attendant")
  List<ClassSeat> findByAttendanceAndAttendantFixed(
          @Param("attendance") Attendance attendance,
          @Param("attendant") Attendant attendant);


  @Query("SELECT c from ClassSeat c " +
          "join c.classSeats a " +
          "join c.temporary f " +
          "where a =:attendance " +
          "and f.attendant =:attendant")
  List<ClassSeat> findByAttendanceAndAttendantTemporary(
          @Param("attendance") Attendance attendance,
          @Param("attendant") Attendant attendant);


}
