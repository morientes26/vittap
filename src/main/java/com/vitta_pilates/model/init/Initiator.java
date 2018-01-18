package com.vitta_pilates.model.init;


import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class Initiator  {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private TarifRepository tarifRepository;

  @Autowired
  private AttendantRepository attendantRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private ClassTemplateRepository classTemplateRepository;

  @Autowired
  private ClassInstanceRepository classInstanceRepository;

  @Autowired
  private ClassRepository classRepository;

  private ClassCategory classCategory;

  //FIXME: for this puprose will be use FLYWAY libs instead
  // https://flywaydb.org/
  @Bean
  InitializingBean initDbData() {
    return () -> {
      importUsersAndRoles();
      importClassCategories();
      importTarifs();
      importAttendents();
      importClassInstance();
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
    classCategory = classCategoryRepository.save(new ClassCategory("test category 1","desc 1"));
  }

  private void importTarifs(){
    tarifRepository.save(new Tarif("test tarif 1", "desc", 1.00, new Date()));
  }

  private void importAttendents(){
    List<Attendant> attendants = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(
            i -> attendants.add(
                    new Attendant(
                            new PersonalData("personal data " + i)
                    )
            )
    );
    attendantRepository.save(attendants);
  }

  private void importClassInstance(){
    Room room = new Room("room1", "desc room 1");
    room = roomRepository.save(room);

    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    ClassTemplate classTemplate = new ClassTemplate("t 1","desc 1", classCategory);
    classTemplate = classTemplateRepository.save(classTemplate);

    ClassInstance classInstance = new ClassInstance( new Date());

    Class clazz = new Class(schedule, classTemplate);
    clazz.setActive(true);
    clazz.addEvent(classInstance);
    clazz.setRoom(room);
    clazz = classRepository.save(clazz);

    classInstance.setClazz(clazz);
    classInstance = classInstanceRepository.save(classInstance);
  }

  private boolean isEmptyDB(){
    return userRepository.findAll().isEmpty();
  }

}
