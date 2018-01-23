package com.vitta_pilates.conf;

import com.vitta_pilates.core.people.service.ClassService;
import com.vitta_pilates.core.people.service.PupilService;
import com.vitta_pilates.core.people.service.TeacherService;
import com.vitta_pilates.core.studioadmin.service.*;
import com.vitta_pilates.model.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * I don't use default empty constructor for services. Every services is
 * inherited by EntityService so it is necessary to call constructor
 * of EntityService (super). If I have used default constructor of service,
 * than I would't need to make this configuration.
 */
@Configuration
public class ServiceInitializer {

  @Bean
  public PupilService pupilService(AttendantRepository attendantRepository){
    return new PupilService(attendantRepository);
  }

  @Bean
  public TeacherService teacherService(AttendantRepository attendantRepository){
    return new TeacherService(attendantRepository);
  }

  @Bean
  public ClassService classService(ClassRepository classRepository){
    return new ClassService(classRepository);
  }

  @Bean
  public ClassCategoryService classCategoryService(ClassCategoryRepository classCategoryRepository){
    return new ClassCategoryService(classCategoryRepository);
  }

  @Bean
  public ClassTemplateService classTemplateService(ClassTemplateRepository classTemplateRepository){
    return new ClassTemplateService(classTemplateRepository);
  }

  @Bean
  public ProgramTemplateService programTemplateService(ProgramTemplateRepository programTemplateRepository){
    return new ProgramTemplateService(programTemplateRepository);
  }

  @Bean
  public RoomService roomService(RoomRepository roomRepository){
    return new RoomService(roomRepository);
  }

  @Bean
  public LevelService levelService(LevelRepository levelRepository){
    return new LevelService(levelRepository);
  }

  @Bean
  public TarifService tarifService(TarifRepository tarifRepository){
    return new TarifService(tarifRepository);
  }
}
