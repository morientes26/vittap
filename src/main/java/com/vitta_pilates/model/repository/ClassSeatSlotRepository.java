package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.attendance.ClassSeatSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassSeatSlotRepository extends JpaRepository<ClassSeatSlot, Long> {


}
