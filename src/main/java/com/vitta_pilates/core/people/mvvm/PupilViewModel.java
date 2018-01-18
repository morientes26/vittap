package com.vitta_pilates.core.people.mvvm;

import com.vitta_pilates.core.people.service.PupilService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.FilterData;
import com.vitta_pilates.model.dao.PersonalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class PupilViewModel {

  private final Logger log = LoggerFactory.getLogger(PupilViewModel.class);

  private FilterData filter = FilterData.ALL;
  private List<FilterData> listFilter = Arrays.asList(
                                            FilterData.ALL,
                                            FilterData.NON_ENROLLED,
                                            FilterData.OPEN_DEBTS
                                        );
  private String keyword = "";
  private List<Attendant> attendentList;
  private Attendant selected;

  private int mounth = 1;

  private List<ClassInstance> pupilClassInstances;
  private ClassInstance selectedClassInstance;

  @WireVariable("pupilService")
  private PupilService service;

  @Command
  @NotifyChange("attendentList")
  public void search(){
    log.debug("Search attendents by {} {}", keyword, filter);
    attendentList = service.findByFilterAndKeywords(filter, keyword);
  }

  @Command
  @NotifyChange({"selected", "attendentList"})
  public void submit(){
    service.save(selected);
    log.info("Save pupil {}", selected);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }
  @Command
  @NotifyChange({"selected", "attendentList"})
  public void changeStatus(){
    selected = service.changeStatus(selected);
    log.info("Change status pupil {}", selected);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    service.delete(selected);
    log.info("Delete pupil {}", selected);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"pupilClassInstances", "selectedClassInstance"})
  public void calendarControlClassInstance(@BindingParam( "direction") String direction){
    if (direction.equals("left")){
      log.debug("left");
      mounth-=1;
      pupilClassInstances = service.findClassInstanceByPupilAndPeriod(selected, mounth);
    } else {
      log.debug("right");
      mounth+=1;
      pupilClassInstances = service.findClassInstanceByPupilAndPeriod(selected, mounth);
    }
  }

  @Command
  @NotifyChange({"pupilClassInstances", "selectedClassInstance"})
  public void updateTabsData () {
    mounth = 1;
    pupilClassInstances = pupilClassInstances = service.findClassInstanceByPupilAndPeriod(
            selected, mounth);
    selectedClassInstance = new ClassInstance();
  }


  @Init
  public void init(){
    clear();
    //fixme:
    //pupilClassInstances = service.findClassInstanceByPupilAndPeriod(null,null);
    //selectedClassInstance = new ClassInstance();
  }

  private void clear(){
    keyword = "";
    attendentList = service.getAll();
    selected = new Attendant(new PersonalData());
    pupilClassInstances = new ArrayList<>();
    mounth = 1;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public List<Attendant> getAttendentList() {
    return attendentList;
  }

  public void setAttendentList(List<Attendant> attendentList) {
    this.attendentList = attendentList;
  }

  public Attendant getSelected() {
    return selected;
  }

  public void setSelected(Attendant selected) {
    this.selected = selected;
  }

  public PupilService getService() {
    return service;
  }

  public void setService(PupilService service) {
    this.service = service;
  }

  public FilterData getFilter() {
    return filter;
  }

  public void setFilter(FilterData filter) {
    this.filter = filter;
  }

  public List<FilterData> getListFilter() {
    return listFilter;
  }

  public void setListFilter(List<FilterData> listFilter) {
    this.listFilter = listFilter;
  }

  public List<ClassInstance> getPupilClassInstances() {
    return pupilClassInstances;
  }

  public void setPupilClassInstances(List<ClassInstance> pupilClassInstances) {
    this.pupilClassInstances = pupilClassInstances;
  }

  public ClassInstance getSelectedClassInstance() {
    return selectedClassInstance;
  }

  public void setSelectedClassInstance(ClassInstance selectedClassInstance) {
    this.selectedClassInstance = selectedClassInstance;
  }
}
