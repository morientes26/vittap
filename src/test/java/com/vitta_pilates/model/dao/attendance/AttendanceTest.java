package com.vitta_pilates.model.dao.attendance;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.enumeration.ClassSeatSlotStatus;
import com.vitta_pilates.model.init.Initiator;
import com.vitta_pilates.model.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class AttendanceTest {

  private Attendant attendant;
  private List<ClassSeat> classSeats = new ArrayList<>();
  private ClassInstance classInstance;

  @Autowired
  private AttendanceRepository attendanceRepository;

  @Autowired
  private ClassInstanceRepository classInstanceRepository;

  @Autowired
  private ClassSeatSlotRepository classSeatSlotRepository;

  @Autowired
  private ClassSeatRepository classSeatRepository;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private ClassTemplateRepository classTemplateRepository;

  @Autowired
  private AttendantRepository attendantRepository;

  @Before
  public void setUp() throws Exception {

    PersonalData personalData = new PersonalData("test", "test");
    this.attendant = attendantRepository.save(new Attendant(personalData));

    ClassSeatSlot classSeatSlot = new ClassSeatSlot();
    classSeatSlot.setAttendant(this.attendant);
    classSeatSlot.setStatus(ClassSeatSlotStatus.EMPTY);
    classSeatSlot = classSeatSlotRepository.save(classSeatSlot);

    ClassSeatSlot classSeatSlot2 = new ClassSeatSlot();
    classSeatSlot2.setAttendant(this.attendant);
    classSeatSlot2.setStatus(ClassSeatSlotStatus.OCCUPIED);
    classSeatSlot2 = classSeatSlotRepository.save(classSeatSlot2);

    ClassSeat classSeat = new ClassSeat();
    classSeat.setFixed(classSeatSlot);
    classSeat.setTemporary(classSeatSlot2);
    classSeat.setAttendanceStaus(true);

    ClassSeat classSeat2 = new ClassSeat();
    classSeat2.setFixed(classSeatSlot);
    classSeat2.setTemporary(classSeatSlot2);
    classSeat2.setAttendanceStaus(true);

    classSeats.add(classSeatRepository.save(classSeat));
    classSeats.add(classSeatRepository.save(classSeat2));

    ClassCategory classCategory = classCategoryRepository.save(
            new ClassCategory("test","test"));

    Schedule schedule = scheduleRepository.save(new Schedule(new Date(), new Date()));

    ClassTemplate classTemplate = classTemplateRepository.save(
            new ClassTemplate("test","test", classCategory));

    Class clazz = classRepository.save(Initiator.instanceClass(schedule,classTemplate));
    this.classInstance = classInstanceRepository.save(
            Initiator.generateClassInstance(clazz)
    );

  }

  @Test
  public void testCreateAttendance() {
    int count = attendanceRepository.findAll().size();

    Attendance attendance = new Attendance();
    attendance.setClassInstance(this.classInstance);
    attendance.setTeacher(this.classSeats.get(0));
    attendance.setPupils(Collections.singletonList(this.classSeats.get(1)));
    attendance = attendanceRepository.save(attendance);

    List<Attendance> result = attendanceRepository.findAll();
    assertThat(result.size(), is(count+1));
    assertThat(attendance.getTeacher().getFixed().getStatus(), is(ClassSeatSlotStatus.EMPTY));
    assertThat(attendance.getPupils().get(0).getTemporary().getStatus(), is(ClassSeatSlotStatus.OCCUPIED));

    // test pupils vs seats n:m relationship
    attendance = attendanceRepository.findOne(attendance.getId());
    assertThat(attendance.getPupils().size(), is(1));
  }

}
