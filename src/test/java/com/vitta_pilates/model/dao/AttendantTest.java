package com.vitta_pilates.model.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by  ??_?¬ morientes on 15/01/2018.
 */
public class AttendantTest {

  private static final String PERSONAL_DATA = "Personal data ";
  Attendant attendant;
  List<Attendant> attendants = new ArrayList<>();

  @Before
  public void setUp() throws Exception {

    IntStream.rangeClosed(0, 10).forEach(
            i -> attendants.add(
                    new Attendant(
                            new PersonalData(PERSONAL_DATA + i)
                    )
            )
    );
    attendant = attendants.get(0);

  }

  @Test
  public void registerUser() throws Exception {

  }

  @Test
  public void buyCourse() throws Exception {

  }

  @Test
  public void addSkill() throws Exception {

  }

  @Test
  public void listAllPupilAttendances() throws Exception {

  }

  @Test
  public void listAllTeacherAttendances() throws Exception {

  }

}