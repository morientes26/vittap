package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import com.innovatrics.commons.vittap.core.studioadmin.service.ClassCategoryService;
import com.innovatrics.commons.vittap.core.studioadmin.service.ClassTemplateService;
import com.innovatrics.commons.vittap.model.dao.ClassCategory;
import com.innovatrics.commons.vittap.model.dao.ClassTemplate;
import com.innovatrics.commons.vittap.model.dao.Level;
import com.innovatrics.commons.vittap.model.repository.LevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class ClassTemplateViewModel {

  private final Logger log = LoggerFactory.getLogger(ClassTemplateViewModel.class);

  private String keyword;
  private List<ClassTemplate> classTemplateList;
  private ClassTemplate selectedClassTemplate;

  private List<ClassCategory> listClassCategory;
  private ClassCategory selectedClassCategory;

  private List<Level> listLevel;
  private Level selectedLevel;

  @WireVariable("classTemplateService")
  private ClassTemplateService service;

  @WireVariable("classCategoryService")
  private ClassCategoryService serviceCategory;

  @WireVariable("levelRepository")
  private LevelRepository levelRepository;

  @Command
  @NotifyChange("classTemplateList")
  public void search(){
    classTemplateList = service.findByNameOrDescription(keyword);
  }

  @Command
  @NotifyChange({"classTemplateList", "selectedClassTemplate"})
  public void submit(){
    service.save(selectedClassTemplate);
    log.info("Save classTemplate {}", selectedClassTemplate);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    service.delete(selectedClassTemplate);
    log.info("Delete classTemplate {}", selectedClassTemplate);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"classTemplateList", "selectedClassTemplate"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    classTemplateList = service.getAll();
    listClassCategory = serviceCategory.getAll();
    selectedClassTemplate = new ClassTemplate();
    selectedClassCategory = new ClassCategory();
    listLevel = levelRepository.findAll();
  }


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public List<ClassTemplate> getClassTemplateList() {
    return classTemplateList;
  }

  public void setClassTemplateList(List<ClassTemplate> classTemplateList) {
    this.classTemplateList = classTemplateList;
  }

  public ClassTemplate getSelectedClassTemplate() {
    return selectedClassTemplate;
  }

  public void setSelectedClassTemplate(ClassTemplate selectedClassTemplate) {
    this.selectedClassTemplate = selectedClassTemplate;
  }

  public ClassTemplateService getService() {
    return service;
  }

  public void setService(ClassTemplateService service) {
    this.service = service;
  }

  public List<ClassCategory> getListClassCategory() {
    return listClassCategory;
  }

  public void setListClassCategory(List<ClassCategory> listClassCategory) {
    this.listClassCategory = listClassCategory;
  }

  public ClassCategory getSelectedClassCategory() {
    return selectedClassCategory;
  }

  public void setSelectedClassCategory(ClassCategory selectedClassCategory) {
    this.selectedClassCategory = selectedClassCategory;
  }

  public List<Level> getListLevel() {
    return listLevel;
  }

  public void setListLevel(List<Level> listLevel) {
    this.listLevel = listLevel;
  }

  public Level getSelectedLevel() {
    return selectedLevel;
  }

  public void setSelectedLevel(Level selectedLevel) {
    this.selectedLevel = selectedLevel;
  }

}
