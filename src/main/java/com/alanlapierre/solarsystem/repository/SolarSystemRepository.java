package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.solarsystem.model.SolarSystem;

public interface SolarSystemRepository extends JpaRepository<SolarSystem, Long> {

}
