package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {



}
