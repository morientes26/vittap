package com.vitta_pilates.model.init;


import com.vitta_pilates.core.people.service.ClassService;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.enumeration.LevelOfAccess;
import com.vitta_pilates.model.enumeration.ReccurenceType;
import com.vitta_pilates.model.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.Calendar;
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

  @Autowired
  private ProgramTemplateRepository programTemplateRepository;

  @Autowired
  private ProgramRepository programRepository;

  @Autowired
  private ProgramInstanceRepository programInstanceRepository;

  @Autowired
  private ClassService classService;

  private ClassCategory classCategory;

  private List<Attendant> pupils;
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
      importProgramInstance();
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
    pupils = attendantRepository.save(attendants);
  }

  private void importClassInstance(){
    Room room = new Room("Room A", "desc room A");
    room = roomRepository.save(room);

    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    ClassTemplate classTemplate = new ClassTemplate("template 1","desc 1", classCategory);
    classTemplate = classTemplateRepository.save(classTemplate);

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.HOUR, -48);

    ClassInstance classInstance = new ClassInstance( c.getTime());

    Class clazz = new Class(schedule, classTemplate);
    clazz.setActive(true);
    clazz.addEvent(classInstance);
    clazz.setRoom(room);
    clazz = classRepository.save(clazz);

    classInstance.setClazz(clazz);
    classInstance.setAttendedPupils(pupils);
    classInstance = classInstanceRepository.save(classInstance);

    Attendant teacher = pupils.get(0);
    teacher.setPupil(false);
    pupils.set(0, attendantRepository.save(teacher));

    classService.executeInstance(classInstance, teacher, pupils.get(1), pupils.get(2), pupils.get(3));

  }

  private void importProgramInstance() {

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.HOUR, -48);

    List<ClassVisit> classVisit = new ArrayList<>();
    Tarif tarif = tarifRepository.save(new Tarif("tarif A", "some tarif number A",1.00, new Date()));
    ProgramTemplate programTemplate = new ProgramTemplate(
            "template",
            "description of something",
            tarif,
            classVisit,
            c.getTime(),
            true);

    programTemplate = programTemplateRepository.save(programTemplate);
    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    Program program = new Program(schedule, programTemplate,  c.getTime(), 5.00);
    programRepository.save(program);

    ProgramInstance programInstance = new ProgramInstance(c.getTime(), ProgramInstanceStatus.EXECUTED);
    programInstance.setProgram(program);
    programInstance.setAttendedPupils(Arrays.asList(pupils.get(1)));
    programInstance.setTrueAttendingTeacher(pupils.get(0));
    programInstanceRepository.save(programInstance);
  }

  private boolean isEmptyDB(){
    return userRepository.findAll().isEmpty();
  }

}
