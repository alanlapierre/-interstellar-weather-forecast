package com.alanlapierre.solarsystem.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.service.SolarSystemService;
import com.alanlapierre.solarsystem.vo.PeriodWeatherConditionVO;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "solarsystem")
public class SolarSystemController {

	@Autowired
	private SolarSystemService solarSystemService;

	Logger logger = LogManager.getLogger(SolarSystemController.class);

	@GetMapping("/solarsystems/{solarSystemId}/dayweathercondition")
	@ApiOperation(value = "Calculate the weather conditions for a given Solar System", notes = "Service to calculate the weather conditions for a given Solar System")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Weather conditions calculated successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> getDayWeatherCondition(@PathVariable Long solarSystemId,
			@RequestParam(value = "day", required = true) Integer day) {

		try {
			logger.info("SolarSystemController - getDayWeatherCondition, day=" + day);
			// La condición para un dia determinado.
			WeatherConditionVO weatherConditionVO = solarSystemService
					.determineWeatherConditionBySolarSystemIdAndDay(solarSystemId, day);
			return new ResponseEntity<>(weatherConditionVO, HttpStatus.OK);

		} catch (IllegalArgumentException | BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/solarsystems/{solarSystemId}/periodweatherconditions")
	@ApiOperation(value = "Calculate the weather conditions for a given Solar System", notes = "Service to calculate the period weather conditions for a given Solar System")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Weather conditions calculated successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> getPeriodWeatherConditions(@PathVariable Long solarSystemId,
			@RequestParam(value = "years", required = true) Integer years) {

		try {
			logger.info("SolarSystemController - getPeriodWeatherCondition, years=" + years);
			
			// Conjunto de condiciones para un numero de años dado.
			PeriodWeatherConditionVO periodWeatherConditionVO = solarSystemService
					.determineWeatherConditionsBySolarSystemIdAndYears(solarSystemId, years);
			return new ResponseEntity<>(periodWeatherConditionVO, HttpStatus.OK);

		} catch (IllegalArgumentException | BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
