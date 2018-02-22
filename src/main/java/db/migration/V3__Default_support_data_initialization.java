//package db.migration;
//
//import org.flywaydb.core.api.MigrationVersion;
//import org.flywaydb.core.api.migration.MigrationInfoProvider;
//import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class V3__Default_support_data_initialization implements SpringJdbcMigration, MigrationInfoProvider {
//
//  @Override
//  public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
//
//    jdbcTemplate.execute("INSERT INTO LEVEL (DESCRIPTION, NAME) VALUES ('Basic', '1')");
//    jdbcTemplate.execute("INSERT INTO LEVEL (DESCRIPTION, NAME) VALUES ('Intermediate', '2')");
//    jdbcTemplate.execute("INSERT INTO LEVEL (DESCRIPTION, NAME) VALUES ('Advenced', '3')");
//
//  }
//
//  @Override
//  public MigrationVersion getVersion() {
//    return MigrationVersion.fromVersion("3");
//
//  }
//
//  @Override
//  public String getDescription() {
//    return "Default support data";
//  }
//
//}