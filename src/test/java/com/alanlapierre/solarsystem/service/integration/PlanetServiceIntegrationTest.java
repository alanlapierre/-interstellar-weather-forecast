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
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.service.PlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PlanetServiceIntegrationTest {
	
	@Autowired
	PlanetService planetService;
	
	@Test
	public void testGetNewPlanetPositionByPlanetIdAndDay() {
	
		try {
			Planet planet = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, 3);
			assertThat(planet.getPolarCoordinate().getAngle()).isEqualTo(357);
			assertNotNull(planet.getId());
		} catch (IllegalArgumentException | BusinessException e) {
			e.printStackTrace();
		}
		
	}


}
