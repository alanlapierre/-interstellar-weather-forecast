package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.solarsystem.model.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

}
