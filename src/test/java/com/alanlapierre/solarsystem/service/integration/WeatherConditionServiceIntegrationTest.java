package com.alanlapierre.solarsystem.service.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.SolarSystem;
import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.service.SolarSystemService;
import com.alanlapierre.solarsystem.service.WeatherConditionService;
import com.alanlapierre.solarsystem.service.WeatherConditionTypeService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class WeatherConditionServiceIntegrationTest {
	
	@Autowired
	WeatherConditionService weatherConditionService;
	
	@Autowired
	WeatherConditionTypeService weatherConditionTypeService;
	
	@Autowired
	SolarSystemService solarSystemService;
	
	
	@Test
	public void testGetWeatherConditionBySolarSystemIdAndDay() {
	
		WeatherCondition weatherCondition = weatherConditionService.getWeatherConditionBySolarSystemIdAndDay(1L, 20000);
		assertThat(weatherCondition.getDay()).isEqualTo(20000);
	}
	
	@Test
	public void testCreate() {
	
		WeatherCondition wc = new WeatherCondition();
		
		WeatherConditionType weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionPrediction.RAINY);
		SolarSystem solarSystem = solarSystemService.getSolarSystemById(1L);
		
		wc.setDay(3);
		wc.setWeatherConditionType(weatherConditionType);
		wc.setSolarSystem(solarSystem);
		
		try {
			WeatherCondition result = weatherConditionService.create(wc);
			assertThat(result.getWeatherConditionType().getPrediction()).isEqualTo(WeatherConditionPrediction.RAINY);
			assertNotNull(result.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	

}
