package com.vitta_pilates.model.dao;

import com.google.common.primitives.Ints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

@Deprecated
public class Calendar {

  private int colums;
  private int rows;
  private String[] header = {"time","Mo","Tu","We","Th","Fr","Sa","So"};
  private String[] time = {"9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30",
          "13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30",
          "18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30"};
  private Box[][] hours;
  private int[] hours_idx;


  public Calendar(int columns, int rows){
    this.colums = columns;
    this.rows = rows;
    this.hours = new Box[colums][rows];

    initTestData();
  }

  private void initTestData(){

    IntStream.range(0, colums).forEachOrdered(j -> {
      IntStream.range(0, rows).forEachOrdered(i -> {
        hours[j][i] = new Box(j+"_"+i+"0");
      });
    });

    hours_idx = new int[]{25, 26, 27};

//    setEvent(parseDate("2018-01-25 10:00"), new Box(Arrays.asList(new Event("", "B3(2)", "#D6EAF8", 2, "/img/some.png"),new Event("","A1(2)", "#D6EAF8", 2, "/img/some.png"))));
//    setEvent(parseDate("2018-01-25 13:30"), new Box(Arrays.asList(new Event("","B3(2)", "#D6EAF8", 0, "/img/one.png"))));
//    setEvent(parseDate("2018-01-25 15:00"), new Box(Arrays.asList(new Event("","B0(2)", "#FADBD8", 3, "/img/some.png"))));
//    setEvent(parseDate("2018-01-25 18:30"), new Box(Arrays.asList(new Event("","B3(1)", "#FAE5D3", 10, "/img/not-one.png"))));
//    setEvent(parseDate("2018-01-26 12:00"), new Box(Arrays.asList(new Event("","A1(1)", "#FADBD8", 2, "/img/some.png"),new Event("","A2(3)", "#D6EAF8", 4, "/img/some.png"))));
//    setEvent(parseDate("2018-01-26 09:00"), new Box(Arrays.asList(new Event("","D1", "#FADBD8", 4, "/img/some.png"))));
//    setEvent(parseDate("2018-01-27 17:00"), new Box(Arrays.asList(new Event("","C1(1)", "#FADBD8", 10, "/img/not-one.png"))));
//    setEvent(parseDate("2018-01-27 20:30"), new Box(Arrays.asList(new Event("","C1(1)", "#FADBD8", 0, "/img/one.png"),new Event("","A5(3)", "#D6EAF8", 2, "/img/some.png"),new Event("","A5(3)", "#D6EAF8", 4, "/img/some.png"))));
  }

  private void setEvent(Date date, Box box){
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = formatter.format(date);
    int day = Integer.valueOf(formattedDate.substring(8,10));
    int hour = Integer.valueOf(formattedDate.substring(11,13));
    int minutes = Integer.valueOf(formattedDate.substring(14,16));
    minutes = (minutes>=30) ? 1 : 0;
    int column = Ints.indexOf(hours_idx, day);
    int idx = (hour - 9) * 2 + minutes;
    int i = 0;
//    for (Event e  : box.getEvents())
//      e.setId(column+"_" + idx + (i++));
    hours[column][idx] = box;
  }

  private static Date parseDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
    } catch (ParseException e) {
      return null;
    }
  }

  public int getColums() {
    return colums;
  }

  public int getRows() {
    return rows;
  }

  public String[] getHeader() {
    return header;
  }

  public Box[][] getHours() {
    return hours;
  }

  public void setHours(Box[][] hours) {
    this.hours = hours;
  }

  public String[] getTime() {
    return time;
  }

}
