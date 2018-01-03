package com.innovatrics.commons.vittap.model;

import com.innovatrics.commons.vittap.Application;
import com.innovatrics.commons.vittap.model.dao.*;
import com.innovatrics.commons.vittap.model.repository.ProgramRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProgramTest {

  private Program program;

  @Autowired
  private ProgramRepository programRepository;

  @Before
  public void setUp() throws Exception {
    Schedule schedule = new Schedule();
    schedule.setReccurenceType(ReccurenceType.MONTHY);
    List<ClassVisit> classVisit = new ArrayList<>();
    Tarif tarif = new Tarif("tarif1", "some tarif number 1",1.00, new Date());
    ProgramTemplate programTemplate = new ProgramTemplate(
            "template",
            "description of something",
            tarif,
            classVisit,
            new Date(),
            true);

    this.program = new Program(schedule, programTemplate, new Date(), 5.00);
  }

  @Test
  public void testProgramCRUD() {

    Program program = programRepository.save(this.program);
    Program program2 = programRepository.findOne(program.getId());

    assertThat(program, instanceOf(Program.class));
    assertThat(program2.getDiscount(), is(5.00));
    assertThat(program2.getProgramTemplate().getTarif().getName(), is("tarif1"));
    assertThat(program2.getSchedule().getReccurenceType(), is(ReccurenceType.MONTHY));

    programRepository.delete(program2);
    List<Program> result = programRepository.findAll();

    assertThat(result.isEmpty(), is(true));

  }
}