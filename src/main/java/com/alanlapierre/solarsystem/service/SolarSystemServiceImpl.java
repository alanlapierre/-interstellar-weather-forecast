package com.alanlapierre.solarsystem.service;

import static com.alanlapierre.solarsystem.util.Constants.getDaysPerYear;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.calculator.IPositionable;
import com.alanlapierre.solarsystem.calculator.PositionCalculator;
import com.alanlapierre.solarsystem.calculator.PositionName;
import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.model.SolarSystem;
import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.repository.SolarSystemRepository;
import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;
import com.alanlapierre.solarsystem.validator.ParamValidator;
import com.alanlapierre.solarsystem.vo.PeriodWeatherConditionVO;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;

@Service("solarSystemService")
@Transactional(readOnly = true)
public class SolarSystemServiceImpl implements SolarSystemService {

	Logger logger = LogManager.getLogger(SolarSystemServiceImpl.class);

	private final SolarSystemRepository solarSystemRepository;

	private final PlanetService planetService;

	private final WeatherConditionService weatherConditionService;

	private WeatherConditionTypeService weatherConditionTypeService;

	public SolarSystemServiceImpl(SolarSystemRepository solarSystemRepository, PlanetService planetService,
			WeatherConditionService weatherConditionService, WeatherConditionTypeService weatherConditionTypeService) {
		
		this.solarSystemRepository = solarSystemRepository;
		this.planetService = planetService;
		this.weatherConditionService = weatherConditionService;
		this.weatherConditionTypeService = weatherConditionTypeService;
	}

	public WeatherConditionVO determineWeatherConditionBySolarSystemIdAndDay(Long solarSystemId, Integer day)
			throws IllegalArgumentException, BusinessException {

		ParamValidator.test(day, (i)-> i == null || i <= 0);
		ParamValidator.test(solarSystemId, (i)-> i == null || i <= 0);

		WeatherConditionVO result = null;

		// Intentamos obtener la condicion previamente calculada si es que existe.
		WeatherCondition weatherCondition = weatherConditionService
				.getWeatherConditionBySolarSystemIdAndDay(solarSystemId, day);

		if (weatherCondition == null) {
			weatherCondition = generateNewWeatherCondition(solarSystemId, day);
			WeatherCondition weatherConditionSaved = weatherConditionService.create(weatherCondition);
			result = weatherConditionService.mapToWeatherConditionVO(weatherConditionSaved);
		} else {
			result = weatherConditionService.mapToWeatherConditionVO(weatherCondition); 
		}
		
		return result;
	}

	public PeriodWeatherConditionVO determineWeatherConditionsBySolarSystemIdAndYears(Long solarSystemId, Integer years)
			throws IllegalArgumentException, BusinessException {

		ParamValidator.test(years, (i)-> i == null || i <= 0 || i > 10);
		ParamValidator.test(solarSystemId, (i)-> i == null || i <= 0);

		Integer totalDays = getDaysPerYear() * years;
		Integer droughtPeriods, rainyPeriods, optimalPeriods, maxTriangleAreaDay;
		droughtPeriods = rainyPeriods = optimalPeriods = maxTriangleAreaDay = 0;
		Double maxTriangleAreaValue = 0D;

		PeriodWeatherConditionVO periodWeatherConditionVO = new PeriodWeatherConditionVO();
		periodWeatherConditionVO.setSolarSystemId(solarSystemId);
		periodWeatherConditionVO.setYears(years);

		List<WeatherConditionVO> weatherConditions = new ArrayList<WeatherConditionVO>();

		for (int i = 1; i <= totalDays; i++) {
			WeatherConditionVO weatherConditionVO = determineWeatherConditionBySolarSystemIdAndDay(solarSystemId, i);
			weatherConditions.add(weatherConditionVO);

			switch (weatherConditionVO.getWeatherConditionDescription()) {
			case OPTIMAL_CONDITIONS:
				optimalPeriods++;
				break;
			case DROUGHT:
				droughtPeriods++;
				break;
			case RAINY:
				Double area = weatherConditionVO.getTriangleArea();
				if (area > maxTriangleAreaValue) {
					maxTriangleAreaValue = area;
					maxTriangleAreaDay = i;
				}
				rainyPeriods++;
			default:
				break;
			}
		}

		periodWeatherConditionVO.setWeatherConditions(weatherConditions);
		periodWeatherConditionVO.setDroughtPeriods(droughtPeriods);
		periodWeatherConditionVO.setRainyPeriods(rainyPeriods);
		periodWeatherConditionVO.setOptimalPeriods(optimalPeriods);
		periodWeatherConditionVO.setDayWithMaximumRainfallIntensity(maxTriangleAreaDay);
		return periodWeatherConditionVO;

	} 
	
	@Override
	public SolarSystem getSolarSystemById(Long solarSystemId) {
		return solarSystemRepository.findById(solarSystemId).get();
	}


	private WeatherCondition generateNewWeatherCondition(Long solarSystemId, Integer day) throws BusinessException {
		
		SolarSystem solarSystemSaved = solarSystemRepository.findById(solarSystemId).get();
		List<Planet> planetList = planetService.getNewPlanetPositionsByDay(solarSystemSaved.getPlanets(), day);

		List<IPositionable> listPositions = new ArrayList<IPositionable>();
		
		for (Planet planet : planetList) {
			listPositions.add(planet.getCartesianCoordinate());	
		}
		
		PositionName positionName = PositionCalculator.determinePosition(listPositions);
		
		WeatherCondition weatherCondition = new WeatherCondition();
		weatherCondition.setDay(day);

		WeatherConditionType weatherConditionType;

		Double triangleArea = 0D;
		
		switch(positionName) {
			case POSITIONS_ALIGNED_BETWEEN_THEM_AND_POSITION_ZERO:
				weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionTypeName.DROUGHT);
				break;
			case POSITIONS_ALIGNED_BETWEEN_THEM:
				weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionTypeName.OPTIMAL_CONDITIONS);
				break;
			case POSITION_ZERO_INSIDE_TRIANGLE:
				weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionTypeName.RAINY);
				break;
			default:
				weatherConditionType = weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionTypeName.UNDETERMINED);
		}		
				
		CartesianCoordinate p1 = planetList.get(0).getCartesianCoordinate();
		CartesianCoordinate p2 = planetList.get(1).getCartesianCoordinate();
		CartesianCoordinate p3 = planetList.get(2).getCartesianCoordinate();
		triangleArea = PositionCalculator.getTriangleArea(p1, p2, p3);
		
		weatherCondition.setWeatherConditionType(weatherConditionType);
		weatherCondition.setSolarSystem(solarSystemSaved);
		weatherCondition.setTriangleArea(triangleArea);
		return weatherCondition;
	}

}
