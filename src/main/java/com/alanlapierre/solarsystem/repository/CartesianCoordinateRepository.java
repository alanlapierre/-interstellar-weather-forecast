package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.solarsystem.model.CartesianCoordinate;

public interface CartesianCoordinateRepository extends JpaRepository<CartesianCoordinate, Long> {

}
