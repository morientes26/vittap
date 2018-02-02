package com.vitta_pilates.core.event;


import com.vitta_pilates.core.event.component.Filter;
import com.vitta_pilates.core.event.service.EventService;
import com.vitta_pilates.model.dao.Event;
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

  @RequestMapping({"","/"})
  public String calendarFull( Model model) {
    model.addAttribute("filter", new Filter(
            "Class Type",
            "",
            "Class Type")
    );
    return "calendarFull";
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

  @RequestMapping(value = "/data", method = RequestMethod.POST)//,
                  //headers="Accept=application/json",
                  //produces = "application/json") @RequestBody
  public @ResponseBody
  List<Event> calendarFiltredData(Filter filter) {
    return service.getFiltred(filter);
  }


  /**
   * Add new event to calendar and refreshing data
   * @param event
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST, headers="Accept=application/json", produces = "application/json")
  public @ResponseBody
  List<Event> add(@RequestBody Event event) {
    service.save(event);
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


}
