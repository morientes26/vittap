package com.vitta_pilates.core.event.component;


import java.util.ArrayList;
import java.util.List;

public class SelectPersonResult {

  List<Person> results = new ArrayList<>();


  public SelectPersonResult(){

  }

  public List<Person> getResults() {
    return results;
  }

  public void setResults(List<Person> results) {
    this.results = results;
  }

  public static class Person {
    private String id;
    private String text;

    public Person(){

    }

    public Person(String id, String text){
      this.id = id;
      this.text = text;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }

}
