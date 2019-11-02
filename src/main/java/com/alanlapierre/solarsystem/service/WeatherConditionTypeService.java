package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public interface WeatherConditionTypeService {
	
	WeatherConditionType getWeatherConditionTypeByName(WeatherConditionPrediction name) throws IllegalArgumentException;

}
