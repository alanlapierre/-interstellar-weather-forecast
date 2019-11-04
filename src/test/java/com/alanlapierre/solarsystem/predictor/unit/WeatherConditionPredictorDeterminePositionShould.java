package com.alanlapierre.solarsystem.predictor.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.predictor.IPositionable;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPredictor;
import com.alanlapierre.solarsystem.predictor.position.IPosition;
import com.alanlapierre.solarsystem.predictor.type.StandardWeatherConditionPredictor;


public class WeatherConditionPredictorDeterminePositionShould {
	
	private WeatherConditionPredictor weatherConditionPredictor;
	
	
	@Before
	 public void init() {
		weatherConditionPredictor = new StandardWeatherConditionPredictor();
	}
	
	
	@Test
	public void return_optimal_conditions_if_positions_are_aligned_beetween_them() throws Exception {
		
		List<IPositionable> positionList = new ArrayList<IPositionable>();
		positionList.add(new CartesianCoordinate(-1D, -1D));
		positionList.add(new CartesianCoordinate(-1D, 0D));
		positionList.add(new CartesianCoordinate(-1D, 1D));
		
		IPosition position = weatherConditionPredictor.determinePosition(positionList);
		assertThat(position.getWeatherConditionPredictionForPosition(), is(WeatherConditionPrediction.OPTIMAL_CONDITIONS));
	}
	
	
	@Test
	public void return_drought_if_positions_are_aligned_beetween_them_and_position_zero() throws Exception {
		
		List<IPositionable> positionList = new ArrayList<IPositionable>();
		positionList.add(new CartesianCoordinate(1D, 1D));
		positionList.add(new CartesianCoordinate(2D, 2D));
		positionList.add(new CartesianCoordinate(3D, 3D));
		
		IPosition position = weatherConditionPredictor.determinePosition(positionList);
		assertThat(position.getWeatherConditionPredictionForPosition(), is(WeatherConditionPrediction.DROUGHT));
	}
	
	
	
	@Test
	public void return_rainy_if_position_zero_is_inside_triangle() throws Exception{

		List<IPositionable> positionList = new ArrayList<IPositionable>();
		positionList.add(new CartesianCoordinate(2D, 2D));
		positionList.add(new CartesianCoordinate(1D, -3D));
		positionList.add(new CartesianCoordinate(-2D, 1D));
		
		IPosition position = weatherConditionPredictor.determinePosition(positionList);
		assertThat(position.getWeatherConditionPredictionForPosition(), is(WeatherConditionPrediction.RAINY));

	}
	
	
	@Test
	public void return_undetermined_if_position_zero_is_outside_triangle() throws Exception{
		
		List<IPositionable> positionList = new ArrayList<IPositionable>();
		positionList.add(new CartesianCoordinate(2D, 2D));
		positionList.add(new CartesianCoordinate(-2D, 1D));
		positionList.add(new CartesianCoordinate(-2D, 3D));
		
		IPosition position = weatherConditionPredictor.determinePosition(positionList);
		assertThat(position.getWeatherConditionPredictionForPosition(), is(WeatherConditionPrediction.UNDETERMINED));

	}
	
}
