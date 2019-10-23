package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

public interface WeatherConditionTypeService {
	
	WeatherConditionType getWeatherConditionTypeByName(WeatherConditionTypeName name) throws IllegalArgumentException;

}
