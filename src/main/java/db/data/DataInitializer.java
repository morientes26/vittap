package db.data;


import com.vitta_pilates.core.people.service.ClassService;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.dao.attendance.ClassSeat;
import com.vitta_pilates.model.dao.attendance.ClassSeatSlot;
import com.vitta_pilates.model.enumeration.LevelOfAccess;
import com.vitta_pilates.model.enumeration.ReccurenceType;
import com.vitta_pilates.model.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Seed data management
 */
@Component
public class DataInitializer {

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
  private ClassSeatSlotRepository classSeatSlotRepository;

  @Autowired
  private ClassSeatRepository classSeatRepository;

  @Autowired
  AttendanceRepository attendanceRepository;

  @Autowired
  private ClassService classService;

  private List<ClassCategory> classCategories = new ArrayList<>();

  private List<Attendant> pupils;

  private List<ClassInstance> classInstances = new ArrayList<>();


  @Value("${db.data.seed}")
  private Boolean dbDataSeed;


  @Bean
  public InitializingBean seed() {
    return () -> {
      if (dbDataSeed) {
        importUsersAndRoles();
        importLevel();
        importClassCategories();
        importTarifs();
        importAttendents();
        importClassInstance();
      // importProgramInstance();
      // importEventAttendance(3);
      }
    };
  }

  private void importUsersAndRoles(){
    if (!this.isEmptyDB(roleRepository))
      return;

    Role admin = roleRepository.save(new Role(LevelOfAccess.ADMIN.name(),"Administrator"));
    Role pupil = roleRepository.save(new Role(LevelOfAccess.PUPIL.name(),"Pupil"));
    Role teacher = roleRepository.save(new Role(LevelOfAccess.TEACHER.name(),"Teacher"));
    Role secretary = roleRepository.save(new Role(LevelOfAccess.SECRETARY.name(),"Secretary"));

    if (!this.isEmptyDB(userRepository))
      return;

    userRepository.save(new UserAccount("admin","admin","test", admin));
    userRepository.save(new UserAccount("pupil","pupil","test", pupil));
    userRepository.save(new UserAccount("teacher","teacher","test", teacher));
    userRepository.save(new UserAccount("secretary","secretary","test", secretary));

  }

  private void importClassCategories(){

    if (!this.isEmptyDB(classCategoryRepository))
      return;

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

    if (!this.isEmptyDB(tarifRepository))
      return;

    tarifRepository.save(new Tarif("test tarif 1", "desc", new BigDecimal(1.00), new Date()));
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

  private void importLevel(){

    if (!this.isEmptyDB(levelRepository))
      return;

    Level level = new Level("1", "Basic");
    Level level2 = new Level("2", "Intermediate");
    Level level3 = new Level("3", "Advenced");
    levelRepository.save(Arrays.asList(level, level2, level3));

  }

  private void importClassInstance(){

    if (!this.isEmptyDB(classTemplateRepository))
      return;

    if (!this.isEmptyDB(classInstanceRepository))
      return;

    Room room = new Room("Room A", "desc room A");
    room = roomRepository.save(room);

    Level level = levelRepository.findAll().get(0);

    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    List<ClassTemplate> classTemplaties =new ArrayList<>();

    ClassTemplate classTemplate = new ClassTemplate("Custom", "desc 1", classCategories.get(0));
    classTemplate.setCapacity(5);
    classTemplate.setDuration(60);
    classTemplate.setRequiredLevel(level);
    classTemplate = classTemplateRepository.save(classTemplate);

    classInstances.add(new ClassInstance( todayRandomTime()));
    Class clazz = classRepository.save(instanceClass(schedule, classTemplate, classInstances.get(0), room, pupils.get(0)));
    classInstances.get(0).setClazz(clazz);
   // classInstances.get(i).setAttendedPupils(pupils);
    classInstances.set(0,classInstanceRepository.save(classInstances.get(0)));
    Attendant teacher = pupils.get(0);
    teacher.setPupil(false);
    pupils.set(0, attendantRepository.save(teacher));

    classService.executeInstance(classInstances.get(0), teacher, pupils.get(1), pupils.get(2), pupils.get(3));

  }


  private static Class instanceClass(Schedule schedule,
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

  public void importEventAttendance(int count){

    if (!this.isEmptyDB(attendanceRepository))
      return;

    for (int i=0;i<count;i++) {
      ClassSeatSlot slot = new ClassSeatSlot(pupils.get(i));
      //slot = classSeatSlotRepository.save(slot);
      ClassSeat seat = new ClassSeat();
      seat.setTeacher(true);
      seat.setFixed(slot);
      seat = classSeatRepository.save(seat);
      Attendance attendance = new Attendance();
      attendance.setClassInstance(classInstances.get(i));
      attendance.getClassSeats().add(seat);
      attendanceRepository.save(attendance);
    }
  }

  private void importProgramInstance() {

    if (!this.isEmptyDB(programTemplateRepository))
      return;

    if (!this.isEmptyDB(programInstanceRepository))
      return;

    Calendar c = Calendar.getInstance();
    c.setTime(todayRandomTime());
    c.add(Calendar.HOUR, -48);

    List<ClassVisit> classVisit = new ArrayList<>();
    Tarif tarif = tarifRepository.save(new Tarif("tarif A", "some tarif number A", new BigDecimal(1.00), new Date()));
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

    Program program = new Program(schedule, programTemplate,  c.getTime(), new BigDecimal(5.00));
    programRepository.save(program);

    ProgramInstance programInstance = new ProgramInstance(c.getTime(), ProgramInstanceStatus.EXECUTED);
    programInstance.setProgram(program);
    programInstance.setAttendedPupils(Arrays.asList(pupils.get(1)));
    programInstance.setTrueAttendingTeacher(pupils.get(0));
    programInstanceRepository.save(programInstance);
  }

  private boolean isEmptyDB(JpaRepository repository){
    List test = repository.findAll();
    if (test==null)
      return false;
    return test.isEmpty();
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
