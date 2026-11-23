package com.afutbol.afutbolweb.ctrl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afutbol.afutbolweb.model.Game;
import com.afutbol.afutbolweb.model.Team;
import com.afutbol.afutbolweb.repository.GameRepository;
import com.afutbol.afutbolweb.repository.TeamRepository;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameController(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameRepository.findAllByOrderByGameDateAscGameTimeAsc();
    }

    @PostMapping
    public ResponseEntity<String> saveGame(@RequestBody Game game) {
        Team homeTeam = teamRepository.findById(game.getHomeTeam().getTeamId()).get();
        Team awayTeam = teamRepository.findById(game.getAwayTeam().getTeamId()).get();
        Integer fieldId = game.getField().getFieldId();

        // Regla 1: el local y el visitante deben ser diferentes
        if (homeTeam.getTeamId().equals(awayTeam.getTeamId())) {
            return ResponseEntity.badRequest().body("El equipo local y el visitante deben ser diferentes.");
        }

        // Regla 2: la cancha debe ser la local o la alterna del equipo local
        boolean validField = false;

        if (homeTeam.getHomeField().getFieldId().equals(fieldId)) {
            validField = true;
        }

        if (homeTeam.getAlternateField() != null) {
            if (homeTeam.getAlternateField().getFieldId().equals(fieldId)) {
                validField = true;
            }
        }

        if (validField == false) {
            return ResponseEntity.badRequest().body("La cancha debe ser la local o la alterna del equipo local.");
        }

        // Regla 3: un equipo solo puede jugar una vez en cada fecha
        List<Game> sameMatchdayGames = gameRepository.findByMatchdayMatchdayId(game.getMatchday().getMatchdayId());

        for (Game other : sameMatchdayGames) {
            if (playsInGame(homeTeam, other)) {
                return ResponseEntity.badRequest().body("El equipo local ya tiene un partido en esta fecha.");
            }

            if (playsInGame(awayTeam, other)) {
                return ResponseEntity.badRequest().body("El equipo visitante ya tiene un partido en esta fecha.");
            }
        }

        // Regla 4: la cancha y los equipos deben estar libres ese día a esa hora
        List<Game> sameTimeGames = gameRepository.findByGameDateAndGameTime(game.getGameDate(), game.getGameTime());

        for (Game other : sameTimeGames) {
            if (other.getField().getFieldId().equals(fieldId)) {
                return ResponseEntity.badRequest().body("La cancha ya tiene un partido ese día a esa hora.");
            }

            if (playsInGame(homeTeam, other)) {
                return ResponseEntity.badRequest().body("El equipo local ya tiene un partido ese día a esa hora.");
            }

            if (playsInGame(awayTeam, other)) {
                return ResponseEntity.badRequest().body("El equipo visitante ya tiene un partido ese día a esa hora.");
            }
        }

        gameRepository.save(game);
        return ResponseEntity.ok("Partido programado correctamente.");
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Integer id) {
        gameRepository.deleteById(id);
    }

    private boolean playsInGame(Team team, Game game) {
        if (game.getHomeTeam().getTeamId().equals(team.getTeamId())) {
            return true;
        }

        if (game.getAwayTeam().getTeamId().equals(team.getTeamId())) {
            return true;
        }

        return false;
    }
}