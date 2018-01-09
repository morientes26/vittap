package com.innovatrics.commons.vittap.model.init;


import com.innovatrics.commons.vittap.model.dao.ClassCategory;
import com.innovatrics.commons.vittap.model.dao.LevelOfAccess;
import com.innovatrics.commons.vittap.model.dao.Tarif;
import com.innovatrics.commons.vittap.model.dao.User;
import com.innovatrics.commons.vittap.model.repository.ClassCategoryRepository;
import com.innovatrics.commons.vittap.model.repository.TarifRepository;
import com.innovatrics.commons.vittap.model.repository.UserRepository;
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



  //FIXME: for this puprose will be use FLYWAY libs instead
  // https://flywaydb.org/
  @Bean
  InitializingBean initDbData() {
    return () -> {
      importUsers();
      importClassCategories();
      importTarifs();
    };
  }

  private void importUsers(){
    if (!this.isEmptyDB())
      return;

    userRepository.save(
            new User("test","test","test", LevelOfAccess.ADMIN)
    );
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
