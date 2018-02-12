package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.attendance.ClassSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassSeatRepository extends JpaRepository<ClassSeat, Long> {


}
