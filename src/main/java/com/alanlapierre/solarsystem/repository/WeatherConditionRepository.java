package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alanlapierre.solarsystem.model.WeatherCondition;

public interface WeatherConditionRepository extends JpaRepository<WeatherCondition, Long> {

	@Query("FROM WeatherCondition WHERE solarSystem.id = ?1 AND day = ?2")
	 WeatherCondition findBySolarSystemIdAndDay(Long solarSystemId, Integer day);
}
