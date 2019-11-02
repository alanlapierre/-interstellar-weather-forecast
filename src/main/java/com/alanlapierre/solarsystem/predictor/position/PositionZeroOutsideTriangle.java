package com.alanlapierre.solarsystem.predictor.position;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public class PositionZeroOutsideTriangle implements IPosition{

	private WeatherConditionPrediction wcp;
	
	
	public PositionZeroOutsideTriangle(WeatherConditionPrediction wcp) {
		this.wcp = wcp;
	}
	
	@Override
	public WeatherConditionPrediction getWeatherConditionPredictionForPosition() {
		return wcp;
	}

}
