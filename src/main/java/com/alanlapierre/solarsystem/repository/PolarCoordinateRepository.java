package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.solarsystem.model.PolarCoordinate;

public interface PolarCoordinateRepository extends JpaRepository<PolarCoordinate, Long> {

}
