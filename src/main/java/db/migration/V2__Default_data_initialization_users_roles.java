//package db.migration;
//
//import org.flywaydb.core.api.MigrationVersion;
//import org.flywaydb.core.api.migration.MigrationInfoProvider;
//import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class V2__Default_data_initialization_users_roles implements SpringJdbcMigration, MigrationInfoProvider {
//
//  // not working because of issue of autowired dependency
//  //private RoleRepository roleRepository = SpringUtility.getBean(RoleRepository.class);
//  //private UserRepository userRepository = SpringUtility.getBean(UserRepository.class);
//
//  @Override
//  public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
//
//   // jdbcTemplate.execute("select nextval ('hibernate_sequence')");
//    //jdbcTemplate.execute("INSERT INTO public.role (description, name) VALUES ('Administrator', 'ADMIN')");
//
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO ROLE (DESCRIPTION, NAME) VALUES ('Pupil', 'PUPIL')");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO ROLE (DESCRIPTION, NAME) VALUES ('Teacher', 'TEACHER')");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO ROLE (DESCRIPTION, NAME) VALUES ('Secretary', 'SECRETARY')");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO USER_ACCOUNT (LOGIN, NAME, PASSWORD, ROLE_ID) VALUES ('admin', 'admin', 'test', 1)");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO USER_ACCOUNT (LOGIN, NAME, PASSWORD, ROLE_ID) VALUES ('pupil', 'pupil', 'test', 2)");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO USER_ACCOUNT (LOGIN, NAME, PASSWORD, ROLE_ID) VALUES ('teacher', 'teacher', 'test', 3)");
////
////    jdbcTemplate.execute("select nextval ('hibernate_sequence')");
////    jdbcTemplate.execute("INSERT INTO USER_ACCOUNT (LOGIN, NAME, PASSWORD, ROLE_ID) VALUES ('secretary', 'secretary', 'test', 4)");
//  }
//
//  @Override
//  public MigrationVersion getVersion() {
//    return MigrationVersion.fromVersion("2");
//
//  }
//
//  @Override
//  public String getDescription() {
//    return "Default data users and roles ";
//  }
//
//
//// not working because of issue of autowired dependency
////  private void importUsersAndRoles(){
////
////    Role admin = roleRepository.save(new Role(LevelOfAccess.ADMIN.name(),"Administrator"));
////    Role pupil = roleRepository.save(new Role(LevelOfAccess.PUPIL.name(),"Pupil"));
////    Role teacher = roleRepository.save(new Role(LevelOfAccess.TEACHER.name(),"Teacher"));
////    Role secretary = roleRepository.save(new Role(LevelOfAccess.SECRETARY.name(),"Secretary"));
////
////    userRepository.save(new UserAccount("admin","admin","test", admin));
////    userRepository.save(new UserAccount("pupil","pupil","test", pupil));
////    userRepository.save(new UserAccount("teacher","teacher","test", teacher));
////    userRepository.save(new UserAccount("secretary","secretary","test", secretary));
////
////  }
//
//}