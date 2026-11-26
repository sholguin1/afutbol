package com.afutbol.afutbolweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;

    private String name;
    private String city;

    @ManyToOne
    @JoinColumn(name = "home_field_id", nullable = false)
    private Field homeField;

    @ManyToOne
    @JoinColumn(name = "alternate_field_id")
    // Cancha alterna: es opcional, puede quedar en null
    private Field alternateField;
    // Constructor vacío
    public Team() {
    }

    public Team(String name, String city, Field homeField, Field alternateField) {
        this.name = name;
        this.city = city;
        this.homeField = homeField;
        this.alternateField = alternateField;
    }

    //Getters y Setters

   public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Field getHomeField() {
        return homeField;
    }

    public void setHomeField(Field homeField) {
        this.homeField = homeField;
    }

    public Field getAlternateField() {
        return alternateField;
    }

    public void setAlternateField(Field alternateField) {
        this.alternateField = alternateField;
    }
}