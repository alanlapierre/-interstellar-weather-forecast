package com.alanlapierre.solarsystem.predictor.position;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public class AlignedBetweenThemAndPositionZero implements IPosition{

	private WeatherConditionPrediction wcp;
	
	
	public AlignedBetweenThemAndPositionZero(WeatherConditionPrediction wcp) {
		this.wcp = wcp;
	}
	
	
	@Override
	public WeatherConditionPrediction getWeatherConditionPredictionForPosition() {
		return wcp;
	}

}
