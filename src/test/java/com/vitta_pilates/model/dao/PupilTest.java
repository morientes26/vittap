package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.core.people.service.ClassService;
import com.vitta_pilates.core.people.service.PupilService;
import com.vitta_pilates.model.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

  @Autowired
  private PupilService pupilService;

  @Autowired
  private AttendantRepository attendantRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private ClassInstanceRepository classInstanceRepository;

  @Autowired
  private ClassTemplateRepository classTemplateRepository;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private ClassService classService;

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

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.HOUR, -48);

    ClassInstance classInstance = new ClassInstance( c.getTime());

    clazz = new Class(schedule, classTemplate);
    clazz.setActive(true);
    clazz.addEvent(classInstance);
    clazz.setRoom(room);
    this.clazz = classRepository.save(this.clazz);

    classInstance.setClazz(clazz);
    classInstance.setAttendedPupils(attendants);
    classInstance = classInstanceRepository.save(classInstance);
    attendantRepository.save(attendants);
  }

  @Test
  public void findPupilByKeyword() throws Exception {
    String keywords = "data";
    List<Attendant> all = attendantRepository.findAll();
    List<Attendant> result = pupilService.findByKeywords(keywords);
    assertThat(result.size(), is(all.size()));
  }

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

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.MONTH, -1);

    List<ClassInstance> result = classInstanceRepository.findByPupilAndDate(
            pupil1.getId(),
            c.getTime(),
            new Date());
    assertThat(result.isEmpty(), is(false));
  }

  @Test
  public void testAddSkill() throws Exception {
    //todo
  }

  //todo...

}