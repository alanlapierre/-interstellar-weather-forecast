package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

public interface WeatherConditionTypeRepository extends JpaRepository<WeatherConditionType, Long> {

	@Query("FROM WeatherConditionType WHERE name = ?1")
	WeatherConditionType findByName(WeatherConditionTypeName name);

}
