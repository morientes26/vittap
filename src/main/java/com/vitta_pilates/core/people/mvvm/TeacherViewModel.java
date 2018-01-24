package com.vitta_pilates.core.people.mvvm;

import com.vitta_pilates.core.people.service.TeacherService;
import com.vitta_pilates.core.shared.mvvm.BaseViewModel;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.enumeration.FilterTeacherData;
import com.vitta_pilates.model.enumeration.Month;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

@VariableResolver(DelegatingVariableResolver.class)
public class TeacherViewModel {

  private final Logger log = LoggerFactory.getLogger(TeacherViewModel.class);

  private FilterTeacherData filter = FilterTeacherData.ALL;
  private List<FilterTeacherData> listFilter = Arrays.asList(
          FilterTeacherData.ALL,
          FilterTeacherData.PENDING_SALARIES
  );
  private String keyword = "";
  private List<Attendant> attendentList;
  private Attendant selected;

  private int month = 0;

  private boolean showOthertabs = false;
  private List<ClassInstance> teacherClassInstances;
  private ClassInstance selectedClassInstance;

  private List<ProgramInstance> teacherProgramInstances;
  private ProgramInstance selectedProgramInstance;


  @WireVariable("teacherService")
  private TeacherService service;

  @Command
  @NotifyChange("attendentList")
  public void search() {
    log.debug("Search attendents by {} {}", keyword, filter);
    attendentList = service.findByFilterAndKeywords(filter, keyword);
  }

  @Command
  @NotifyChange({"teacherClassInstances", "selectedClassInstance", "month", "showOthertabs"})
  public void select() {
    showOthertabs = true;
    clearTabs();
  }

  @Command
  @NotifyChange({"selected", "attendentList"})
  public void submit() {
    if (selected.getId()==null){
      service.register(selected);
      //FIXME: component has to refresh by its self without redirect
      Executions.sendRedirect(null);
    } else
      new BaseViewModel<Attendant>(service, selected).submit();
  }

  @Command
  @NotifyChange(".")
  public void delete() {
    new BaseViewModel<Attendant>(service, selected).delete();
  }

  @Command
  @NotifyChange({"selected"})
  public void changeStatus() {
    service.changeStatus(selected);
    log.info("Change status teacher {}", selected);
  }

  @Command
  @NotifyChange({"teacherClassInstances", "selectedClassInstance", "month"})
  public void calendarControlClassInstance(@BindingParam("direction") String direction) {
    if (direction.equals("left")) {
      month -= 1;
      teacherClassInstances = service.findClassInstanceByPeriod(selected, month);
      selectedClassInstance = null;
    } else {
      month += 1;
      teacherClassInstances = service.findClassInstanceByPeriod(selected, month);
      selectedClassInstance = null;
    }
  }

  @Command
  @NotifyChange({"teacherProgramInstances", "selectedProgramInstance", "month"})
  public void calendarControlProgramInstance(@BindingParam("direction") String direction) {
    if (direction.equals("left")) {
      month -= 1;
      teacherProgramInstances = service.findProgramInstanceByPeriod(selected, month);
      selectedProgramInstance = null;
    } else {
      month += 1;
      teacherProgramInstances = service.findProgramInstanceByPeriod(selected, month);
      selectedProgramInstance = null;
    }
  }

  @Command
  @NotifyChange({"teacherClassInstances", "selectedClassInstance",
          "teacherProgramInstances", "selectedProgramInstance", "month"})
  public void updateTabsData() {
    month = 0;
    teacherClassInstances = service.findClassInstanceByPeriod(
            selected, month);
    teacherProgramInstances = service.findProgramInstanceByPeriod(
            selected, month);
    selectedClassInstance = new ClassInstance();
    selectedProgramInstance = new ProgramInstance();
  }

  @Command
  @NotifyChange(".")
  public void clearForm() {
    clear();
  }


  @Init
  public void init() {
    clear();
  }

  private void clear() {
    keyword = "";
    attendentList = service.findByFilterAndKeywords(FilterTeacherData.ALL,"");
    selected = new Attendant(new PersonalData());
    teacherClassInstances = null;
    teacherProgramInstances = null;
    selectedClassInstance = null;
    selectedProgramInstance = null;
    month = 0;
    showOthertabs = false;
  }

  private void clearTabs() {
    month = 0;
    teacherClassInstances = null;
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

  public TeacherService getService() {
    return service;
  }

  public void setService(TeacherService service) {
    this.service = service;
  }

  public FilterTeacherData getFilter() {
    return filter;
  }

  public void setFilter(FilterTeacherData filter) {
    this.filter = filter;
  }

  public List<FilterTeacherData> getListFilter() {
    return listFilter;
  }

  public void setListFilter(List<FilterTeacherData> listFilter) {
    this.listFilter = listFilter;
  }

  public List<ClassInstance> getPupilClassInstances() {
    return teacherClassInstances;
  }

  public void setTeacherClassInstances(List<ClassInstance> teacherClassInstances) {
    this.teacherClassInstances = teacherClassInstances;
  }

  public ClassInstance getSelectedClassInstance() {
    return selectedClassInstance;
  }

  public void setSelectedClassInstance(ClassInstance selectedClassInstance) {
    this.selectedClassInstance = selectedClassInstance;
  }

  public String getMonth() {

    LocalDate fromDate = LocalDate.now().plusMonths(month).with(firstDayOfMonth());
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

  public List<ProgramInstance> getTeacherProgramInstances() {
    return teacherProgramInstances;
  }

  public void setTeacherProgramInstances(List<ProgramInstance> teacherProgramInstances) {
    this.teacherProgramInstances = teacherProgramInstances;
  }

  public ProgramInstance getSelectedProgramInstance() {
    return selectedProgramInstance;
  }

  public void setSelectedProgramInstance(ProgramInstance selectedProgramInstance) {
    this.selectedProgramInstance = selectedProgramInstance;
  }

  public List<ClassInstance> getTeacherClassInstances() {
    return teacherClassInstances;
  }
}
