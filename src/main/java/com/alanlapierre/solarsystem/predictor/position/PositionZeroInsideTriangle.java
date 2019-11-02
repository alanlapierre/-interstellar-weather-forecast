package com.alanlapierre.solarsystem.predictor.position;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public class PositionZeroInsideTriangle implements IPosition{

	private WeatherConditionPrediction wcp;
	
	
	public PositionZeroInsideTriangle(WeatherConditionPrediction wcp) {
		this.wcp = wcp;
	}
	
	
	@Override
	public WeatherConditionPrediction getWeatherConditionPredictionForPosition() {
		return wcp;
	}

}
