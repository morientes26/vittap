package com.vitta_pilates.core.event.mvvm;


import com.vitta_pilates.core.shared.component.MessageBox;
import com.vitta_pilates.model.dao.Calendar;
import com.vitta_pilates.model.dao.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@VariableResolver(DelegatingVariableResolver.class)
public class CalendarViewModel {

  private final Logger log = LoggerFactory.getLogger(CalendarViewModel.class);

  private Calendar calendar;
  private Event selected;

  private List<String> listFilterType = Arrays.asList("type 1", "type 2", "type 3");
  private String filterType;
  private String filterValue;

  @Command(value = "select")
  public void select(@BindingParam("id") String id){
    checkNotNull(id);

    selected = findEventById(id);
    if (selected.active) {
      log.info("select event : {}", findEventById(id).getName());
      new MessageBox.MessageBoxBuilder("Info",
              "You have clicked on the event '"+selected.getName()+" with ID: "+selected.getId())
              .createMessageBox();
    } else {
      log.info(findEventById(id).getId());
      new MessageBox.MessageBoxBuilder("Info",
              "You have clicked here, but there is no event yet. ID of box is: "+selected.getId())
              .createMessageBox();
    }
  }

  @Init
  public void init(){

    this.calendar = new Calendar(7, 24);
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public List<String> getListFilterType() {
    return listFilterType;
  }

  public void setListFilter(List<String> listFilterType) {
    this.listFilterType = listFilterType;
  }

  public void setListFilterType(List<String> listFilterType) {
    this.listFilterType = listFilterType;
  }

  public String getFilterType() {
    return filterType;
  }

  public void setFilterType(String filterType) {
    this.filterType = filterType;
  }

  public String getFilterValue() {
    return filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public Event getSelected() {
    return selected;
  }

  public void setSelected(Event selected) {
    this.selected = selected;
  }

  //FIXME: refactor this nonseance
  private Event findEventById(String id){
    String[] ident = id.split("_");
    return calendar.getHours()[Integer.valueOf(ident[0])][Integer.valueOf(ident[1])];
  }
}
