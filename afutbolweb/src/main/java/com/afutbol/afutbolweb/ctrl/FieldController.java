package com.afutbol.afutbolweb.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afutbol.afutbolweb.model.Field;
import com.afutbol.afutbolweb.repository.FieldRepository;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldRepository repository;

    public FieldController(FieldRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Field> getFields() {
        return repository.findAll();
    }
}