package com.alanlapierre.solarsystem.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.repository.WeatherConditionTypeRepository;
import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

@Service("weatherConditionTypeService")
@Transactional(readOnly = true)
public class WeatherConditionTypeServiceImpl implements WeatherConditionTypeService{
	
	Logger logger = LogManager.getLogger(WeatherConditionTypeServiceImpl.class);

	private final WeatherConditionTypeRepository weatherConditionTypeRepository;


	public WeatherConditionTypeServiceImpl(WeatherConditionTypeRepository weatherConditionTypeRepository) {
		this.weatherConditionTypeRepository = weatherConditionTypeRepository;
	}
	
	
	public WeatherConditionType getWeatherConditionTypeByName(WeatherConditionTypeName name) throws IllegalArgumentException {
		
		if(name == null ){
			throw new IllegalArgumentException("Argument not valid");
		}
		
		return weatherConditionTypeRepository.findByName(name);
		
	}


}
