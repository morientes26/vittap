package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  Attendance findOneByClassInstance(ClassInstance classInstance);
}
