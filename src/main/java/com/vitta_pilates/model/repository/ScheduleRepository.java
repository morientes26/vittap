package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


}
