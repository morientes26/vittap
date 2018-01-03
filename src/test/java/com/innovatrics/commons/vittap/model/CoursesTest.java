package com.innovatrics.commons.vittap.model;

import com.innovatrics.commons.vittap.Application;
import com.innovatrics.commons.vittap.model.dao.ClassCategory;
import com.innovatrics.commons.vittap.model.dao.ClassTemplate;
import com.innovatrics.commons.vittap.model.repository.ClassCategoryRepository;
import com.innovatrics.commons.vittap.model.repository.ClassTemplateRepository;
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
    ClassCategory cc = classCategoryRepository.save(this.classCategory);
    ClassCategory cc2 = classCategoryRepository.findOne(cc.getId());
    assertThat(cc, instanceOf(ClassCategory.class));
    assertThat(cc2.getName(), is("category 1"));

    classCategoryRepository.delete(cc2);
    List<ClassCategory> result = classCategoryRepository.findAll();
    assertThat(result.isEmpty(), is(true));

  }

  @Test
  public void testClassTemplateCRUD() {
    ClassTemplate ct = classTemplateRepository.save(this.classTemplate);
    ct = classTemplateRepository.findOne(ct.getId());

    assertThat(ct, instanceOf(ClassTemplate.class));
    assertThat(ct.getName(), is("template 1"));
    assertThat(ct.getType().getName(), is(this.classCategory.getName()));

    classTemplateRepository.delete(ct);
    List<ClassTemplate> result = classTemplateRepository.findAll();
    assertThat(result.isEmpty(), is(true));

  }
}
