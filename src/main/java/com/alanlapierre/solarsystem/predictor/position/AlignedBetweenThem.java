package com.alanlapierre.solarsystem.predictor.position;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public class AlignedBetweenThem implements IPosition{

	private WeatherConditionPrediction wcp;
	
	
	public AlignedBetweenThem(WeatherConditionPrediction wcp) {
		this.wcp = wcp;
	}
	
	
	@Override
	public WeatherConditionPrediction getWeatherConditionPredictionForPosition() {
		return wcp;
	}

}
