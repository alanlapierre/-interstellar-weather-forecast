package com.alanlapierre.solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public interface WeatherConditionTypeRepository extends JpaRepository<WeatherConditionType, Long> {

	@Query("FROM WeatherConditionType WHERE prediction = ?1")
	WeatherConditionType findByPrediction(WeatherConditionPrediction prediction);

}
