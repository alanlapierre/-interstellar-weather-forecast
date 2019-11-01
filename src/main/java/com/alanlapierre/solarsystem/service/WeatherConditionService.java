package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;

public interface WeatherConditionService {
	
	WeatherCondition getWeatherConditionBySolarSystemIdAndDay(Long solarSystemId, Integer day) throws IllegalArgumentException;
	WeatherCondition create(WeatherCondition weatherCondition) throws BusinessException;
	WeatherConditionVO mapToWeatherConditionVO(WeatherCondition weatherCondition);

}
