package com.afutbol.afutbolweb.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afutbol.afutbolweb.model.Matchday;
import com.afutbol.afutbolweb.repository.MatchdayRepository;

@RestController
@RequestMapping("/api/matchdays")
public class MatchdayController {

    private final MatchdayRepository repository;

    public MatchdayController(MatchdayRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Matchday> getMatchdays() {
        return repository.findAllByOrderByNumberAsc();
    }

    @PostMapping
    public Matchday saveMatchday(@RequestBody Matchday matchday) {
        return repository.save(matchday);
    }
}