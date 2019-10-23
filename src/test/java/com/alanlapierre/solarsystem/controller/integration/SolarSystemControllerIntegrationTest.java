package com.alanlapierre.solarsystem.controller.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alanlapierre.solarsystem.service.PlanetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@AutoConfigureMockMvc
public class SolarSystemControllerIntegrationTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    private final static String API_ROOT_PATH ="/api/v1";
	
	
	Logger logger = LogManager.getLogger(PlanetServiceImpl.class);

	
	@Test
	public void testGetWeatherConditionByDay() throws UnsupportedEncodingException, Exception {
		
		String response = mockMvc.perform(get(API_ROOT_PATH +"/solarsystems/{solarSystemId}/weathercondition?day=200", 1))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.day", is(200)))
                .andReturn().getResponse().getContentAsString();
		
		logger.info("response testGetWeatherConditionByDay: " + response);
   }
	
	
	@Test
	public void testGetWeatherConditionByYears() throws UnsupportedEncodingException, Exception {
		
		String response = mockMvc.perform(get(API_ROOT_PATH +"/solarsystems/{solarSystemId}/weathercondition?years=1", 1))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.solarSystemId", is(1)))
                .andReturn().getResponse().getContentAsString();

		logger.info("response testGetWeatherConditionByYears: " + response);
	}	

}
