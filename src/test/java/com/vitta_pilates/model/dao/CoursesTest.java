package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.dao.ClassCategory;
import com.vitta_pilates.model.dao.ClassTemplate;
import com.vitta_pilates.model.repository.ClassCategoryRepository;
import com.vitta_pilates.model.repository.ClassTemplateRepository;
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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class CoursesTest {

  private ClassCategory classCategory;
  private ClassTemplate classTemplate;

  @Autowired
  private ClassCategoryRepository classCategoryRepository;

  @Autowired
  private ClassTemplateRepository classTemplateRepository;

  @Before
  public void setUp() throws Exception {

    this.classCategory = new ClassCategory("category 1", "desc of 1");
    this.classTemplate = new ClassTemplate("template 1",
            "desc of 1",
            this.classCategory);

  }

  @Test
  public void testClassCategoryCRUD() {

    int count = classCategoryRepository.findAll().size();

    ClassCategory cc = classCategoryRepository.save(this.classCategory);
    ClassCategory cc2 = classCategoryRepository.findOne(cc.getId());
    assertThat(cc, instanceOf(ClassCategory.class));
    assertThat(cc2.getName(), is("category 1"));

    classCategoryRepository.delete(cc2);
    List<ClassCategory> result = classCategoryRepository.findAll();
    assertThat(result.size(), is(count));

  }

  @Test
  public void testClassTemplateCRUD() {

    int count = classTemplateRepository.findAll().size();
    classCategoryRepository.save(this.classCategory);
    ClassTemplate ct = classTemplateRepository.save(this.classTemplate);
    ct = classTemplateRepository.findOne(ct.getId());

    assertThat(ct, instanceOf(ClassTemplate.class));
    assertThat(ct.getName(), is("template 1"));
    assertThat(ct.getType().getName(), is(this.classCategory.getName()));

    classTemplateRepository.delete(ct);
    List<ClassTemplate> result = classTemplateRepository.findAll();
    assertThat(result.size(), is(count));

  }
}
