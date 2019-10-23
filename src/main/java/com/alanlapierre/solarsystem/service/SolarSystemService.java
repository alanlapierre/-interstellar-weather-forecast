package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.SolarSystem;
import com.alanlapierre.solarsystem.vo.PeriodWeatherConditionVO;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;

public interface SolarSystemService {
	
	WeatherConditionVO determineWeatherConditionBySolarSystemIdAndDay(Long solarSystemId, Integer day) throws IllegalArgumentException, BusinessException;
	PeriodWeatherConditionVO determineWeatherConditionsBySolarSystemIdAndYears(Long solarSystemId, Integer years) throws IllegalArgumentException, BusinessException;
	SolarSystem getSolarSystemById(Long solarSystemId);

}
