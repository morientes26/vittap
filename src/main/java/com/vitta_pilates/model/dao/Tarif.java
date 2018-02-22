package com.vitta_pilates.model.dao;

import org.hibernate.validator.constraints.NotEmpty;
import org.zkoss.bind.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Tarif {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name can not be null")
    private String name;
    private String description;
    private BigDecimal value; // pesos per hour // TODO:2017-01-13:mze: make it currency independent
    private Date dateOfIssue;

    public Tarif(){}

    public Tarif(String name, String description, BigDecimal value, Date dateOfIssue) {
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    //note: For ZK8 is neccessery to add immutable annotation for Date type
    @Immutable
    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}