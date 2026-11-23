package com.afutbol.afutbolweb.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afutbol.afutbolweb.model.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findAllByOrderByGameDateAscGameTimeAsc();

    List<Game> findByGameDateAndGameTime(LocalDate gameDate, LocalTime gameTime);

    List<Game> findByMatchdayMatchdayId(Integer matchdayId);
}
