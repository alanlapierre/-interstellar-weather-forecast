package com.alanlapierre.solarsystem.repository.integration;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.repository.WeatherConditionTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class WeatherConditionTypeRepositoryIntegrationTest {

	@Autowired
    private WeatherConditionTypeRepository weatherConditionTypeRepository;
	
	@Test
	public void testFindById() {
		
		WeatherConditionType weatherConditionType = weatherConditionTypeRepository.findById(4L).get();
		assertThat(weatherConditionType.getName().toString()).isEqualToIgnoringCase("UNDETERMINED");
	}
	
	
	@Test
	public void testFindByName() {
		WeatherConditionType weatherConditionType = weatherConditionTypeRepository.findByName(WeatherConditionPrediction.DROUGHT);
		assertThat(weatherConditionType.getId()).isEqualTo(1);	
	}
	
}
