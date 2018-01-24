package com.vitta_pilates.model.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Calendar {

  private int colums;
  private int rows;
  private String[] header = {"time","Mo","Tu","We","Th","Fr","Sa","So"};
  private Event[][] hours;
  private List<Integer> time = new ArrayList<>();


  public Calendar(int columns, int rows){
    this.colums = columns;
    this.rows = rows;
    this.hours = new Event[colums][rows];

    initTestData();
  }

  private void initTestData(){

    IntStream.range(0, colums).forEachOrdered(j -> {
      IntStream.range(0, rows).forEachOrdered(i -> {
        hours[j][i] = new Event(j+"_"+i);
      });
    });
    IntStream.range(0, rows).forEachOrdered(j -> {
      time.add(new Integer(j));
    });
    hours[0][10] = new Event("0_10","B3(2)", "#D6EAF8");
    hours[0][11] = new Event("0_11","", "#D6EAF8");
    hours[1][20] = new Event("1_20","B0(4)", "#D6EAF8");
    hours[1][11] = new Event("1_11","B1(1)", "#FADBD8");
    hours[2][9] = new Event("2_9","B3(1)", "#FAE5D3");
    hours[3][16] = new Event("3_16","I1(3)", "#FADBD8");
    hours[4][8] = new Event("4_8","B3(1)", "#FAE5D3");
    hours[5][12] = new Event("5_12","B3(1)", "#D6EAF8");
    hours[6][14] = new Event("6_14","B3(1)", "#FADBD8");
    hours[6][9] = new Event("6_9","I2(4)", "#FADBD8");
    hours[6][13] = new Event("6_13","B3(2)", "#FADBD8");
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

  public Event[][] getHours() {
    return hours;
  }

  public void setHours(Event[][] hours) {
    this.hours = hours;
  }

  public List<Integer> getTime() {
    return time;
  }

}
