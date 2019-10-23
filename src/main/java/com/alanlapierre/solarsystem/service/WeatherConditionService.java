package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.WeatherCondition;

public interface WeatherConditionService {
	
	WeatherCondition getWeatherConditionBySolarSystemIdAndDay(Long solarSystemId, Integer day) throws IllegalArgumentException;
	WeatherCondition create(WeatherCondition weatherCondition) throws BusinessException;


}
