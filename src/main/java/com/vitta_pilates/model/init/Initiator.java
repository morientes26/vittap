package com.vitta_pilates.model.init;


import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.repository.ClassCategoryRepository;
import com.vitta_pilates.model.repository.RoleRepository;
import com.vitta_pilates.model.repository.TarifRepository;
import com.vitta_pilates.model.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Initiator  {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private TarifRepository tarifRepository;


  @Autowired
  private RoleRepository roleRepository;

  //FIXME: for this puprose will be use FLYWAY libs instead
  // https://flywaydb.org/
  @Bean
  InitializingBean initDbData() {
    return () -> {
      importUsersAndRoles();
      importClassCategories();
      importTarifs();
    };
  }

  private void importUsersAndRoles(){
    if (!this.isEmptyDB())
      return;

    Role admin = roleRepository.save(new Role(LevelOfAccess.ADMIN.name(),"Administrator"));
    Role pupil = roleRepository.save(new Role(LevelOfAccess.PUPIL.name(),"Pupil"));
    Role teacher = roleRepository.save(new Role(LevelOfAccess.TEACHER.name(),"Teacher"));
    Role secretary = roleRepository.save(new Role(LevelOfAccess.SECRETARY.name(),"Secretary"));

    userRepository.save(new User("admin","admin","test", admin));
    userRepository.save(new User("pupil","pupil","test", pupil));
    userRepository.save(new User("teacher","teacher","test", teacher));
    userRepository.save(new User("secretary","secretary","test", secretary));

  }

  private void importClassCategories(){
    classCategoryRepository.save(new ClassCategory("test category 1","desc 1"));
  }

  private void importTarifs(){
    tarifRepository.save(new Tarif("test tarif 1", "desc", 1.00, new Date()));
  }

  private boolean isEmptyDB(){
    return userRepository.findAll().isEmpty();
  }

}
