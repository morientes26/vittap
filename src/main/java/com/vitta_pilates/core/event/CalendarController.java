package com.vitta_pilates.core.event;


import com.vitta_pilates.model.dao.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CalendarController {

  @RequestMapping("/event-calendar")
  public String calendarFull( Model model) {
    model.addAttribute("name", "test");
    return "calendarFull";
  }

  @RequestMapping("/calendarData")
  public @ResponseBody
  List<Event> calendarData() {
    List<Event> events = new ArrayList<>();
    events.add(new Event("2", parseDate("2018-01-29 09:00"), parseDate("2018-01-29 10:00"), " ", Event.ORANGE, 0));
    events.add(new Event("1", parseDate("2018-01-29 10:30"), parseDate("2018-01-29 11:30")," ", Event.ORANGE, 0));
    events.add(new Event("3", parseDate("2018-01-29 11:00"), parseDate("2018-01-29 12:00")," ", Event.GREEN, 4));
    events.add(new Event("4", parseDate("2018-01-29 13:00"), parseDate("2018-01-29 14:00"),"Pis1", Event.BLUE, 0));
    events.add(new Event("5", parseDate("2018-01-29 14:00"), parseDate("2018-01-29 15:00"),"Ref", Event.ORANGE, 0));
    events.add(new Event("6", parseDate("2018-01-29 14:00"), parseDate("2018-01-29 15:00"),"Sus2", Event.GREEN, 0));
    events.add(new Event("7", parseDate("2018-01-30 09:00"), parseDate("2018-01-30 10:00")," ", Event.BLUE, 0));
    events.add(new Event("8", parseDate("2018-01-30 10:00"), parseDate("2018-01-30 11:00")," ", Event.GREEN, 0));
    events.add(new Event("9", parseDate("2018-01-30 11:00"), parseDate("2018-01-30 12:00")," ", Event.BLUE, 0));
    events.add(new Event("10", parseDate("2018-01-30 11:00"), parseDate("2018-01-30 12:00")," ", Event.GREEN, 2));
    events.add(new Event("11", parseDate("2018-01-30 11:00"), parseDate("2018-01-30 12:00")," ", Event.ORANGE, 0));
    events.add(new Event("12", parseDate("2018-01-30 13:00"), parseDate("2018-01-30 14:00"),"Con", Event.GRAY, 0));
    events.add(new Event("13", parseDate("2018-01-30 13:30"), parseDate("2018-01-30 14:30"),"Sus1", Event.GREEN, 0));
    events.add(new Event("14", parseDate("2018-01-30 13:30"), parseDate("2018-01-30 14:30"),"Ref", Event.ORANGE, 0));
    events.add(new Event("15", parseDate("2018-01-31 09:00"), parseDate("2018-01-31 10:00")," ", Event.BLUE, 2));
    events.add(new Event("16", parseDate("2018-01-31 10:00"), parseDate("2018-01-31 11:00")," ", Event.GREEN, 0));
    events.add(new Event("17", parseDate("2018-01-31 13:00"), parseDate("2018-01-31 14:00"),"Pis2", Event.BLUE, 0));
    return events;
  }

  private static Date parseDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
    } catch (ParseException e) {
      return null;
    }
  }
}
