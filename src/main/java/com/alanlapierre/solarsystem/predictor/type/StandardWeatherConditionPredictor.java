package com.alanlapierre.solarsystem.predictor.type;

import org.springframework.stereotype.Component;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPredictor;
import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;
import com.alanlapierre.solarsystem.predictor.position.AlignedBetweenThem;
import com.alanlapierre.solarsystem.predictor.position.AlignedBetweenThemAndPositionZero;
import com.alanlapierre.solarsystem.predictor.position.IPosition;
import com.alanlapierre.solarsystem.predictor.position.PositionZeroInsideTriangle;
import com.alanlapierre.solarsystem.predictor.position.PositionZeroOutsideTriangle;

@Component("weatherConditionPredictor")
public class StandardWeatherConditionPredictor extends WeatherConditionPredictor{

	@Override
	protected IPosition getAlignedBetweenThemPosition() {
		return new AlignedBetweenThem(WeatherConditionPrediction.OPTIMAL_CONDITIONS);
	}

	@Override
	protected IPosition getAlignedBetweenThemAndPositionZero() {
		return new AlignedBetweenThemAndPositionZero(WeatherConditionPrediction.DROUGHT);
	}

	@Override
	protected IPosition getPositionZeroInsideTriangle() {
		return new PositionZeroInsideTriangle(WeatherConditionPrediction.RAINY);
	}

	@Override
	protected IPosition getPositionZeroOutsideTriangle() {
		return new PositionZeroOutsideTriangle(WeatherConditionPrediction.UNDETERMINED);
	}

}
