package com.vitta_pilates.core.people.mvvm;

import com.vitta_pilates.core.people.service.PupilService;
import com.vitta_pilates.model.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabs;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

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

  private int month = 0;

  private boolean showOthertabs = false;
  private List<ClassInstance> pupilClassInstances;
  private ClassInstance selectedClassInstance;

  private List<ProgramInstance> pupilProgramInstances;
  private ProgramInstance selectedProgramInstance;


  @WireVariable("pupilService")
  private PupilService service;

  @Command
  @NotifyChange("attendentList")
  public void search(){
    log.debug("Search attendents by {} {}", keyword, filter);
    attendentList = service.findByFilterAndKeywords(filter, keyword);
  }

  @Command
  @NotifyChange({"pupilClassInstances", "selectedClassInstance", "month","showOthertabs"})
  public void select(){
    showOthertabs = true;
    clearTabs();
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
  @NotifyChange({"selected"})
  public void changeStatus(){
    service.changeStatus(selected);
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
  @NotifyChange({"pupilClassInstances", "selectedClassInstance", "month"})
  public void calendarControlClassInstance(@BindingParam( "direction") String direction){
    if (direction.equals("left")){
      month-=1;
      pupilClassInstances = service.findClassInstanceByPupilAndPeriod(selected, month);
      selectedClassInstance = null;
    } else {
      month+=1;
      pupilClassInstances = service.findClassInstanceByPupilAndPeriod(selected, month);
      selectedClassInstance = null;
    }
  }

  @Command
  @NotifyChange({"pupilProgramInstances", "selectedProgramInstance", "month"})
  public void calendarControlProgramInstance(@BindingParam( "direction") String direction){
    if (direction.equals("left")){
      month-=1;
      pupilProgramInstances = service.findProgramInstanceByPupilAndPeriod(selected, month);
      selectedProgramInstance = null;
    } else {
      month+=1;
      pupilProgramInstances = service.findProgramInstanceByPupilAndPeriod(selected, month);
      selectedProgramInstance = null;
    }
  }

  @Command
  @NotifyChange({"pupilClassInstances", "selectedClassInstance",
          "pupilProgramInstances", "selectedProgramInstance", "month"})
  public void updateTabsData () {
    month = 0;
    pupilClassInstances = service.findClassInstanceByPupilAndPeriod(
            selected, month);
    pupilProgramInstances = service.findProgramInstanceByPupilAndPeriod(
            selected, month);
    selectedClassInstance = new ClassInstance();
    selectedProgramInstance = new ProgramInstance();
  }

  @Command
  @NotifyChange(".")
  public void clearForm () {
    clear();
  }


  @Init
  public void init(){
    clear();
  }

  private void clear(){
    keyword = "";
    attendentList = service.getAll();
    selected = new Attendant(new PersonalData());
    pupilClassInstances = null;
    pupilProgramInstances = null;
    selectedClassInstance = null;
    selectedProgramInstance = null;
    month = 0;
    showOthertabs = false;
  }

  private void clearTabs(){
    month = 0;
    pupilClassInstances = null;
    selectedClassInstance = null;
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

  public String getMonth() {

    LocalDate fromDate = LocalDate.now().plusMonths( month ).with(firstDayOfMonth());
    return Month.getName(fromDate.getMonth().getValue());
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public boolean isShowOthertabs() {
    return showOthertabs;
  }

  public void setShowOthertabs(boolean showOthertabs) {
    this.showOthertabs = showOthertabs;
  }

  public List<ProgramInstance> getPupilProgramInstances() {
    return pupilProgramInstances;
  }

  public void setPupilProgramInstances(List<ProgramInstance> pupilProgramInstances) {
    this.pupilProgramInstances = pupilProgramInstances;
  }

  public ProgramInstance getSelectedProgramInstance() {
    return selectedProgramInstance;
  }

  public void setSelectedProgramInstance(ProgramInstance selectedProgramInstance) {
    this.selectedProgramInstance = selectedProgramInstance;
  }
}
