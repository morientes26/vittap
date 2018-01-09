package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import com.innovatrics.commons.vittap.core.studioadmin.service.ClassCategoryService;
import com.innovatrics.commons.vittap.model.dao.ClassCategory;
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
public class ClassCategoryViewModel {

  private final Logger log = LoggerFactory.getLogger(ClassCategoryViewModel.class);

  private String keyword;
  private List<ClassCategory> classCategoryList;
  private ClassCategory selectedClassCategory;

  @WireVariable("classCategoryService")
  private ClassCategoryService service;

  @Command
  @NotifyChange("classCategoryList")
  public void search(){
    classCategoryList = service.findByNameOrDescription(keyword);
  }

  @Command
  @NotifyChange({"classCategoryList", "selectedClassCategory"})
  public void submit(){
    service.save(selectedClassCategory);
    log.info("Save classCategory {}", selectedClassCategory);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    service.delete(selectedClassCategory);
    log.info("Delete classCategory {}", selectedClassCategory);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"classCategoryList", "selectedClassCategory"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    classCategoryList = service.getAll();
    selectedClassCategory = new ClassCategory();
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public List<ClassCategory> getClassCategoryList() {
    return classCategoryList;
  }

  public void setClassCategoryList(List<ClassCategory> classCategoryList) {
    this.classCategoryList = classCategoryList;
  }

  public ClassCategory getSelectedClassCategory() {
    return selectedClassCategory;
  }

  public void setSelectedClassCategory(ClassCategory selectedClassCategory) {
    this.selectedClassCategory = selectedClassCategory;
  }

  public ClassCategoryService getService() {
    return service;
  }

  public void setService(ClassCategoryService service) {
    this.service = service;
  }
}
