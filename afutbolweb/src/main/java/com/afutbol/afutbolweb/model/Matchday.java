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
@Table(name = "matchday")
public class Matchday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchday_id")
    private Integer matchdayId;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    private Integer number;

    public Matchday() {
    }

    public Matchday(Tournament tournament, Integer number) {
        this.tournament = tournament;
        this.number = number;
    }

    //Getters y Setters

    public Integer getMatchdayId() {
        return matchdayId;
    }

    public void setMatchdayId(Integer matchdayId) {
        this.matchdayId = matchdayId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}