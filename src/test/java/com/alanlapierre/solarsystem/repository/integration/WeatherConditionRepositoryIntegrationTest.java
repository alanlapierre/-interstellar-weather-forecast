package com.alanlapierre.solarsystem.repository.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.repository.WeatherConditionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class WeatherConditionRepositoryIntegrationTest {
	
	@Autowired
    private WeatherConditionRepository weatherConditionRepository;
	
	@Test
	public void testFindBySolarSystemIdAndDay() {
		WeatherCondition weatherCondition = weatherConditionRepository.findBySolarSystemIdAndDay(1L, -1);
		assertThat(weatherCondition.getDay()).isEqualTo(-1);
	
	}

}
