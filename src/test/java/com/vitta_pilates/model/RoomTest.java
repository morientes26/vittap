package com.vitta_pilates.model;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.dao.Room;
import com.vitta_pilates.model.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("dev")
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

    int count = roomRepository.findAll().size();

    Room room = roomRepository.save(this.room);
    Room room2 = roomRepository.findOne(room.getId());

    assertThat(room, instanceOf(Room.class));
    assertThat(room2.getDescription(), is("description of room 1"));

    roomRepository.delete(room2);
    List<Room> result = roomRepository.findAll();

    assertThat(result.size(), is(count));

  }

}