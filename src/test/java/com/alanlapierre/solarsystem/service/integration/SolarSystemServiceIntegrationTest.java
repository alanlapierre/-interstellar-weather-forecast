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
import com.alanlapierre.solarsystem.service.SolarSystemService;
import com.alanlapierre.solarsystem.vo.PeriodWeatherConditionVO;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SolarSystemServiceIntegrationTest {
	
	
	@Autowired
	SolarSystemService solarSystemService;
	
	@Test
	public void testDetermineWeatherConditionBySolarSystemIdAndDay() {
		try {
			WeatherConditionVO weatherConditionVO = solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, 3);
			assertNotNull(weatherConditionVO);
		} catch (IllegalArgumentException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDetermineWeatherConditionsBySolarSystemIdAndYears() {
		try {
			PeriodWeatherConditionVO periodWeatherConditionVO = solarSystemService.determineWeatherConditionsBySolarSystemIdAndYears(1L, 1);
			assertNotNull(periodWeatherConditionVO);
		} catch (IllegalArgumentException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetSolarSystemById() {
		SolarSystem solarSystem = solarSystemService.getSolarSystemById(1L);
		assertThat(solarSystem.getId()).isEqualTo(1L);
	}
	


}
