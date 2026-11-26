package com.afutbol.afutbolweb.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afutbol.afutbolweb.model.Team;
import com.afutbol.afutbolweb.repository.TeamRepository;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository repository;

    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Team> getTeams() {
        return repository.findAll();
    }

    // Sin teamId crea el equipo; con teamId lo actualiza
    @PostMapping
    public Team saveTeam(@RequestBody Team team) {
        return repository.save(team);
    }
    
    // Si el equipo tiene partidos, MySQL no deja borrarlo (llave foránea)
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}