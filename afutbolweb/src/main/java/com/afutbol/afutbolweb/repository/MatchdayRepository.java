package com.afutbol.afutbolweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afutbol.afutbolweb.model.Matchday;

public interface MatchdayRepository extends JpaRepository<Matchday, Integer> {

    List<Matchday> findAllByOrderByNumberAsc();
}