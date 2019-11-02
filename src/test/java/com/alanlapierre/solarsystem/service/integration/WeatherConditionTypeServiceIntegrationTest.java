package com.alanlapierre.solarsystem.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.service.WeatherConditionTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class WeatherConditionTypeServiceIntegrationTest {
	
	@Autowired
	WeatherConditionTypeService weatherConditionTypeService;
	
	
	@Test
	public void testGetWeatherConditionTypeByName() {
		
		WeatherConditionPrediction name = WeatherConditionPrediction.DROUGHT;
		WeatherConditionType weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(name);
		assertThat(weatherConditionType.getName().toString()).isEqualToIgnoringCase("DROUGHT");
	}

}
