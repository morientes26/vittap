package com.vitta_pilates.core.event.mvc;


import com.vitta_pilates.core.event.component.EventForm;
import com.vitta_pilates.core.event.component.Filter;
import com.vitta_pilates.core.event.service.EventService;
import com.vitta_pilates.model.dao.Event;
import com.vitta_pilates.model.repository.ClassTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/event-calendar")
public class CalendarController {

  @Autowired
  EventService service;

  @Autowired
  ClassTemplateRepository classTemplateRepository;

  @RequestMapping({"","/"})
  public String calendarFull( Model model) {
    model.addAttribute("filter", new Filter(
            "Class Type",
            "",
            "Class Type")
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

  @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
  public @ResponseBody
  Event loadData(@PathVariable("id") String id) {
    return service.get(id);
  }


}
