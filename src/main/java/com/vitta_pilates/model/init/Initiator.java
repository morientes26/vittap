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

import java.time.LocalTime;
import java.util.*;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
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
  private LevelRepository levelRepository;

  @Autowired
  private ClassService classService;

  private List<ClassCategory> classCategories = new ArrayList<>();

  private List<Attendant> pupils;

  private List<ClassInstance> classInstances = new ArrayList<>();

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
    ClassCategory classCategory = new ClassCategory("Category 1","desc 1");
    classCategory.setColor(ClassCategory.GRAY);
    classCategories.add(classCategoryRepository.save(classCategory));

    ClassCategory classCategory2 = new ClassCategory("Category 2","desc 2");
    classCategory2.setColor(ClassCategory.BLUE);
    classCategories.add(classCategoryRepository.save(classCategory2));

    ClassCategory classCategory3 = new ClassCategory("Category 3","desc 3");
    classCategory3.setColor(ClassCategory.RED);
    classCategories.add(classCategoryRepository.save(classCategory2));
  }

  private void importTarifs(){
    tarifRepository.save(new Tarif("test tarif 1", "desc", 1.00, new Date()));
  }

  private void importAttendents(){
    List<Attendant> attendants = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(
            i -> attendants.add(
                    new Attendant(
                            new PersonalData(randomName())
                    )
            )
    );
    pupils = attendantRepository.save(attendants);
  }

  private void importClassInstance(){
    Room room = new Room("Room A", "desc room A");
    room = roomRepository.save(room);

    Level level = new Level("1", "Basic");
    Level level2 = new Level("2", "Intermediate");
    Level level3 = new Level("3", "Advenced");
    level = levelRepository.save(level);
    levelRepository.save(Arrays.asList(level2, level3));

    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    List<ClassTemplate> classTemplaties =new ArrayList<>();

    ClassTemplate classTemplate = new ClassTemplate("Custom", "desc 1", classCategories.get(0));
    classTemplate.setCapacity(5);
    classTemplate.setDuration(60);
    classTemplate.setRequiredLevel(level);
    classTemplaties.add(classTemplateRepository.save(classTemplate));

    ClassTemplate classTemplate2 = new ClassTemplate("Rumba template", "desc 2", classCategories.get(1));
    classTemplate2.setCapacity(5);
    classTemplate2.setDuration(60);
    classTemplate2.setRequiredLevel(level);
    classTemplaties.add(classTemplateRepository.save(classTemplate2));

    ClassTemplate classTemplate3 = new ClassTemplate("Chacha template ", "desc 3", classCategories.get(2));
    classTemplate3.setCapacity(2);
    classTemplate3.setDuration(5);
    classTemplate3.setRequiredLevel(level);
    classTemplaties.add(classTemplateRepository.save(classTemplate3));


    for (int i=0; i<10; i++){
      classInstances.add(new ClassInstance( todayRandomTime()));
      int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
      Class clazz = classRepository.save(instanceClass(schedule, classTemplaties.get(randomNum), classInstances.get(i), room, pupils.get(i)));
      classInstances.get(i).setClazz(clazz);
      classInstances.get(i).setAttendedPupils(pupils);
      classInstances.set(i,classInstanceRepository.save(classInstances.get(i)));
      Attendant teacher = pupils.get(0);
      teacher.setPupil(false);
      pupils.set(0, attendantRepository.save(teacher));

      if (i==7) {
        // do nothing
      } else if (i==8) {
        classService.executeInstance(classInstances.get(i),
                teacher,
                pupils.get(1),
                pupils.get(2),
                pupils.get(3),
                pupils.get(4)
        );
      } else if (i==9) {
        classService.executeInstance(classInstances.get(i),
                teacher,
                pupils.get(1),
                pupils.get(2),
                pupils.get(3),
                pupils.get(4)
        );
      } else {
        classService.executeInstance(classInstances.get(i), teacher, pupils.get(1), pupils.get(2), pupils.get(3));
      }
    }

  }


  public static Class instanceClass(Schedule schedule,
                                     ClassTemplate classTemplate,
                                     ClassInstance classInstance,
                                     Room room,
                                     Attendant teacher){

    Class clazz = new Class(schedule, classTemplate);
    clazz.setRoom(room);
    clazz.setActive(true);
    clazz.addEvent(classInstance);
    clazz.setConductingTeacher(teacher);
    return clazz;
  }

  public static Class instanceClass(
          Schedule schedule,
          ClassTemplate classTemplate){

    return new Class(schedule, classTemplate);
  }

  public static ClassInstance generateClassInstance(Class clazz){
    ClassInstance classInstance = new ClassInstance( todayRandomTime());
    classInstance.setClazz(clazz);
    return classInstance;

  }

  private void importProgramInstance() {

    Calendar c = Calendar.getInstance();
    c.setTime(todayRandomTime());
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
    Schedule schedule = new Schedule(todayRandomTime(), todayRandomTime(), ReccurenceType.DAILY);
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

  private static Date todayRandomTime(){
    int randomNum = ThreadLocalRandom.current().nextInt(9, 22 + 1);
    Calendar c = Calendar.getInstance();
    c.setTimeZone(TimeZone.getTimeZone("America/Montevideo"));
    c.setTime(new Date());
    c.set(Calendar.HOUR,0);
    c.set(Calendar.MINUTE,0);
    c.add(Calendar.HOUR, randomNum);
    return c.getTime();
  }

  private static String randomName(){
    String[] firstname = {"Michal", "Peter", "Martin", "John", "Emm", "Jozef","Robert","Dan"};
    String[] lastname = {"Zajacovic","Kladivko","Barnabak","Morka","Hasin","Laboreta","Vidla","Urkan"};
    int randomNum = ThreadLocalRandom.current().nextInt(0, 7);
    int randomNum2 = ThreadLocalRandom.current().nextInt(0, 7);
    return firstname[randomNum] + " " + lastname[randomNum2];
  }

}
