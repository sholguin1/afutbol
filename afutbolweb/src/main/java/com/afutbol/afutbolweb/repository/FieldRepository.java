package com.afutbol.afutbolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afutbol.afutbolweb.model.Field;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}