package com.alanlapierre.solarsystem.service;

import static com.alanlapierre.solarsystem.util.Constants.getDaysPerYear;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.model.SolarSystem;
import com.alanlapierre.solarsystem.model.WeatherCondition;
import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.repository.SolarSystemRepository;
import com.alanlapierre.solarsystem.util.ParamValidator;
import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;
import com.alanlapierre.solarsystem.vo.PeriodWeatherConditionVO;
import com.alanlapierre.solarsystem.vo.VectorDefinitionVO;
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
		
		// Intentamos obtener la condicion previamente calculada si es que existe.
		WeatherCondition weatherCondition = weatherConditionService
				.getWeatherConditionBySolarSystemIdAndDay(solarSystemId, day);

		if (weatherCondition == null) {
			weatherCondition = generateNewWeatherCondition(solarSystemId, day);
			WeatherCondition weatherConditionSaved = weatherConditionService.create(weatherCondition);
			if(weatherConditionSaved != null) {
				weatherCondition.setId(weatherConditionSaved.getId());
			}
		}
		return mapToWeatherConditionVO(weatherCondition);

	}

	public PeriodWeatherConditionVO determineWeatherConditionsBySolarSystemIdAndYears(Long solarSystemId, Integer years)
			throws IllegalArgumentException, BusinessException {

		ParamValidator.test(years, (i)-> i == null || i <= 0 || i > 10);
		ParamValidator.test(solarSystemId, (i)-> i == null || i <= 0);

		Integer totalDays = getDaysPerYear() * years;
		Integer droughtPeriods = 0;
		Integer rainyPeriods = 0;
		Integer optimalPeriods = 0;

		Double maxTriangleAreaValue = 0D;
		Integer maxTriangleAreaDay = 0;

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
		List<Planet> planetList = this.calculateNewPlanetPositionByDay(solarSystemSaved.getPlanets(), day);

		List<VectorDefinitionVO> vectorsBetweenPlanets = this.createVectorsBetweenPlanets(planetList);
		Boolean arePlanetsAlignedBetweenThem = this.determineIfPlanetsAreAlignedBetweenThem(vectorsBetweenPlanets);
		Boolean arePlanetsAlignedBetweenThemAndSun = this.determineIfPlanetsAreAlignedWithSun(vectorsBetweenPlanets,
				planetList);

		WeatherCondition weatherCondition = new WeatherCondition();
		weatherCondition.setDay(day);

		WeatherConditionType weatherConditionType;

		Double triangleArea = 0D;
		if (arePlanetsAlignedBetweenThem && arePlanetsAlignedBetweenThemAndSun) {
			weatherConditionType = weatherConditionTypeService
					.getWeatherConditionTypeByName(WeatherConditionTypeName.DROUGHT);
		} else if (arePlanetsAlignedBetweenThem) {
			weatherConditionType = weatherConditionTypeService
					.getWeatherConditionTypeByName(WeatherConditionTypeName.OPTIMAL_CONDITIONS);
		} else {

			CartesianCoordinate p1 = planetList.get(0).getCartesianCoordinate();
			CartesianCoordinate p2 = planetList.get(1).getCartesianCoordinate();
			CartesianCoordinate p3 = planetList.get(2).getCartesianCoordinate();
			triangleArea = this.getTriangleArea(p1, p2, p3);
			Boolean isSunInsideTriangle = this.sunInsideTriangle(p1, p2, p3);
			if (isSunInsideTriangle) {
				weatherConditionType = weatherConditionTypeService
						.getWeatherConditionTypeByName(WeatherConditionTypeName.RAINY);
			} else {
				weatherConditionType = weatherConditionTypeService
						.getWeatherConditionTypeByName(WeatherConditionTypeName.UNDETERMINED);
			}
		}

		weatherCondition.setWeatherConditionType(weatherConditionType);
		weatherCondition.setSolarSystem(solarSystemSaved);
		weatherCondition.setTriangleArea(triangleArea);
		return weatherCondition;
	}

	private List<Planet> calculateNewPlanetPositionByDay(List<Planet> planets, Integer day)
			throws IllegalArgumentException, BusinessException {

		List<Planet> result = new ArrayList<Planet>();

		for (Planet planet : planets) {
			Planet newPlanetPos = planetService.getNewPlanetPositionByPlanetIdAndDay(planet.getId(), day);
			result.add(newPlanetPos);
		}

		return result;
	}

	private Double getTriangleArea(CartesianCoordinate p1, CartesianCoordinate p2, CartesianCoordinate p3) {

		return Math.abs((p1.getXposition() * (p2.getYposition() - p3.getYposition())
				+ p2.getXposition() * (p3.getYposition() - p1.getYposition())
				+ p3.getXposition() * (p1.getYposition() - p2.getYposition())) / 2.0);
	}

	private Boolean determineIfPlanetsAreAlignedWithSun(List<VectorDefinitionVO> vectorsBetweenPlanets,
			List<Planet> planetList) {

		Boolean areAlignedBetweenThemAndSun = true;
		// Un vector entre algun planeta de la lista de planetas y el sol.
		// En este caso no hay resta de valores porque el sol siempre s eencuentra en el
		// punto (0,0)
		VectorDefinitionVO vectorBetweenSomePlanetAndSun = new VectorDefinitionVO(
				planetList.get(0).getCartesianCoordinate().getXposition(),
				planetList.get(0).getCartesianCoordinate().getYposition());

		// Tomamos un vector de los que se calcularon entre planetas y comprobamos el
		// valor de la determinante con el vector del paso anterior.
		Double value = (vectorsBetweenPlanets.get(0).getXposition() * vectorBetweenSomePlanetAndSun.getYposition())
				- (vectorsBetweenPlanets.get(0).getYposition() * vectorBetweenSomePlanetAndSun.getXposition());
		if (value != 0) {
			areAlignedBetweenThemAndSun = false;
		}
		return areAlignedBetweenThemAndSun;
	}

	private Boolean determineIfPlanetsAreAlignedBetweenThem(List<VectorDefinitionVO> vectorsBetweenPlanets) {
		Boolean areAlignedBetweenThem = true;
		VectorDefinitionVO v1 = null;
		VectorDefinitionVO v2 = null;

		for (int i = 0; i < vectorsBetweenPlanets.size(); i++) {
			if (v1 == null) {
				v1 = vectorsBetweenPlanets.get(i);
			} else {
				v2 = vectorsBetweenPlanets.get(i);
			}
			if (v1 != null && v2 != null) {
				Double value = (v1.getXposition() * v2.getYposition()) - (v1.getYposition() * v2.getXposition());
				v1 = v2;
				v2 = null;
				if (value != 0) {
					areAlignedBetweenThem = false;
				}
			}
		}
		return areAlignedBetweenThem;

	}

	private List<VectorDefinitionVO> createVectorsBetweenPlanets(List<Planet> planetList) {
		CartesianCoordinate cc1 = null;
		CartesianCoordinate cc2 = null;
		List<VectorDefinitionVO> vectors = new ArrayList<VectorDefinitionVO>();

		for (Planet planet : planetList) {
			if (cc1 == null) {
				cc1 = planet.getCartesianCoordinate();
			} else {
				cc2 = planet.getCartesianCoordinate();
			}
			if (cc1 != null && cc2 != null) {
				Double xPosition = cc1.getXposition() - cc2.getXposition();
				Double yPosition = cc1.getYposition() - cc2.getYposition();
				VectorDefinitionVO vector = new VectorDefinitionVO(xPosition, yPosition);
				vectors.add(vector);
				cc1 = cc2;
				cc2 = null;
			}
		}
		return vectors;
	}

	private Boolean sunInsideTriangle(CartesianCoordinate p1, CartesianCoordinate p2, CartesianCoordinate p3) {

		// Punto P.
		CartesianCoordinate sunPosition = new CartesianCoordinate(0D, 0D);
		// Area de triangulo ABC
		double A = getTriangleArea(p1, p2, p3);
		// Area de triangulo PBC
		double A1 = getTriangleArea(sunPosition, p2, p3);
		// Area de triangulo PAC
		double A2 = getTriangleArea(p1, sunPosition, p3);
		// Area de triangulo PAB
		double A3 = getTriangleArea(p1, p2, sunPosition);
		// Verificar si suma de A1, A2 y A3 es igual a A
		return (A == A1 + A2 + A3);
	}

	private WeatherConditionVO mapToWeatherConditionVO(WeatherCondition weatherCondition) {
		WeatherConditionVO vo = new WeatherConditionVO();
		vo.setDay(weatherCondition.getDay());
		vo.setSolarSystemId(weatherCondition.getSolarSystem().getId());
		vo.setWeatherConditionDescription(weatherCondition.getWeatherConditionType().getName());
		vo.setWeatherConditionId(weatherCondition.getId());
		vo.setTriangleArea(weatherCondition.getTriangleArea());
		return vo;
	}

}
