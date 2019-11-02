package com.alanlapierre.solarsystem.calculator.position;

import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

public class AlignedBetweenThem implements IPosition{

	@Override
	public WeatherConditionTypeName getWeatherConditionTypeName() {
		return WeatherConditionTypeName.OPTIMAL_CONDITIONS;
	}

}
