package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.dao.LevelOfAccess;
import com.vitta_pilates.model.dao.Role;
import com.vitta_pilates.model.dao.User;
import com.vitta_pilates.model.repository.RoleRepository;
import com.vitta_pilates.model.repository.UserRepository;
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
@ActiveProfiles("test")
public class UserTest {

  private User user;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Before
  public void setUp() throws Exception {
    this.user = new User();
    Role role = roleRepository.save(new Role(LevelOfAccess.ADMIN.name(),"test"));
    this.user.setRole(role);
  }

  @Test
  public void testUserCRUD() {

    int count = userRepository.findAll().size();

    User user = userRepository.save(this.user);
    User user2 = userRepository.findOne(user.getId());

    assertThat(user, instanceOf(User.class));
    assertThat(user2.getRole().getName(), is(LevelOfAccess.ADMIN.name()));

    userRepository.delete(user2);
    List<User> result = userRepository.findAll();

    assertThat(result.size(), is(count));

  }



}