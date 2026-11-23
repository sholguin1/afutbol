package com.afutbol.afutbolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afutbol.afutbolweb.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}