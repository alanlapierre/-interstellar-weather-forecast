package com.alanlapierre.solarsystem.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.repository.WeatherConditionRepository;
import com.alanlapierre.solarsystem.util.ParamValidator;

@Service("weatherConditionService")
@Transactional(readOnly = true)
public class WeatherConditionServiceImpl implements WeatherConditionService {
	
	Logger logger = LogManager.getLogger(WeatherConditionServiceImpl.class);

	private final WeatherConditionRepository weatherConditionRepository;


	public WeatherConditionServiceImpl(WeatherConditionRepository weatherConditionRepository) {
		this.weatherConditionRepository = weatherConditionRepository;
	}
	
	
	public WeatherCondition getWeatherConditionBySolarSystemIdAndDay(Long solarSystemId, Integer day) throws IllegalArgumentException {
		
		ParamValidator.test(day, (i)-> i == null || i <= 0);
		ParamValidator.test(solarSystemId, (i)-> i == null || i <= 0);
		
		return weatherConditionRepository.findBySolarSystemIdAndDay(solarSystemId, day);
		
	}


	public WeatherCondition create(WeatherCondition weatherCondition) throws BusinessException{
		
		
		WeatherCondition result = null;

		try {
			result = this.weatherConditionRepository.save(weatherCondition);
		} catch (Exception e) {
			throw new BusinessException("There was an error creating WeatherCondition");
		}

		return result;
		
	}


}
