package com.alanlapierre.solarsystem.service.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.model.Direction;
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.model.PolarCoordinate;
import com.alanlapierre.solarsystem.model.SolarSystem;
import com.alanlapierre.solarsystem.model.WeatherConditionType;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPredictor;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.repository.SolarSystemRepository;
import com.alanlapierre.solarsystem.service.PlanetService;
import com.alanlapierre.solarsystem.service.SolarSystemService;
import com.alanlapierre.solarsystem.service.SolarSystemServiceImpl;
import com.alanlapierre.solarsystem.service.WeatherConditionService;
import com.alanlapierre.solarsystem.service.WeatherConditionTypeService;
import com.alanlapierre.solarsystem.util.DirectionName;
import com.alanlapierre.solarsystem.vo.WeatherConditionVO;


public class SolarSystemServiceDetermineWeatherConditionBySolarSystemIdAndDayShould {
	
	
	private SolarSystemService solarSystemService;
	
	private SolarSystemRepository solarSystemRepository;

	private PlanetService planetService;

	private WeatherConditionService weatherConditionService;

	private WeatherConditionTypeService weatherConditionTypeService;
	
	private WeatherConditionPredictor weatherConditionPredictor;
	
	
	@Before
	 public void init() {
		 
		solarSystemRepository = Mockito.mock(SolarSystemRepository.class);
		this.mockSolarSystemRepositoryFindById();

		planetService = Mockito.mock(PlanetService.class);
		
		weatherConditionService = Mockito.mock(WeatherConditionService.class);
		Mockito.when(weatherConditionService.getWeatherConditionBySolarSystemIdAndDay(Mockito.anyLong(), Mockito.anyInt())).thenReturn(null);

		weatherConditionTypeService = Mockito.mock(WeatherConditionTypeService.class);
		this.mockWeatherConditionTypeServiceGetWeatherConditionTypeByName();
		
		weatherConditionPredictor = Mockito.mock(WeatherConditionPredictor.class);
		
		
		solarSystemService = new SolarSystemServiceImpl(solarSystemRepository, planetService, weatherConditionService, weatherConditionTypeService, weatherConditionPredictor);

	}
	
	
	// ********************************************************************************
	// TESTS PARA PARAMETROS INCORRECTOS
	// ********************************************************************************
	
	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_solarSystemId_is_null_and_day_is_null() throws Exception {
		
		solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(null, null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_solarSystemId_is_not_null_and_day_is_negative() throws Exception {
		
		solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, -1);
	}


	@Test
	public void return_optimal_conditions_if_planets_are_aligned_beetween_them() throws Exception {
		
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(1L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-1D, -1D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(2L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-1D, 0D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(3L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-1D, 1D), null, null));

		WeatherConditionVO weatherConditionVO = solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, 1);
		
		assertThat(weatherConditionVO.getWeatherConditionDescription(), is(WeatherConditionPrediction.OPTIMAL_CONDITIONS));
		
	}
	
	
	@Test
	public void return_drought_if_planets_are_aligned_beetween_them_and_sun() throws Exception {
		
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(1L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(1D, 1D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(2L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(2D, 2D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(3L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(3D, 3D), null, null));
		
		WeatherConditionVO weatherConditionVO = solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, 1);
		
		assertThat(weatherConditionVO.getWeatherConditionDescription(), is(WeatherConditionPrediction.DROUGHT));
		
	}
	
	
	
	@Test
	public void return_rainy_if_sun_is_inside_triangle() throws Exception{

		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(1L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(2D, 2D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(2L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(1D, -3D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(3L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-2D, 1D), null, null));
		
		WeatherConditionVO weatherConditionVO = solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, 1);
		
		assertThat(weatherConditionVO.getWeatherConditionDescription(), is(WeatherConditionPrediction.RAINY));

	}
	
	
	@Test
	public void return_undetermined_if_sun_is_outside_triangle() throws Exception{
		
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(1L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(2D, 2D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(2L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-2D, 1D), null, null));
		Mockito.when(planetService.getNewPlanetPositionByPlanetIdAndDay(Mockito.eq(3L), Mockito.anyInt())).thenReturn(new Planet(null, null, null, new CartesianCoordinate(-2D, 3D), null, null));
		
		WeatherConditionVO weatherConditionVO = solarSystemService.determineWeatherConditionBySolarSystemIdAndDay(1L, 1);
		
		assertThat(weatherConditionVO.getWeatherConditionDescription(), is(WeatherConditionPrediction.UNDETERMINED));
	}
	
	private void mockSolarSystemRepositoryFindById() {
		List<Planet> planetList = this.getPlanetList();
		
		SolarSystem solarSystem = new SolarSystem("SolarSystem Test", planetList, null);
		Optional<SolarSystem> optionaSolarSystem =  Optional.of(solarSystem);
		
		Mockito.when(solarSystemRepository.findById(Mockito.anyLong())).thenReturn(optionaSolarSystem);
	}


	private List<Planet> getPlanetList() {
		Planet planet1 = new Planet(1L, 1D, "Planet 1", new PolarCoordinate(500D,0D, null), new CartesianCoordinate(500D, 0D), new Direction(DirectionName.CLOCKWISE), null);
		Planet planet2 = new Planet(2L, 3D, "Planet 2", new PolarCoordinate(2000D,0D, null), new CartesianCoordinate(2000D, 0D), new Direction(DirectionName.CLOCKWISE), null);
		Planet planet3 = new Planet(3L, 5D, "Planet 3", new PolarCoordinate(1000D,0D, null), new CartesianCoordinate(1000D, 0D), new Direction(DirectionName.ANTICLOCKWISE), null);
		
		List<Planet> planetList = new ArrayList<Planet>();
		planetList.add(planet1);
		planetList.add(planet2);
		planetList.add(planet3);
		return planetList;
	}
	
	private void mockWeatherConditionTypeServiceGetWeatherConditionTypeByName() {
		Mockito.when(weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionPrediction.DROUGHT)).thenReturn(new WeatherConditionType(WeatherConditionPrediction.DROUGHT));
		Mockito.when(weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionPrediction.OPTIMAL_CONDITIONS)).thenReturn(new WeatherConditionType(WeatherConditionPrediction.OPTIMAL_CONDITIONS));
		Mockito.when(weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionPrediction.RAINY)).thenReturn(new WeatherConditionType(WeatherConditionPrediction.RAINY));
		Mockito.when(weatherConditionTypeService.getWeatherConditionTypeByName(WeatherConditionPrediction.UNDETERMINED)).thenReturn(new WeatherConditionType(WeatherConditionPrediction.UNDETERMINED));
	}

}
