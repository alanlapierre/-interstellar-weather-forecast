package com.alanlapierre.solarsystem.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.service.SolarSystemService;

@Component
public class ScheduledTasks {
	
	Logger logger = LogManager.getLogger(ScheduledTasks.class);
	
	private Boolean generateWeatherConditionJOBWasExecuted  = false;
	
	@Autowired
	private SolarSystemService solarSystemService;
	
	
	@Scheduled(fixedRate = 2000)
	public void generateWeatherConditionsJOB() throws IllegalArgumentException, BusinessException {
		
		if(!generateWeatherConditionJOBWasExecuted) {
			solarSystemService.determineWeatherConditionsBySolarSystemIdAndYears(1L, 10);
			generateWeatherConditionJOBWasExecuted = true;
			logger.info("Ejecutando generateWeatherConditionsJOB");
		} 
		
		try {
			logger.info("Durmiendo thread de job generateWeatherConditionsJOB");
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
