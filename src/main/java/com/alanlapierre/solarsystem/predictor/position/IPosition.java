package com.alanlapierre.solarsystem.predictor.position;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;

public interface IPosition {
	WeatherConditionPrediction getWeatherConditionPredictionForPosition();
}
