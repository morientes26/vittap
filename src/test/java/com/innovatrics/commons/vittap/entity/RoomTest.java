package com.innovatrics.commons.vittap.entity;

import com.innovatrics.commons.vittap.Application;
import com.innovatrics.commons.vittap.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RoomTest {

  private Room room;

  @Autowired
  private RoomRepository roomRepository;

  @Before
  public void setUp() throws Exception {
    this.room = new Room("room 1", "description of room 1");
  }

  @Test
  public void testRoomCRUD() {

    Room room = roomRepository.save(this.room);
    Room room2 = roomRepository.findOne(room.getId());

    assertThat(room, instanceOf(User.class));
    assertThat(room2.getDescription(), is("description of room 1"));

    roomRepository.delete(room2);
    List<Room> result = roomRepository.findAll();

    assertThat(result.isEmpty(), is(true));

  }

}