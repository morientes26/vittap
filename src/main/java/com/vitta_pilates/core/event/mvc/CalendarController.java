package com.vitta_pilates.core.event.mvc;


import com.vitta_pilates.core.event.component.*;
import com.vitta_pilates.core.event.service.AttendenceService;
import com.vitta_pilates.core.event.service.EventService;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.Event;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.repository.ClassTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event-calendar")
public class CalendarController {

  @Autowired
  EventService service;

  @Autowired
  AttendenceService attendenceService;

  @Autowired
  ClassTemplateRepository classTemplateRepository;

  @RequestMapping({"","/"})
  public String calendarFull( Model model) {
    model.addAttribute("filter", new Filter(
            "Class Type",
            "",
            "All")
    );
    model.addAttribute("event", new EventForm());
    model.addAttribute("templates", classTemplateRepository.findAll());

    return "calendar";
  }

  /**
   * Default calendar data
   * @return json list events
   */
  @RequestMapping("/data")
  public @ResponseBody
  List<Event> calendarData() {
    return service.getDefaultData();
  }

  @RequestMapping(value = "/data", method = RequestMethod.POST)
  public @ResponseBody
  List<Event> calendarFiltredData(Filter filter) {
    return service.getFiltred(filter);
  }


  /**
   * Add new event to calendar and refreshing data
   * @param eventForm
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody
  List<Event> add(EventForm eventForm) {
    service.save(eventForm);
    //todo: filter from session
    return service.getFiltred(null);
  }

  /**
   * Delete selected event and refreshing data
   * @param id
   * @return
   */
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public @ResponseBody
  List<Event> delete(@PathVariable("id") String id) {
    service.delete(id);
    //todo: filter from session
    return service.getFiltred(null);
  }

  /**
   * Load one event
   * @param id
   * @return
   */
  @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
  public @ResponseBody
  Event getEvent(@PathVariable("id") String id) {
    return service.get(id);
  }


  /**
   * Load all teacher to select2 box
   * @return
   */
  @RequestMapping(value = "/selectData", method = RequestMethod.GET)
  public @ResponseBody
  SelectPersonResult selectData(
          @RequestParam(value = "id", defaultValue = "teacher") String id,
          @RequestParam(value = "term", required=false) String term,
          @RequestParam(value = "_type", required=false) String type) {
    return (id.equals("teacher")) ?
            service.getPerson(term, true) :
            service.getPerson(term, false);
  }

  /**
   * Load Attendance to modal
   * @return
   */
  @RequestMapping(value = "/registration/{id}", method = RequestMethod.GET)
  public
  String getRegistration(
          @PathVariable("id") String id, Model model) {
    model.addAttribute("event", prepareRegistration(id));
    return "items/class-event :: attendance-table";
  }

  @RequestMapping(value = "/attendance/{id}", method = RequestMethod.GET)
  public
  String getAttendance(
          @PathVariable("id") String id, Model model) {
    model.addAttribute("event", prepareAttendanceForm(id));
    return "items/class-event :: attendance-table";
  }

  @RequestMapping(value = "/action", method = RequestMethod.POST)
  public @ResponseBody
  long action(ActionForm form, Model model) {
    ClassInstance classInstance = attendenceService.action(form);
    model.addAttribute("event", prepareAttendanceForm(form.getAttendanceId()));
    return classInstance.getId();
  }

  @RequestMapping(value = "/attend", method = RequestMethod.POST)
  public @ResponseBody
  long attend(AttendForm form, Model model) {
    ClassInstance classInstance = attendenceService.attend(form);
    model.addAttribute("event", prepareAttendanceForm(form.getAttendanceId()));
    return classInstance.getId();
  }

  private EventForm prepareRegistration(String id){
    List<AttendanceForm> list =  attendenceService.getRegistration(id);
    return prepareEventForm(list);
  }

  private EventForm prepareAttendanceForm(String attId){
    List<AttendanceForm> list =  attendenceService.getAttendance(attId);
    return prepareEventForm(list);
  }

  private EventForm prepareEventForm(List<AttendanceForm> list ){
    EventForm eventForm = new EventForm();
    if (!list.isEmpty())
      eventForm.setAttendanceTeacherForm(list.get(0));
    if (list.size()>1)
      eventForm.setAttendanceForm(list.subList(1, list.size()));
    return eventForm;
  }
}
