package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.solarsystem.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

}
