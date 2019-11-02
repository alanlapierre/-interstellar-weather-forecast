package com.alanlapierre.solarsystem.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.repository.WeatherConditionTypeRepository;
import com.alanlapierre.solarsystem.validator.ParamValidator;

@Service("weatherConditionTypeService")
@Transactional(readOnly = true)
public class WeatherConditionTypeServiceImpl implements WeatherConditionTypeService{
	
	Logger logger = LogManager.getLogger(WeatherConditionTypeServiceImpl.class);

	private final WeatherConditionTypeRepository weatherConditionTypeRepository;


	public WeatherConditionTypeServiceImpl(WeatherConditionTypeRepository weatherConditionTypeRepository) {
		this.weatherConditionTypeRepository = weatherConditionTypeRepository;
	}
	
	
	public WeatherConditionType getWeatherConditionTypeByPrediction(WeatherConditionPrediction prediction) throws IllegalArgumentException {
		
		ParamValidator.test(prediction, (i)-> i == null);
		
		return weatherConditionTypeRepository.findByPrediction(prediction);
		
	}


}
