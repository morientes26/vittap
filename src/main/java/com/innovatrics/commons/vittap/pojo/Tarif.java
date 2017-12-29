package com.innovatrics.commons.vittap.pojo;

import java.util.Date;

public class Tarif {
    public Tarif(String name, String description, Double value, Date dateOfIssue) {
        this.name = name;
        this.description = description; 
        this.value = value;
        this.dateOfIssue = dateOfIssue;
    }
    public String name;
    public String description; 
    public Double value; // pesos per hour // TODO:2017-01-13:mze: make it currency independent
    public Date dateOfIssue;
}