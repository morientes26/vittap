package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.core.people.service.ClassService;
import com.vitta_pilates.core.people.service.PupilService;
import com.vitta_pilates.model.enumeration.ClassInstanceStatus;
import com.vitta_pilates.model.enumeration.ReccurenceType;
import com.vitta_pilates.model.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class PupilTest {

  private static final String PERSONAL_DATA = "Personal data ";
  Attendant attendant;
  List<Attendant> attendants = new ArrayList<>();
  Class clazz;
  Program program;

  @Autowired
  private AttendantRepository attendantRepository;

  @Autowired
  private PersonalDataRepository personalDataRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private ClassInstanceRepository classInstanceRepository;

  @Autowired
  private ProgramInstanceRepository programInstanceRepository;

  @Autowired
  private ClassTemplateRepository classTemplateRepository;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private TarifRepository tarifRepository;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private ProgramRepository programRepository;

  @Autowired
  private ProgramTemplateRepository programTemplateRepository;

  @Autowired
  private ClassService classService;

  @Autowired
  private PupilService pupilService;

  @Before
  public void setUp() throws Exception {

    IntStream.rangeClosed(1, 10).forEach(
            i -> attendants.add(
                    new Attendant(
                            new PersonalData(PERSONAL_DATA + i)
                    )
            )
    );
    attendant = attendants.get(0);
    attendantRepository.save(attendants);

    Room room = new Room("room1", "desc room 1");
    room = roomRepository.save(room);

    Schedule schedule = new Schedule(new Date(), new Date(), ReccurenceType.DAILY);
    schedule = scheduleRepository.save(schedule);

    ClassCategory classCategory = new ClassCategory("category 1", "desc of 1");
    classCategory = classCategoryRepository.save(classCategory);

    ClassTemplate classTemplate = new ClassTemplate("t 1","desc 1", classCategory);
    classTemplate = classTemplateRepository.save(classTemplate);

    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(new Date());
    c.add(java.util.Calendar.HOUR, -48);

    ClassInstance classInstance = new ClassInstance( c.getTime());

    clazz = new Class(schedule, classTemplate);
    clazz.setActive(true);
    clazz.addEvent(classInstance);
    clazz.setRoom(room);
    this.clazz = classRepository.save(this.clazz);

    classInstance.setClazz(clazz);
    classInstance.setAttendedPupils(attendants);
    classInstance = classInstanceRepository.save(classInstance);
    attendants = attendantRepository.save(attendants);

    List<ClassVisit> classVisit = new ArrayList<>();
    Tarif tarif = tarifRepository.save(new Tarif("tarif1", "some tarif number 1",1.00, new Date()));
    ProgramTemplate programTemplate = new ProgramTemplate(
            "template",
            "description of something",
            tarif,
            classVisit,
            c.getTime(),
            true);

    programTemplate = programTemplateRepository.save(programTemplate);

    this.program = new Program(schedule, programTemplate,  c.getTime(), 5.00);
    programRepository.save(program);
  }

//  @After
//  public void setDown() throws Exception {
//    attendantRepository.deleteAll();
//    //personalDataRepository.deleteAll();
//    roomRepository.deleteAll();
//    scheduleRepository.deleteAll();
//    classInstanceRepository.deleteAll();
//    programInstanceRepository.deleteAll();
//    classTemplateRepository.deleteAll();
//    classCategoryRepository.deleteAll();
//    tarifRepository.deleteAll();
//    classRepository.deleteAll();
//  }

//  @Test
//  public void findPupilByKeyword() throws Exception {
//    String keywords = "data";
//    List<Attendant> all = attendantRepository.findAll();
//    List<Attendant> result = pupilService.findByKeywords(keywords);
//    assertThat(result.size(), is(all.size()));
//  }

  @Test
  public void registerPupil() throws Exception {
    Attendant newcommer = pupilService.register(attendant);
    assertThat(newcommer.getPersonalData().getName(), is(PERSONAL_DATA + "1"));
  }

  @Test
  public void testExecuteClassInstance() throws Exception {
    Attendant teacher = attendant;
    Attendant pupil1 = attendants.get(1);
    Attendant pupil2 = attendants.get(2);
    ClassInstance result = classService.executeInstance(this.clazz.getInstances().get(0), teacher, pupil1, pupil2);
    assertThat(result.getStatus(), is(ClassInstanceStatus.EXECUTED));
  }

  @Test
  public void testClassCreate() throws Exception {
    assertThat(this.clazz.getRoom().getName(), is("room1"));
  }

  @Test
  public void testGetAllClassInstanceByAttendentAndDate() {
    Attendant pupil1 = attendants.get(1);
    ClassInstance ci = classService.executeInstance(this.clazz.getInstances().get(0), attendant, pupil1);

    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(new Date());
    c.add(java.util.Calendar.MONTH, -1);

    List<ClassInstance> result = classInstanceRepository.findByPupilAndDate(
            pupil1.getId(),
            c.getTime(),
            new Date());
    assertThat(result.isEmpty(), is(false));

    result = pupilService.findClassInstanceByPupilAndPeriod(pupil1, 0);
    assertThat(result.isEmpty(), is(false));
  }

  @Test
  public void testGetAllProgramInstanceByAttendentAndDate() {

    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(new Date());
    c.add(java.util.Calendar.HOUR, -48);

    Attendant pupil1 = new Attendant(new PersonalData("test","test"));
    pupil1 = attendantRepository.save(pupil1);
    ProgramInstance programInstance = new ProgramInstance(c.getTime(), ProgramInstanceStatus.EXECUTED);
    programInstance.setProgram(this.program);
    programInstance.setAttendedPupils(Arrays.asList(pupil1));
    programInstanceRepository.save(programInstance);

    List<ProgramInstance> result = pupilService.findProgramInstanceByPupilAndPeriod(
            pupil1, 0);
    assertThat(result.isEmpty(), is(false));
  }

  @Test
  public void testAddSkill() throws Exception {
    //todo
  }

  //todo...

}