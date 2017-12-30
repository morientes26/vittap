package com.innovatrics.commons.vittap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tarif {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private Double value; // pesos per hour // TODO:2017-01-13:mze: make it currency independent
    private Date dateOfIssue;

    public Tarif(){}

    public Tarif(String name, String description, Double value, Date dateOfIssue) {
        this.name = name;
        this.description = description; 
        this.value = value;
        this.dateOfIssue = dateOfIssue;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}