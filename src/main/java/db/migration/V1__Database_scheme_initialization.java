package db.migration;

import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.MigrationInfoProvider;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class V1__Database_scheme_initialization implements SpringJdbcMigration, MigrationInfoProvider {

  @Override
  public void migrate(JdbcTemplate jdbcTemplate) throws Exception {

    jdbcTemplate.execute("drop table if exists attendance cascade");
    jdbcTemplate.execute("drop table if exists attendance_class_seat cascade");
    jdbcTemplate.execute("drop table if exists attendant cascade");
    jdbcTemplate.execute("drop table if exists class cascade");
    jdbcTemplate.execute("drop table if exists class_category cascade");
    jdbcTemplate.execute("drop table if exists class_instance cascade");
    jdbcTemplate.execute("drop table if exists class_seat cascade");
    jdbcTemplate.execute("drop table if exists class_seat_slot cascade");
    jdbcTemplate.execute("drop table if exists class_template cascade");
    jdbcTemplate.execute("drop table if exists class_visit cascade");
    jdbcTemplate.execute("drop table if exists level cascade");
    jdbcTemplate.execute("drop table if exists notification cascade");
    jdbcTemplate.execute("drop table if exists notification_settings cascade");
    jdbcTemplate.execute("drop table if exists personal_data cascade");
    jdbcTemplate.execute("drop table if exists program cascade");
    jdbcTemplate.execute("drop table if exists program_instance cascade");
    jdbcTemplate.execute("drop table if exists program_instance_attendent cascade");
    jdbcTemplate.execute("drop table if exists program_template cascade");
    jdbcTemplate.execute("drop table if exists reminder cascade");
    jdbcTemplate.execute("drop table if exists role cascade");
    jdbcTemplate.execute("drop table if exists room cascade");
    jdbcTemplate.execute("drop table if exists schedule cascade");
    jdbcTemplate.execute("drop table if exists skill cascade");
    jdbcTemplate.execute("drop table if exists tarif cascade");
    jdbcTemplate.execute("drop table if exists user_account cascade");
    jdbcTemplate.execute("drop table if exists user_account_notification cascade");
   // jdbcTemplate.execute("drop sequence hibernate_sequence");

    jdbcTemplate.execute("create table attendance (id int8 not null, class_instance_id int8, primary key (id))");
    jdbcTemplate.execute("create table attendance_class_seat (attendance_id int8 not null, class_seat_id int8 not null)");
    jdbcTemplate.execute("create table attendant (id int8 not null, pupil boolean not null, personal_data_id int8, user_account_id int8, primary key (id))");
    jdbcTemplate.execute("create table class (id int8 not null, active boolean not null, conducting_teacher_id int8, event_id int8, room_id int8, schedule_id int8, primary key (id))");
    jdbcTemplate.execute("create table class_category (id int8 not null, access_restriction boolean not null, attendance_required boolean not null, color varchar(255), description varchar(255), name varchar(255) not null, primary key (id))");
    jdbcTemplate.execute("create table class_instance (id int8 not null, description varchar(255), name varchar(255), status varchar(255), true_time timestamp, class_id int8, true_attending_teacher_id int8, primary key (id))");
    jdbcTemplate.execute("create table class_seat (id int8 not null, attendance_staus boolean not null, is_teacher boolean not null, fixed_id int8, temporary_id int8, primary key (id))");
    jdbcTemplate.execute("create table class_seat_slot (id int8 not null, status varchar(255), attendant_id int8, primary key (id))");
    jdbcTemplate.execute("create table class_template (id int8 not null, capacity int4 not null, description varchar(255), duration int4, name varchar(255) not null, required_level_id int8, type_id int8, primary key (id))");
    jdbcTemplate.execute("create table class_visit (id int8 not null, number int4 not null, program_template_id int8 not null, primary key (id))");
    jdbcTemplate.execute("create table level (id int8 not null, description varchar(255), name varchar(255), primary key (id))");
    jdbcTemplate.execute("create table notification (id int8 not null, occurence timestamp, type int4, primary key (id))");
    jdbcTemplate.execute("create table notification_settings (id int8 not null, type int4, primary key (id))");
    jdbcTemplate.execute("create table personal_data (id int8 not null, active boolean not null, additional_data varchar(255), name varchar(255), primary key (id))");
    jdbcTemplate.execute("create table program (id int8 not null, active boolean not null, date_of_issue timestamp, discount numeric(19, 2), program_template_id int8, schedule_id int8, primary key (id))");
    jdbcTemplate.execute("create table program_instance (id int8 not null, notes varchar(255), status varchar(255), true_time timestamp, program_id int8, true_attending_teacher_id int8, primary key (id))");
    jdbcTemplate.execute("create table program_instance_attendent (program_instance_id int8 not null, attendent_id int8 not null)");
    jdbcTemplate.execute("create table program_template (id int8 not null, active boolean not null, date_of_issue timestamp, description varchar(255), name varchar(255) not null, tarif_id int8, primary key (id))");
    jdbcTemplate.execute("create table reminder (id int8 not null, details varchar(255), name varchar(255), primary key (id))");
    jdbcTemplate.execute("create table role (id int8 not null, description varchar(255), name varchar(255) not null, primary key (id))");
    jdbcTemplate.execute("create table room (id int8 not null, description varchar(255), name varchar(255) not null, primary key (id))");
    jdbcTemplate.execute("create table schedule (id int8 not null, end_date timestamp, reccurence_type varchar(255), scheduled_day int4, scheduled_day_of_month int4 not null, scheduled_month int4, scheduled_time timestamp, start_date timestamp, notification_settings_id int8, primary key (id))");
    jdbcTemplate.execute("create table skill (id int8 not null, attendant_id int8 not null, category_id int8, level_id int8, primary key (id))");
    jdbcTemplate.execute("create table tarif (id int8 not null, date_of_issue timestamp, description varchar(255), name varchar(255) not null, value numeric(19, 2), primary key (id))");
    jdbcTemplate.execute("create table user_account (id int8 not null, login varchar(255), name varchar(255), password varchar(255), role_id int8, primary key (id))");
    jdbcTemplate.execute("create table user_account_notification (user_account_id int8 not null, notification_id int8 not null, primary key (user_account_id, notification_id))");
    jdbcTemplate.execute("alter table attendance add constraint FK_2xwsf9b3a8wojgx4ge9co2a67 foreign key (class_instance_id) references class_instance");
    jdbcTemplate.execute("alter table attendance_class_seat add constraint FK_c6fkl7aye5g5qbcep8aiwuwug foreign key (class_seat_id) references class_seat");
    jdbcTemplate.execute("alter table attendance_class_seat add constraint FK_2o5m8d77hxf9duj10xqdg4vu6 foreign key (attendance_id) references attendance");
    jdbcTemplate.execute("alter table attendant add constraint FK_fwjnvxqhl6mav3uoe2poabatd foreign key (personal_data_id) references personal_data");
    jdbcTemplate.execute("alter table attendant add constraint FK_qui8j3d1bpu7nwx1toby7ah9v foreign key (user_account_id) references user_account");
    jdbcTemplate.execute("alter table class add constraint FK_f83bx6968f8ia9wjpju56bs38 foreign key (conducting_teacher_id) references attendant");
    jdbcTemplate.execute("alter table class add constraint FK_68b7sclcrqmgwf3u9ujoujao9 foreign key (event_id) references class_template");
    jdbcTemplate.execute("alter table class add constraint FK_90x3cfjcncab71at5esuw5htj foreign key (room_id) references room");
    jdbcTemplate.execute("alter table class add constraint FK_ns0jgnrnkrrlgu8gdliikdapa foreign key (schedule_id) references schedule");
    jdbcTemplate.execute("alter table class_instance add constraint FK_if5d7bps937dknfulxu5ofwns foreign key (class_id) references class");
    jdbcTemplate.execute("alter table class_instance add constraint FK_e7ef70kfk53f2kav85cn9jalm foreign key (true_attending_teacher_id) references attendant");
    jdbcTemplate.execute("alter table class_seat add constraint FK_799lrgofa18ulua2lrgbpq9rp foreign key (fixed_id) references class_seat_slot");
    jdbcTemplate.execute("alter table class_seat add constraint FK_m36h5c3nnps4spybj1mfxaicx foreign key (temporary_id) references class_seat_slot");
    jdbcTemplate.execute("alter table class_seat_slot add constraint FK_sayuoefjv2ir80ud8wgge8vgw foreign key (attendant_id) references attendant");
    jdbcTemplate.execute("alter table class_template add constraint FK_p3uxv34kgh5fwf1k793o2h3c6 foreign key (required_level_id) references level");
    jdbcTemplate.execute("alter table class_template add constraint FK_gcgwr5lurdnx2xgcarr6oopi8 foreign key (type_id) references class_category");
    jdbcTemplate.execute("alter table class_visit add constraint FK_cja0mjai3bytgyiwktuc3sfjh foreign key (program_template_id) references program_template");
    jdbcTemplate.execute("alter table program add constraint FK_q61skn48g9v99725aibwb6euk foreign key (program_template_id) references program_template");
    jdbcTemplate.execute("alter table program add constraint FK_bufw5sr7903twp1bfyn2nwddv foreign key (schedule_id) references schedule");
    jdbcTemplate.execute("alter table program_instance add constraint FK_r7qw6218festrutteo3dwjuu4 foreign key (program_id) references program");
    jdbcTemplate.execute("alter table program_instance add constraint FK_iv8lls0gqxkt159sff04c5wva foreign key (true_attending_teacher_id) references attendant");
    jdbcTemplate.execute("alter table program_instance_attendent add constraint FK_1a8o9cfxk2wu0cgyy3gpu0rhp foreign key (attendent_id) references attendant");
    jdbcTemplate.execute("alter table program_instance_attendent add constraint FK_ibaubva2hvn9gs54r9iud4tdh foreign key (program_instance_id) references program_instance");
    jdbcTemplate.execute("alter table program_template add constraint FK_l29dc5c3t2s3wif7kpbxsqosp foreign key (tarif_id) references tarif");
    jdbcTemplate.execute("alter table schedule add constraint FK_sl1nqd1dc9lh68n2hpwgy3j4p foreign key (notification_settings_id) references notification_settings");
    jdbcTemplate.execute("alter table skill add constraint FK_sea3r0f460ipgmraivigstkp0 foreign key (attendant_id) references attendant");
    jdbcTemplate.execute("alter table skill add constraint FK_a9o0v9wgd0r69og794wg19eks foreign key (category_id) references class_category");
    jdbcTemplate.execute("alter table skill add constraint FK_q2jq9pqtq7ris6lta8dk65i4h foreign key (level_id) references level");
    jdbcTemplate.execute("alter table user_account add constraint FK_fiywq2x4mg0ht0oq58ihdshpe foreign key (role_id) references role");
    jdbcTemplate.execute("alter table user_account_notification add constraint FK_4jd3d80sc5wbdsp83ifd1x0ty foreign key (notification_id) references notification");
    jdbcTemplate.execute("alter table user_account_notification add constraint FK_3f6m5xf0ndtbw3lcobglmlccp foreign key (user_account_id) references user_account");
    jdbcTemplate.execute("create sequence hibernate_sequence");
  }

  @Override
  public MigrationVersion getVersion() {
    return MigrationVersion.fromVersion("1");

  }

  @Override
  public String getDescription() {
    return "Default database scheme initialization";
  }

}