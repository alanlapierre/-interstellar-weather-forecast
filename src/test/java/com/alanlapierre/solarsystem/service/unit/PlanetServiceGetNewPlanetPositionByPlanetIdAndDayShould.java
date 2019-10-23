package com.alanlapierre.solarsystem.service.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.model.Direction;
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.model.PolarCoordinate;
import com.alanlapierre.solarsystem.repository.PlanetRepository;
import com.alanlapierre.solarsystem.service.PlanetService;
import com.alanlapierre.solarsystem.service.PlanetServiceImpl;
import com.alanlapierre.solarsystem.util.DirectionName;


public class PlanetServiceGetNewPlanetPositionByPlanetIdAndDayShould {
	
	 private PlanetService planetService;
	
	 private PlanetRepository planetRepository;
	 
	 private PolarCoordinate polarCoordinate;
	 
	 private CartesianCoordinate cartesianCoordinate;
	 
	 @Before
	 public void init() {
		 
		 polarCoordinate = new PolarCoordinate(200D, 0D, null);
		 cartesianCoordinate = new CartesianCoordinate(200D, 0D);
		
		 planetRepository = Mockito.mock(PlanetRepository.class);
		 planetService = new PlanetServiceImpl(planetRepository);

	  }
	
	// ********************************************************************************
	// TESTS PARA PARAMETROS INCORRECTOS
	// ********************************************************************************
	
	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_day_is_null() throws Exception {
		
		planetService.getNewPlanetPositionByPlanetIdAndDay(1L, null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_planetId_is_null() throws Exception {
		
		planetService.getNewPlanetPositionByPlanetIdAndDay(null, 1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_day_is_negative() throws Exception {
		
		planetService.getNewPlanetPositionByPlanetIdAndDay(1L, -10);
		
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void throw_exception_when_day_is_zero() throws Exception {

		planetService.getNewPlanetPositionByPlanetIdAndDay(1L, 0);
	
	}

	
	// ********************************************************************************
	// TESTS PARA SENTIDO ANTIHORARIO 
	// ********************************************************************************	

	@Test
	public void return_1_when_day_is_1_and_displacement_is_1_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 1;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(1.0));
		
	}
	


	@Test
	public void return_20_when_day_is_20_and_displacement_is_1_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 20;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		
		
		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(20.0));
		
	}
	
	@Test
	public void return_2_when_day_is_1_and_displacement_is_2_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 1;
		//Displacement
		Double DISPLACEMENT = 2D;
	
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		
		
		
		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(2.0));
		
	}
	
	@Test
	public void return_40_when_day_is_20_and_displacement_is_2_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 20;
		//Displacement
		Double DISPLACEMENT = 2D;
	
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		
		
		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(40.0));
		
	}
	
	@Test
	public void return_1_when_day_is_361_and_displacement_is_1_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 361;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(1.0));

	}
	
	@Test
	public void return_2_when_day_is_1082_and_displacement_is_1_and_direction_is_anticlockwise() throws Exception{

		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 1082;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(2.0));
		
	}
	
	
	@Test
	public void return_0_when_day_is_360_and_displacement_is_1_and_direction_is_anticlockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.ANTICLOCKWISE); 
		//Day
		Integer DAY = 360;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(0.0));
		
	
	}

	// ********************************************************************************
	// TESTS PARA SENTIDO HORARIO 
	// ********************************************************************************
	
	@Test
	public void return_359_when_day_is_1_and_displacement_is_1_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 1;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(359.0));
		
	}
	
	@Test
	public void return_340_when_day_is_20_and_displacement_is_1_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 20;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(340.0));
		
	}
	
	
	@Test
	public void return_358_when_day_is_1_and_displacement_is_2_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 1;
		//Displacement
		Double DISPLACEMENT = 2D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(358.0));
		
	}
	
	
	@Test
	public void return_320_when_day_is_20_and_displacement_is_2_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 20;
		//Displacement
		Double DISPLACEMENT = 2D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(320.0));
		
	}
	
	
	@Test
	public void return_359_when_day_is_361_and_displacement_is_1_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 361;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(359.0));
		
	}
	
	@Test
	public void return_358_when_day_is_1082_and_displacement_is_1_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 1082;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(358.0));
		
	}


	@Test
	public void return_0_when_day_is_360_and_displacement_is_1_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 360;
		//Displacement
		Double DISPLACEMENT = 1D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(0.0));
		
	}
	
	@Test
	public void return_0_when_day_is_180_and_displacement_is_2_and_direction_is_clockwise() throws Exception{
		
		//Direction
		Direction DIRECTION = new Direction(DirectionName.CLOCKWISE); 
		//Day
		Integer DAY = 180;
		//Displacement
		Double DISPLACEMENT = 2D;
		
		this.mockPlanetRepositoryFindById(DIRECTION, DISPLACEMENT);		

		Planet result = planetService.getNewPlanetPositionByPlanetIdAndDay(1L, DAY);
		assertThat(result.getPolarCoordinate().getAngle(), is(0.0));
		
	}
	
	
	private void mockPlanetRepositoryFindById(Direction DIRECTION, Double DISPLACEMENT) {
		Planet planet = new Planet(DISPLACEMENT, "Test", polarCoordinate, cartesianCoordinate, DIRECTION, null);
		Optional<Planet> optionaPlanet =  Optional.of(planet);
		Mockito.when(planetRepository.findById(Mockito.anyLong())).thenReturn(optionaPlanet);
	}
	
}
