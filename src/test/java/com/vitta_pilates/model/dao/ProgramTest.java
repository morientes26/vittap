package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.enumeration.ReccurenceType;
import com.vitta_pilates.model.repository.ProgramRepository;
import com.vitta_pilates.model.repository.ProgramTemplateRepository;
import com.vitta_pilates.model.repository.ScheduleRepository;
import com.vitta_pilates.model.repository.TarifRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class ProgramTest {

  private Program program;

  @Autowired
  private ProgramRepository programRepository;

  @Autowired
  private ProgramTemplateRepository programTemplateRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private TarifRepository tarifRepository;

  @Before
  public void setUp() throws Exception {
    Schedule schedule = new Schedule();
    schedule.setReccurenceType(ReccurenceType.MONTHY);
    schedule = scheduleRepository.save(schedule);
    List<ClassVisit> classVisit = new ArrayList<>();

    Tarif tarif = tarifRepository.save(new Tarif("tarif1", "some tarif number 1",new BigDecimal(1.00), new Date()));
    ProgramTemplate programTemplate = new ProgramTemplate(
            "template",
            "description of something",
            tarif,
            classVisit,
            new Date(),
            true);
    programTemplate = programTemplateRepository.save(programTemplate);


    this.program = new Program(schedule, programTemplate, new Date(), new BigDecimal(5.00));
  }

  @Test
  public void testProgramCRUD() {

    int count = programRepository.findAll().size();

    Program program = programRepository.save(this.program);
    Program program2 = programRepository.findOne(program.getId());

    assertThat(program, instanceOf(Program.class));
    assertThat(program2.getDiscount(), is(5.00));
    assertThat(program2.getProgramTemplate().getTarif().getName(), is("tarif1"));
    assertThat(program2.getSchedule().getReccurenceType(), is(ReccurenceType.MONTHY));

    programRepository.delete(program2);
    List<Program> result = programRepository.findAll();

    assertThat(result.size(), is(count));

  }
}