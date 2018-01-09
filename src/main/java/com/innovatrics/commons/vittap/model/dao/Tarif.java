package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tarif {

    @Id
    @GeneratedValue
    private Long id;

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

    @Override
    public String toString() {
        return "Tarif{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", dateOfIssue=" + dateOfIssue +
                '}';
    }

    public Long getId() {
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